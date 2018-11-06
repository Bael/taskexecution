import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';

import 'dhtmlx-gantt';
import {} from '@types/dhtmlxgantt';
import {TaskService} from '../task.service';
import {LinkService} from '../link.service';
import {Link} from '../link';
import {Task} from '../task';

@Component({
  selector: 'gantt',
  providers: [TaskService, LinkService],
  styles: [
    `
        :host{
            display: block;
            height: 600px;
            position: relative;
            width: 100%;
        }
    `],
  template: '<div #gantt_here style=\'width: 100%; height: 100%;\'></div>',
})
export class GanttComponent implements OnInit {
  @ViewChild('gantt_here') ganttContainer: ElementRef;

  ngOnInit() {
    gantt.config.xml_date = "%Y-%m-%d %H:%i";
    gantt.init(this.ganttContainer.nativeElement);

    gantt.attachEvent("onAfterTaskAdd", (id, item) => {
      this.taskService.insert(this.serializeTask(item, true))
        .then((response)=> {
          if (response.id != id) {
            gantt.changeTaskId(id, response.id);
          }
        });
    });

    gantt.attachEvent("onAfterTaskUpdate", (id, item) => {
      this.taskService.update(this.serializeTask(item));
    });

    gantt.attachEvent("onAfterTaskDelete", (id) => {
      this.taskService.remove(id);
    });

    gantt.attachEvent("onAfterLinkAdd", (id, item) => {
      this.linkService.insert(this.serializeLink(item, true))
        .then((response) => {
          if(response.id != id){
            gantt.changeLinkId(id, response.id);
          }
        });
    });

    gantt.attachEvent("onAfterLinkUpdate", (id, item) => {
      this.linkService.update(this.serializeLink(item));
    });

    gantt.attachEvent("onAfterLinkDelete", (id) => {
      this.linkService.remove(id);
    });


    Promise.all([this.taskService.get(), this.linkService.get()])
      .then(([data, links]) => {
        console.log(data);

        gantt.parse({data, links});
      });
  }

  private serializeTask(data: any, insert: boolean = false): Task {
    return this.serializeItem(data, insert) as Task;
  }

  private serializeLink(data: any, insert: boolean = false): Link {
    return this.serializeItem(data, insert) as Link;
  }

  private serializeItem(data: any, insert: boolean): any{
    var result = {};

    for (let i in data) {
      if (i.charAt(0) == "$" || i.charAt(0) == "_") continue;
      if(insert && i == "id") continue;
      if (data[i] instanceof Date) {
        result[i] = gantt.templates.xml_format(data[i]);
      }
      else {
        result[i] = data[i];
      }
    }

    return result;
  }

  constructor(private taskService: TaskService, private linkService: LinkService) {

  }
}

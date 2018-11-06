import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';

import 'dhtmlx-gantt';
import {} from '@types/dhtmlxgantt';
import {TaskService} from '../task.service';
import {LinkService} from '../link.service';

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

    Promise.all([this.taskService.get(), this.linkService.get()])
      .then(([data, links]) => {
        gantt.parse({data, links});
      });
  }

  constructor(private taskService: TaskService, private linkService: LinkService) {

  }
}

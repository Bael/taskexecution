import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';

import 'dhtmlx-gantt';
import {} from '@types/dhtmlxgantt';
import {TaskService} from '../task.service';
import {LinkService} from '../link.service';
import {Link} from '../link';
import {Task} from '../task';
import {Project} from '../project';
import {ActivatedRoute} from '@angular/router';
import {ProjectService} from '../project.service';

@Component({
  selector: 'gantt',
  providers: [TaskService, LinkService],
  styles: [
      `
      :host {
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

  project: Project;

  constructor(private route: ActivatedRoute,
              private taskService: TaskService,
              private linkService: LinkService,
              private projectService: ProjectService) {


  }

  ngOnInit() {
    this.initGantt();
  }

  initData(project: Project) {


    Promise.all([this.taskService.getByProject(project.id),
      this.linkService.getByProject(project.id)])
      .then(([data, links]) => {
        console.log(data);

        gantt.clearAll();
        gantt.parse({data, links});
        gantt.render();
      });
  }

  initGantt() {
    gantt.config.xml_date = '%Y-%m-%d %H:%i';
    gantt.init(this.ganttContainer.nativeElement);


    gantt.attachEvent('onAfterTaskAdd', (id, item) => {
      const task: Task = this.serializeTask(item, true);
      task.project = this.project.id;
      this.taskService.insert(task)
        .then((response) => {
          if (response.id !== id) {
            gantt.changeTaskId(id, response.id);
          }
        });
    });

    gantt.attachEvent('onAfterTaskUpdate', (id, item) => {
      this.taskService.update(this.serializeTask(item));
    });

    gantt.attachEvent('onAfterTaskDelete', (id) => {
      this.taskService.remove(id);
    });

    gantt.attachEvent('onAfterLinkAdd', (id, item) => {
      const link: Link = this.serializeLink(item, true);
      link.project = this.project.id;

      this.linkService.insert(link)
        .then((response) => {
          if (response.id !== id) {
            gantt.changeLinkId(id, response.id);
          }
        });
    });

    gantt.attachEvent('onAfterLinkUpdate', (id, item) => {
      this.linkService.update(this.serializeLink(item));
    });

    gantt.attachEvent('onAfterLinkDelete', (id) => {
      this.linkService.remove(id);
    });

    //
    // Promise.all([this.taskService.get(), this.linkService.get()])
    //   .then(([data, links]) => {
    //     console.log(data);
    //
    //     gantt.parse({data, links});
    //   });

    this.route.paramMap.subscribe(value => this.projectService.getProject(value.get('id'))
      .then(project => {
        this.project = project;
        this.initData(project);
      }, error1 => alert(error1)));
  }

  private serializeTask(data: any, insert: boolean = false): Task {
    return this.serializeItem(data, insert) as Task;
  }

  private serializeLink(data: any, insert: boolean = false): Link {
    return this.serializeItem(data, insert) as Link;
  }

  private serializeItem(data: any, insert: boolean): any {
    const result = {};

    for (const i in data) {
      if (i.charAt(0) === '$' || i.charAt(0) == '_') {
        continue;
      }
      if (insert && i == 'id') {
        continue;
      }
      if (data[i] instanceof Date) {
        result[i] = gantt.templates.xml_format(data[i]);
      } else {
        result[i] = data[i];
      }
    }

    return result;
  }
}

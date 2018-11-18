import {Component, OnInit} from '@angular/core';
import {Project} from '../project';
import {ProjectService} from '../project.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  project: Project = new Project();
  users: User[] = [];

  constructor(private projectService: ProjectService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(value =>
        this.projectService.getProject(value.get('id'))
          .then(project => this.project = project),
      error1 => alert(error1));

  }

  onSaveChanges() {
    this.projectService.updateProject(this.project)
      .then(project => {
          this.project = project;
          this.router.navigateByUrl('/projects');
        }
        ,
        error1 => alert(error1));
  }

  onArchiveProject() {
    this.projectService.archiveProject(this.project)
      .then(() => {
          this.router.navigateByUrl('/projects');
        }
       ,
        error1 => alert(error1));
  }

  onSaveAccess() {

  }
}

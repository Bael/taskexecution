import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {BacklogItem} from '../backlog-item';
import {Board} from '../board';
import {ProjectService} from '../project.service';
import {Project} from '../project';

@Component({
  selector: 'app-board-dialog',
  templateUrl: './board-dialog.component.html',
  styleUrls: ['./board-dialog.component.css']
})
export class BoardDialogComponent implements OnInit {
  get selectedProject(): number {
    return this._selectedProject;
  }

  set selectedProject(value: number) {
    this._selectedProject = value;
    this.data.projectId = value;
  }
  projects: Project[];
  private _selectedProject: number = null;

  constructor(private projectService: ProjectService,
    public dialogRef: MatDialogRef<BoardDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Board) {
  }

  ngOnInit() {
    this._selectedProject = this.data.projectId;
    this.projectService.getProjects().toPromise().then(value => this.projects = value);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}

import {Component, Inject, OnInit} from '@angular/core';
import {Project} from '../project';
import {ProjectService} from '../project.service';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef, MatTableDataSource} from '@angular/material';



@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projects: Project[] = [];
  displayedColumns = ['id', 'name', 'status', 'edit', 'gantt'];
  dataSource = new MatTableDataSource();

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

  constructor(private projectService: ProjectService, public dialog: MatDialog) { }

  ngOnInit() {

    this.getProjects();
      // .then(value => this.projects = value);
      // .catch(reason => alert(reason));
  }

  getProjects() {
    this.projectService.getProjects().subscribe(value => {
      this.dataSource.data = value;
      this.projects = value;
    });
  }

  onClickCreateNewProject(): void {
    const dialogRef = this.dialog.open(CreateProjectDialogComponent, {
      width: '45vw',
      data: new Project()
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed with result ' + result);
      this.projectService.createProject(result)
        .then(project => {
          this.projects.push(project);
          this.getProjects();
        } );

    });

  }


}

@Component({
  selector: 'app-create-project-dialog',
  templateUrl: 'create-project-dialog.html',
  styleUrls: ['create-project-dialog.css']
})
export class CreateProjectDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<CreateProjectDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Project) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}

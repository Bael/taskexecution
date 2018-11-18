import {Component, Inject, OnInit} from '@angular/core';
import {Project} from '../project';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef, MatTableDataSource} from '@angular/material';
import {User} from '../user';
import {UserService} from '../user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[] = [];
  displayedColumns = ['id', 'fio', 'login', 'edit'];
  dataSource = new MatTableDataSource();

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

  constructor(private userService: UserService, public dialog: MatDialog) { }

  ngOnInit() {
    this.userService.getUsers().then(value => this.users = value, reason => alert(reason));
  }

  onClickCreateNewUser() {

    const dialogRef = this.dialog.open(CreateUserDialogComponent, {
      width: '45vw',
      data: new Project()
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed with result ' + result);
      this.userService.createUser(result)
        .then(user => this.users.push(user));

    });

  }
}


@Component({
  selector: 'app-create-user-dialog',
  templateUrl: 'create-user-dialog.html',
  styleUrls: ['create-user-dialog.css']
})
export class CreateUserDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<CreateUserDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: User) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}

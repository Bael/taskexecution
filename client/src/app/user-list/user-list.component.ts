import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {Project} from '../project';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef, MatTable, MatTableDataSource} from '@angular/material';
import {User} from '../user';
import {UserService} from '../user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  @ViewChild(MatTable) table: MatTable<User>;

  users: User[] = [];
  displayedColumns = ['id', 'fio', 'login', 'edit'];
  dataSource = new MatTableDataSource();

  constructor(private userService: UserService, public dialog: MatDialog) {
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

  ngOnInit() {
    this.userService.getUsers().then(value => {
        this.users = value;
        this.dataSource.data = this.users;
      }, reason => alert(reason.toString())
    );
  }

  onClickCreateNewUser() {

    const dialogRef = this.dialog.open(CreateUserDialogComponent, {
      width: '45vw',
      data: new Project()
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed with result ' + result);
      this.userService.createUser(result)
        .then(user => {
            this.users.push(user);
            this.dataSource.data = this.users;
            this.table.renderRows();
          }
        );


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
    @Inject(MAT_DIALOG_DATA) public data: User) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}

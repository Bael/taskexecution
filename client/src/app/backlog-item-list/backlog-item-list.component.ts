import {BacklogItem} from '../backlog-item';
import {Component, OnInit, ViewChild} from '@angular/core';
import {BacklogItemService} from '../backlog-item.service';
import {Project} from '../project';
import {MatDialog, MatTable, MatTableDataSource} from '@angular/material';
import {CreateProjectDialogComponent} from '../project-list/project-list.component';
import {BacklogItemDialogComponent} from '../backlog-item-dialog/backlog-item-dialog.component';

@Component({
  selector: 'app-backlog-item-list',
  templateUrl: './backlog-item-list.component.html',
  styleUrls: ['./backlog-item-list.component.css']
})
export class BacklogItemListComponent implements OnInit {

  @ViewChild(MatTable) table: MatTable<BacklogItem>;


  list: BacklogItem[] = [];
  displayedColumns = ['id', 'name', 'priority', 'edit'];
  dataSource = new MatTableDataSource();

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }


  constructor(private backlogItemService: BacklogItemService, public dialog: MatDialog) { }

  ngOnInit() {
    this.backlogItemService.getItems().then(value => {
      this.list = value;
      this.dataSource.data = value;
    });
  }

  onClickCreateNewItem() {

    const dialogRef = this.dialog.open(BacklogItemDialogComponent, {
      width: '45vw',
      data: new BacklogItem()
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed with result ' + JSON.stringify(result));
      this.backlogItemService.createItem(result)
        .then(item => {
          this.list.push(item);
          this.dataSource.data = this.list;
          this.table.renderRows();
        } );

    });


  }
}

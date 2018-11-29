import {Component, OnInit, ViewChild} from '@angular/core';
import {BoardService} from '../board.service';
import {MatDialog, MatTable, MatTableDataSource} from '@angular/material';
import {Board} from '../board';
import {BoardDialogComponent} from '../board-dialog/board-dialog.component';

@Component({
  selector: 'app-board-list',
  templateUrl: './board-list.component.html',
  styleUrls: ['./board-list.component.css']
})
export class BoardListComponent implements OnInit {

  @ViewChild(MatTable) table: MatTable<Board>;

  boards: Board[] = [];
  displayedColumns = ['id', 'name', 'projectname', 'edit'];
  dataSource = new MatTableDataSource<Board>();

  constructor(private boardService: BoardService, public dialog: MatDialog) {
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }


  ngOnInit() {
    this.boardService.getBoards().then(value => {
      this.boards = value;
      this.dataSource.data = value;
    });
  }

  onClickSetProject(element: Board) {
    const dialogRef = this.dialog.open(BoardDialogComponent, {
      width: '45vw',
      data: element
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed with result ' + JSON.stringify(result));
      this.boardService.setProject(result).then(item => {
      this.table.renderRows();
      });
    });


  }
}

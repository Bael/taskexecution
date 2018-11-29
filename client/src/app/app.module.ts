import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {GanttComponent} from './gantt/gantt.component';
import {HttpClientModule} from '@angular/common/http';
import {
  MatButtonModule,
  MatCardModule,
  MatDialogModule,
  MatFormFieldModule, MatIconModule,
  MatInputModule,
  MatListModule, MatSelectModule,
  MatSidenavModule, MatSliderModule,
  MatTableModule,
  MatTabsModule
} from '@angular/material';
import {RouterModule, Routes} from '@angular/router';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {NavProjectComponent} from './nav-project/nav-project.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CreateProjectDialogComponent, ProjectListComponent} from './project-list/project-list.component';

import {ProjectService} from './project.service';
import {TaskService} from './task.service';
import {LinkService} from './link.service';
import {ProjectComponent} from './project/project.component';
import {FormsModule} from '@angular/forms';
import {UserService} from './user.service';
import {CreateUserDialogComponent, UserListComponent} from './user-list/user-list.component';
import { BacklogItemListComponent } from './backlog-item-list/backlog-item-list.component';
import { BacklogItemComponent } from './backlog-item/backlog-item.component';
import { BacklogItemDialogComponent } from './backlog-item-dialog/backlog-item-dialog.component';
import {BacklogItemService} from './backlog-item.service';
import { UserComponent } from './user/user.component';
import {BoardService} from './board.service';
import { BoardListComponent } from './board-list/board-list.component';
import { BoardDialogComponent } from './board-dialog/board-dialog.component';

const appRoutes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},

  {path: 'home', component: ProjectListComponent},
  {path: 'backlog', component: BacklogItemListComponent},
  {path: 'backlog/:id', component: BacklogItemComponent},
  {path: 'gantt/:id', component: GanttComponent},
  {path: 'projects', component: ProjectListComponent},
  {path: 'projects/:id', component: ProjectComponent},
  {path: 'boards', component: BoardListComponent},
  {path: 'boards/:id', component: BoardDialogComponent},
  {path: 'users', component: UserListComponent},
  {path: 'users/:id', component: UserComponent},
  {path: '**', component: PageNotFoundComponent}
];


@NgModule({
  declarations: [
    AppComponent,
    GanttComponent,
    PageNotFoundComponent,
    NavProjectComponent,
    ProjectListComponent,
    UserListComponent,
    ProjectComponent,
    CreateProjectDialogComponent,
    UserListComponent,
    CreateUserDialogComponent,
    BacklogItemListComponent,
    BacklogItemComponent,
    BacklogItemDialogComponent,
    UserComponent,
    BoardListComponent,
    BoardDialogComponent
  ],
  entryComponents: [CreateProjectDialogComponent, CreateUserDialogComponent, BacklogItemDialogComponent, BoardDialogComponent],
  imports: [
    RouterModule.forRoot(
      appRoutes, {enableTracing: true}
    ),
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatSidenavModule,
    MatCardModule,
    MatListModule,
    MatButtonModule,
    MatSidenavModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatDialogModule,
    MatTabsModule,
    MatIconModule,
    MatSliderModule,
    MatSelectModule


  ],
  providers: [ProjectService, TaskService, LinkService, UserService, BacklogItemService, BoardService],
  bootstrap: [AppComponent]
})
export class AppModule {
}

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { GanttComponent } from './gantt/gantt.component';
import {HttpClientModule} from '@angular/common/http';
import {
  MatButtonModule,
  MatCardModule, MatDialogModule,
  MatFormFieldModule,
  MatInputModule,
  MatListModule,
  MatSidenavModule,
  MatTableModule, MatTabsModule
} from '@angular/material';
import {RouterModule, Routes} from '@angular/router';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import { NavProjectComponent } from './nav-project/nav-project.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { ProjectListComponent, CreateProjectDialogComponent } from './project-list/project-list.component';

import {ProjectService} from './project.service';
import {TaskService} from './task.service';
import {LinkService} from './link.service';
import { ProjectComponent } from './project/project.component';
import {FormsModule} from '@angular/forms';
import {UserService} from './user.service';
import { UserListComponent, CreateUserDialogComponent } from './user-list/user-list.component';

const appRoutes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},

  {path: 'gantt/:id', component: GanttComponent },
  {path: 'projects', component: ProjectListComponent },
  {path: 'projects/:id', component: ProjectComponent },
  {path: 'users', component: UserListComponent },
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
    CreateUserDialogComponent
  ],
  entryComponents: [CreateProjectDialogComponent, CreateUserDialogComponent],
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
    MatTabsModule

  ],
  providers: [ProjectService, TaskService, LinkService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }

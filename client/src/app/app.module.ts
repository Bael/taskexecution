import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { GanttComponent } from './gantt/gantt.component';
import {HttpClientModule} from '@angular/common/http';
import {MatButtonModule, MatCardModule, MatSidenavModule} from '@angular/material';
import {RouterModule, Routes} from '@angular/router';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import { NavProjectComponent } from './nav-project/nav-project.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { ProjectListComponent } from './project-list/project-list.component';

const appRoutes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},

  {path: 'gantt', component: GanttComponent },
  {path: 'projects', component: ProjectListComponent },
  {path: '**', component: PageNotFoundComponent}
];


@NgModule({
  declarations: [
    AppComponent,
    GanttComponent,
    PageNotFoundComponent,
    NavProjectComponent,
    ProjectListComponent
  ],
  imports: [
    RouterModule.forRoot(
      appRoutes
    ),
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatSidenavModule,
    MatCardModule,
    MatButtonModule,
    MatSidenavModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

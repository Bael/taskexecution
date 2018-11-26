import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Settings} from './settings';
import {Project} from './project';
import {Observable} from 'rxjs/Observable';
import {User} from './user';


@Injectable()
export class ProjectService {

  constructor(private httpService: HttpClient) { }

  // public getProjects(): Promise<Project[]> {
  //   return this.httpService.get<Project[]>(Settings.baseUrl + 'projects').toPromise();
  // }
  //
  public getProjects(): Observable<Project[]> {
    return this.httpService.get<Project[]>(Settings.baseUrl + 'projects');
  }

  createProject(newProject: Project): Promise<Project> {
    return this.httpService.post<Project>(Settings.baseUrl + 'projects', newProject).toPromise();

  }

  getProject(id: string): Promise<Project> {
    return this.httpService.get<Project>(Settings.baseUrl + 'projects/' + id).toPromise();
  }

  updateProject(project: Project): Promise<Project> {
    return this.httpService.put<Project>(Settings.baseUrl + 'projects/' + project.id, project).toPromise();
  }

  archiveProject(project: Project): Promise<Project> {
    return this.httpService.put<Project>(Settings.baseUrl + 'projects/archive/' + project.id, project).toPromise();
  }

  grantAccessToUsers(selectedUserIds: number[]): Promise<void> {
    return this.httpService.post<void>(Settings.baseUrl + 'projects/access/', selectedUserIds).toPromise();
  }

  sendTasksToExecution(project: Project): Promise<void> {
    return this.httpService.post<void>(Settings.baseUrl + 'projects/send/' + project.id, project).toPromise();
  }
}

import {Injectable} from '@angular/core';
import {Task} from './task';
import {HttpClient} from '@angular/common/http';
import {Settings} from './settings';

@Injectable()
export class TaskService {

  taskUrl = Settings.baseUrl + 'tasks';

  constructor(private http: HttpClient) {
    this.taskUrl;
  }

  get(): Promise<Task[]> {

    return this.http.get<Task[]>(Settings.baseUrl + 'tasks').toPromise();

    // return Promise.resolve([
    //   {id: 1, text: 'Task #1', start_date: '2017-04-15 00:00', duration: 3, progress: 0.6},
    //   {id: 2, text: 'Task #2', start_date: '2017-04-18 00:00', duration: 3, progress: 0.4}
    // ]);
  }

  getByProject(id: number): Promise<Task[]> {

    return this.http.get<Task[]>(Settings.baseUrl + 'projects/' + id + '/tasks').toPromise();

  }

  insert(task: Task): Promise<Task> {
    return this.http.post<Task>(this.taskUrl, task).toPromise();
  }

  update(task: Task): Promise<void> {
    return this.http.put<void>(`${this.taskUrl}/${task.id}`, task).toPromise();
  }

  remove(id: number): Promise<void> {
    return this.http.delete<void>(`${this.taskUrl}/${id}`).toPromise();
  }
}

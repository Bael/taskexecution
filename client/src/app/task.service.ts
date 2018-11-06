import {Injectable} from '@angular/core';
import {Task} from './task';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class TaskService {

  constructor(private http: HttpClient) {
  }

  get(): Promise<Task[]> {

    return this.http.get<Task[]>('/api/tasks').toPromise();

    // return Promise.resolve([
    //   {id: 1, text: 'Task #1', start_date: '2017-04-15 00:00', duration: 3, progress: 0.6},
    //   {id: 2, text: 'Task #2', start_date: '2017-04-18 00:00', duration: 3, progress: 0.4}
    // ]);
  }
}

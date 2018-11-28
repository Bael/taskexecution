import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Project} from './project';
import {Settings} from './settings';
import {HttpClient} from '@angular/common/http';
import {BacklogItem} from './backlog-item';

@Injectable()
export class BacklogItemService {

  constructor(private http: HttpClient) {
  }

  public getItems(): Promise<BacklogItem[]> {
    return this.http.get<BacklogItem[]>(Settings.baseUrl + 'backlog').toPromise();
  }

  createItem(item: BacklogItem): Promise<BacklogItem> {
    return this.http.post<BacklogItem>(Settings.baseUrl + 'backlog', item).toPromise();

  }

  getItem(id: string): Promise<BacklogItem> {
    return this.http.get<BacklogItem>(Settings.baseUrl + 'backlog/' + id).toPromise();
  }

  updateItem(item: BacklogItem): Promise<BacklogItem> {
    return this.http.put<BacklogItem>(Settings.baseUrl + 'backlog/' + item.id, item).toPromise();
  }

  convertToProject(item: BacklogItem): Promise<BacklogItem> {
    return this.http.post<BacklogItem>(Settings.baseUrl + 'backlog/convert/' + item.id, item).toPromise();
  }

  deleteItem(item: BacklogItem): Promise<void> {
    return this.http.delete<void>(Settings.baseUrl + 'backlog/' + item.id).toPromise();
  }

}


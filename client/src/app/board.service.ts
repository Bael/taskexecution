import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BacklogItem} from './backlog-item';
import {Settings} from './settings';
import {Board} from './board';

@Injectable()
export class BoardService {

  constructor(private http: HttpClient) { }

  public getBoards(): Promise<Board[]> {
    return this.http.get<Board[]>(Settings.baseUrl + 'boards').toPromise();
  }

  setProject(item: Board): Promise<Board> {
    return this.http.put<Board>(Settings.baseUrl + 'boards/' + item.id, item).toPromise();
  }


}

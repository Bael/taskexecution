import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Settings} from './settings';

@Injectable()
export class UserService {

  constructor(private httpService: HttpClient) { }

  public getUsers(): Promise<User[]> {
    return this.httpService.get<User[]>(Settings.baseUrl + 'users').toPromise();
  }

}

import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Settings} from './settings';
import {User} from './user';

@Injectable()
export class UserService {

  constructor(private httpService: HttpClient) { }

  public getUsers(): Promise<User[]> {
    return this.httpService.get<User[]>(Settings.baseUrl + 'users').toPromise();
  }

  createUser(user: User): Promise<User> {
    return this.httpService.post<User>(Settings.baseUrl + 'users', user).toPromise();
  }

  updateUser(user: User): Promise<User> {
    return this.httpService.put<User>(Settings.baseUrl + 'users/' + user.id, user).toPromise();
  }
}

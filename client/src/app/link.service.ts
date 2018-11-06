import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Link} from './link';

@Injectable()
export class LinkService {

  constructor(private http: HttpClient) {
  }

  get(): Promise<Link[]> {
    return Promise.resolve([
      {id: 1, source: 1, target: 2, type: '0'}
    ]);
  }
}

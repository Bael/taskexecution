import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Link} from './link';
import {Settings} from './settings';

@Injectable()
export class LinkService {

  private linkUrl = '/api/links';

  constructor(private http: HttpClient) {
  }

  get(): Promise<Link[]> {

    return this.http.get<Link[]>(Settings.baseUrl + 'links').toPromise();

    // return Promise.resolve([
    //   {id: 1, source: 1, target: 2, type: '0'}
    // ]);
  }

  insert(link: Link): Promise<Link> {
    return this.http.post<Link>(this.linkUrl, link)
      .toPromise();

  }

  update(link: Link): Promise<void> {
    return this.http.put<void>(`${this.linkUrl}/${link.id}`, link)
      .toPromise();

  }

  remove(id: number): Promise<void> {
    return this.http.delete<void>(`${this.linkUrl}/${id}`).toPromise();

  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthorizationService {

  constructor(private http: HttpClient) {}

  public getAuthorization(params: any) {
    return this.http.get(`${environment.SCHEDULING_WS}/security/authorization`, { params }).toPromise();
  }
}

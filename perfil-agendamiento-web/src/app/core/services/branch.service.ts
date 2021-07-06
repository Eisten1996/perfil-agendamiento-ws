import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Branch } from '../models/branch.model';

@Injectable({
  providedIn: 'root'
})
export class BranchService {

  constructor(private http: HttpClient) {}

  public getBranchList() {
    return this.http
      .get<Branch[]>(`${environment.SCHEDULING_WS}/branches`)
      .toPromise()
      .then(data => 
         {
          data.map(b =>
            b.services?.map((s) => (s.duration = 0)));
            return data;
         }
            
      );
  }

}

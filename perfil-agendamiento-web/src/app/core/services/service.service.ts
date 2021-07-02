import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Service } from '../models/services.model';

@Injectable({
  providedIn: 'root',
})
export class ServiceService {
  constructor(private http: HttpClient) {}

  public getServiceListByBranch(branch_id: string, list: string) {
    return this.http
      .get<Service[]>(
        `${environment.SCHEDULING_WS}/services?branch_id=${branch_id}`
      )
      .toPromise()
      .then((data) => {
        if (list) {
          this.setDurationToServices(list, data);
        } else data.forEach((s) => (s.duration = 0));

        return data;
      });
  }

  private setDurationToServices(list: string, services: Service[]) {
    services.forEach((s) => {
      s.duration = +list
        .split(',')
        .filter((c) => c.split('-')[0] === s.id)
        .map((c) => c.split('-')[1]);
    });
  }
}

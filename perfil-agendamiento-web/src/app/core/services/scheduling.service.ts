import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { CounterBooking } from '../models/counter_booking.model';
import { Scheduling } from '../models/scheduling.model';

@Injectable({
  providedIn: 'root',
})
export class SchedulingService {
  constructor(private http: HttpClient) {}

  public getSchedulingList() {
    return this.http
      .get<Scheduling[]>(`${environment.SCHEDULING_WS}/schedulings`)
      .toPromise();
  }

  public getSchedulingById(scheduling_id: string) {
    return this.http
    .get<Scheduling>(`${environment.SCHEDULING_WS}/schedulings/${scheduling_id}`)
    .toPromise();
  }

  public saveScheduling(scheduling: Scheduling) {
    return this.http
      .post<any>(`${environment.SCHEDULING_WS}/schedulings`, scheduling)
      .toPromise();
  }

  public deleteScheduling(scheduling_id: number) {
    return this.http
    .delete<any>(`${environment.SCHEDULING_WS}/schedulings/${scheduling_id}`)
    .toPromise();
  }

  public saveCounterBookings(counterBooking: CounterBooking) {
    return this.http
      .post<any>(`${environment.SCHEDULING_WS}/schedulings/saveListTypeScheduling`, counterBooking)
      .toPromise();
  }
}

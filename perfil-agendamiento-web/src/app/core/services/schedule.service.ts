import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Schedule } from '../models/schedule.model';

@Injectable({
  providedIn: 'root',
})
export class ScheduleService {
  constructor(private http: HttpClient) {}

  public getScheduleList(schedulingId: number) {
    return this.http
      .get<Schedule[]>(
        `${environment.SCHEDULING_WS}/schedules?scheduling_id=${schedulingId}`
      )
      .toPromise();
  }

  public filterOnlyCurrentDates(schedules: Schedule[]) {
    const date = new Date();
    let d = new Date();
    date.setHours(0, 0, 0, 0);
    let schedulesFiltered = schedules
      .filter((s) => new Date(s.start!) >= date);

    return schedulesFiltered;
  }

  public saveSchedules(schedules: Schedule[], scheduling_id: number) {
    schedules = this.filterOnlyCurrentDates(schedules).map((s) => {
      s.start = s.start?.split(' ')[1];
      s.end = s.end?.split(' ')[1];
      return s;
    });
    return this.http
      .post<any>(`${environment.SCHEDULING_WS}/schedules?scheduling_id=${scheduling_id}`, schedules)
      .toPromise();
  }

  public getSchedulesByFilters(
    schedules: Schedule[],
    counter_id: string,
    counter_type_id: string,
    booking_type: string
  ) {
    let list = schedules.filter(
      (s) =>
        s.bookingType === booking_type &&
        s.counterId === counter_id &&
        s.counterTypeId === counter_type_id
    );

    return list;
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { BookingTypeName } from '../models/booking_type.model';
import { Counter } from '../models/counter.model';
import { CounterType } from '../models/counter_type.model';

@Injectable({
  providedIn: 'root',
})
export class CounterService {
  constructor(private http: HttpClient) {}

  public getCounterTypeList(branch_id: string, schedulingId: number) {
    return this.http
      .get<CounterType[]>(
        `${environment.SCHEDULING_WS}/counters/types?branch_id=${branch_id}`
      )
      .toPromise()
      .then((counter) => {
        counter.map((c) => {
          if (!c.bookingType) c.bookingType = 'N';
          c.schedulingId = schedulingId;
          c.nick = `${c.name} - ${this.getBookingTypeName(c.bookingType!)}`;
        });
        return counter;
      });
  }

  public getCounterListByType(id_type_counter: string, branch_id: string) {
    return this.http
      .get<Counter[]>(
        `${environment.SCHEDULING_WS}/counters?id_type_counter=${id_type_counter}&branch_id=${branch_id}`
      )
      .toPromise();
  }

  private getBookingTypeName(bookingType: string) {
    switch (bookingType) {
      case 'N':
        return BookingTypeName.NOT_BOOKING;
      case 'A':
        return BookingTypeName.AGENDAMIENTO;
      case 'C':
        return BookingTypeName.CITA;
      default:
        return BookingTypeName.NOT_BOOKING;
    }
  }
}

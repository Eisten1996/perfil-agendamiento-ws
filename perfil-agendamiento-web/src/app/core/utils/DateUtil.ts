import { DatePipe } from '@angular/common';
import { Day } from '../models/schedule.model';

export class DateUtil {
  public formatYearMonthDay = 'yyyy-MM-dd';
  public formatHourMinutes = 'HH:mm';
  public formatHourMinutesSeconds = 'HH:mm:ss';
  public formatDayName = 'dd {} MMMM';
  public formatStandar = `${this.formatYearMonthDay} ${this.formatHourMinutesSeconds}`;

  constructor(public datepipe: DatePipe) {}

  public getDayName(date: Date): string {
    return Day[date.getDay()];
  }

  public getDayNumber(date: Date): number {
    return date.getDay();
  }

  public getFormatYearMonthDay(date: Date) {
    return this.datepipe.transform(date, this.formatYearMonthDay);
  }

  public getFormatHour(date: Date) {
    return this.datepipe.transform(date, this.formatHourMinutes);
  }

  public getFormatStandar(date: Date) {
    return this.datepipe.transform(date, this.formatStandar);
  }

  public getWeekName(date: Date) {
    return this.datepipe.transform(date, this.formatDayName)!.replace('{}','de');
  }
}

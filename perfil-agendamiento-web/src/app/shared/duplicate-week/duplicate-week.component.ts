import { DatePipe } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AppConfig } from 'src/app/app.config.component';
import { Configuration } from 'src/app/core/models/configuration.model';
import { Schedule } from 'src/app/core/models/schedule.model';
import { DateUtil } from 'src/app/core/utils/DateUtil';

@Component({
  selector: 'app-duplicate-week',
  templateUrl: './duplicate-week.component.html',
  styleUrls: ['./duplicate-week.component.scss'],
})
export class DuplicateWeekComponent implements OnInit {
  public weekName: string;

  public dateUtil: DateUtil;

  public configuration: Configuration;

  public weekList: any[];

  public messageError: string;

  public schedules: Schedule[] = [];

  constructor(
    public dialogRef: MatDialogRef<DuplicateWeekComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private datePipe: DatePipe,
    private config: AppConfig
  ) {
    this.dateUtil = new DateUtil(this.datePipe);
    this.configuration = this.config.configuration;
  }

  ngOnInit() {

    this.weekName = this.dateUtil.getWeekName(this.data.startWeek)!;

    this.setWeekList();
  }

  setWeekList() {
    this.weekList = this.buildWeekList();
  }

  buildWeekList() {
    let currentWeek = new Date();
    currentWeek.setHours(0, 0, 0, 0);
    let dayOfWeek = currentWeek.getDay();
    let diference = 1 - dayOfWeek;

    currentWeek.setDate(currentWeek.getDate() + diference);

    let weeks = [];
    let i = 0;
    while (i < this.configuration.maxWeeksList!) {
      const weekStatus = this.isWeekFree(currentWeek);

      weeks.push({
        name: this.dateUtil.getWeekName(currentWeek)!,
        date: new Date(currentWeek),
        status: weekStatus,
        checked: false,
      });
      currentWeek.setDate(currentWeek.getDate() + 7);
      i++;
    }
    return weeks;
  }

  isWeekFree(currentWeek: Date) {
    const date = new Date(currentWeek);
    let i = 1;
    const list = this.data.schedules;
    let flag = true;
    while (i <= 7) {
      const exists = list.filter(
        (schedule: any) =>
          schedule.date == this.dateUtil.getFormatYearMonthDay(date)
      );

      if (exists.length !== 0) {
        flag = false;
        break;
      }
      date.setDate(date.getDate() + 1);
      i++;
    }
    return flag;
  }

  close() {
    this.dialogRef.close();
  }

  copy() {
    let weeksSelected = this.weekList.filter((w) => w.checked);

    if (weeksSelected.length > 0) {
      const schedulesWeekSelected = this.getSchedulesWeekSelected();
      let cont = 0;
      schedulesWeekSelected.forEach((w) => {
        if (w.schedules.length == 0) {
          cont++;
        }
      });

      if (cont == schedulesWeekSelected.length) {
        this.messageError = 'No hay horarios para copiar de esta semana';
      } else {
        this.messageError = '';
        this.copyAddWeek(weeksSelected, schedulesWeekSelected);
        this.dialogRef.close({
          action: 'update',
          schedules: this.schedules,
        });
      }
    } else {
      this.messageError = 'Seleccione al menos una semana';
    }
  }

  copyAddWeek(weeksSelected: any[], schedulesWeekSelected: any[]) {
    weeksSelected.forEach((w: any) => {
      const newDate = new Date(w.date);
      let i = 1;
      while (i <= 7) {
        const dateCurrent = this.dateUtil.getFormatYearMonthDay(newDate);

        if (schedulesWeekSelected[i - 1].schedules.length > 0) {
          schedulesWeekSelected[i - 1].schedules.forEach((s: any) => {
            this.addScheduleCopy(dateCurrent, s, i);
          });
        }
        newDate.setDate(newDate.getDate() + 1);
        i++;
      }
    });
  }

  addScheduleCopy(newDate: any, scheduleSelected: any, day: any) {

    const start = scheduleSelected.start.split(' ')[1];
    const end = scheduleSelected.end.split(' ')[1];
    const id = `H${day}${newDate} ${start}${newDate} ${end}`;
    const schedule = {
      identify: id,
      start: `${newDate} ${start}`,
      end: `${newDate} ${end}`,
      day: day,
      date: newDate,
      addDating: scheduleSelected.addDating,
      counterId: scheduleSelected.counterId,
      counterTypeId: scheduleSelected.counterTypeId,
      bookingType: scheduleSelected.bookingType,
      schedulingId: scheduleSelected.schedulingId
    };
    if (newDate != scheduleSelected.date) {
      this.schedules.push(schedule);
    }
  }

  getSchedulesWeekSelected() {
    let schedulesWeekSelected = [];
    const date = new Date(this.data.startWeek);
    const list = this.data.schedules;
    let i = 1;
    while (i <= 7) {
      const dateCurrent = this.dateUtil.getFormatYearMonthDay(date);
      const schedulesCurrent = list.filter((o: any) => o.date == dateCurrent);
      schedulesWeekSelected.push({
        date: dateCurrent,
        schedules: schedulesCurrent,
      });
      date.setDate(date.getDate() + 1);
      i++;
    }
    return schedulesWeekSelected;
  }

  setAll($event: any) {
    if (this.weekList == null) {
      return;
    }
    this.weekList.forEach((t) => {
      if (t.status) t.checked = $event;
    });
  }

  changeWeekSelected(week: any) {
    week.checked = !week.checked;
  }
}

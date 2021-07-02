import { DatePipe } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { Schedule } from 'src/app/core/models/schedule.model';

import { DateUtil } from 'src/app/core/utils/DateUtil';
import { AppConfig } from 'src/app/app.config.component';
import { Configuration } from 'src/app/core/models/configuration.model';
import { FullCalendarComponent } from '@fullcalendar/angular';
import { InputUtil } from 'src/app/core/utils/InputUtil';

@Component({
  selector: 'app-calendar-event',
  templateUrl: './calendar-event.component.html',
  styleUrls: ['./calendar-event.component.scss'],
})
export class CalendarEventComponent implements OnInit {
  public onEdit: boolean;

  public configuration: Configuration;

  public dateUtil: DateUtil;

  public inputUtil: InputUtil;

  public dateSelected: any;

  public dayName: string;

  public schedule: Schedule = {};

  public minDate = new Date();

  public startHour: string;
  public startMinute: string;

  public endHour: string;
  public endMinute: string;
  public calendar: FullCalendarComponent;

  public messageError: string;

  public scheduleIdAux: string;

  constructor(
    public dialogRef: MatDialogRef<CalendarEventComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public datepipe: DatePipe,
    private config: AppConfig
  ) {
    this.dateUtil = new DateUtil(this.datepipe);
    this.inputUtil = new InputUtil();
    this.configuration = this.config.configuration;
    this.onEdit = this.data.action == 'edit';
  }

  ngOnInit(): void {
    this.calendar = this.data.calendar;
    !this.onEdit ? this.newSchedule() : this.editSchedule();
  }

  private newSchedule() {
    this.loadSchedule(this.data.date, undefined!, 0);
  }

  private editSchedule() {
    this.loadSchedule(this.data.start, this.data.end, this.data.addDating);
  }

  private loadSchedule(start: Date, end: Date, addDating: number) {
    if (this.onEdit) {
      this.schedule.identify = this.data.event.extendedProps.identify;
      this.schedule.end = this.dateUtil.getFormatHour(end)!;
    }
    this.schedule.bookingType = this.data.bookingType;
    this.schedule.counterTypeId = this.data.counterTypeId;
    this.schedule.schedulingId = this.data.schedulingId;
    this.schedule.counterId = this.data.counterId;
    this.schedule.day = this.dateUtil.getDayNumber(start);
    this.dayName = this.dateUtil.getDayName(start);
    this.dateSelected = start;
    this.schedule.start = this.dateUtil.getFormatHour(start)!;
    this.startHour = this.schedule.start.split(':')[0]!;
    this.startMinute = this.schedule.start.split(':')[1]!;
    this.endHour = end ? this.schedule.end!.split(':')[0]! : '23';
    this.endMinute = end ? this.schedule.end!.split(':')[1]! : '00';
    this.schedule.addDating = addDating;
  }

  public changeDateInput() {
    this.dayName = this.dateUtil.getDayName(this.dateSelected.toDate());
    this.schedule.day = this.dateUtil.getDayNumber(this.dateSelected.toDate());
    this.schedule.date = this.dateUtil.getFormatStandar(
      this.dateSelected.toDate()
    )!;
  }

  close() {
    this.dialogRef.close();
  }

  accept() {
    this.buildSchedule();
    if (this.isValidated()) {
      if (this.onEdit) this.data.event.remove();
      if (!this.isOverlapping(this.schedule)) {
        this.calendar.getApi().addEvent(this.schedule);
        this.dialogRef.close({
          action: 'update',
          schedule: this.schedule,
          lastId: this.scheduleIdAux,
        });
      } else {
        if (this.onEdit) this.calendar.getApi().addEvent(this.data.event);
        this.schedule.date = undefined;
        this.messageError = 'Horarios no se pueden cruzar';
      }
    } else {
      this.schedule.date = undefined;
    }
  }

  delete() {
    this.data.event.remove();
    this.dialogRef.close({ action: 'delete', schedule: this.schedule });
  }

  private buildSchedule() {
    this.schedule.date = !this.schedule.date
      ? this.dateUtil.getFormatYearMonthDay(this.dateSelected)!
      : this.dateUtil.getFormatYearMonthDay(this.dateSelected.toDate())!;

    if (this.startMinute != null && this.startMinute.length == 2)
      this.startMinute =
        +this.startMinute < 10
          ? `0${+this.startMinute}`
          : (+this.startMinute).toString();

    if (this.endMinute != null && this.endMinute.length == 2)
      this.endMinute =
        +this.endMinute < 10
          ? `0${+this.endMinute}`
          : (+this.endMinute).toString();

    this.schedule.start = `${this.schedule.date} ${this.startHour}:${this.startMinute}:00`;
    this.schedule.end = `${this.schedule.date} ${this.endHour}:${this.endMinute}:00`;
    this.scheduleIdAux = this.schedule.identify!;
    this.schedule.identify = `H${this.schedule.day}${this.schedule.start}${this.schedule.end}`;
  }

  public changeMinute(o: string) {
    if (o == 'start' && this.startMinute != null) {
      this.startMinute =
        +this.startMinute < 10
          ? `0${+this.startMinute}`
          : (+this.startMinute).toString();
    } else if (o == 'end' && !isNaN(+this.endMinute)) {
      this.endMinute =
        +this.endMinute < 10
          ? `0${+this.endMinute}`
          : (+this.endMinute).toString();
    }
  }

  public changeHour(o: string) {
    if (o == 'start' && this.startHour != null) {
      this.startHour =
        +this.startHour < 10
          ? `0${+this.startHour}`
          : (+this.startHour).toString();
    } else if (o == 'end' && !isNaN(+this.endHour)) {
      this.endHour =
        +this.endHour < 10 ? `0${+this.endHour}` : (+this.endHour).toString();
    }
  }

  public isOverlapping(event: any) {
    var array = this.calendar.getApi().getEvents();
    let i = 0;
    let size = array.length;
    while (i < size) {
      if (array[i].extendedProps.identify != event.identify) {
        let start = array[i].start;
        let end = array[i].end;
        if (!(start! >= new Date(event.end) || end! <= new Date(event.start))) {
          return true;
        }
      }
      i++;
    }
    return false;
  }

  private isValidated() {
    if (this.schedule.start?.includes('null')) {
      this.messageError = 'LDebe Ingresar Formato Hora Inicio Correcto HH:mm';
      return false;
    }
    if (this.schedule.end?.includes('null')) {
      this.messageError = 'LDebe Ingresar Formato Hora Fin Correcto HH:mm';
      return false;
    }
    if (+this.startHour > 23 || +this.endHour > 23) {
      this.messageError = 'La Hora máxima a ingresar es 23';
      return false;
    }

    if (+this.startMinute > 59 || +this.endMinute > 59) {
      this.messageError = 'El minuto máximo a ingresar es 59';
      return false;
    }
    if (+this.startMinute % 5 !== 0 || +this.endMinute % 5 !== 0) {
      this.messageError = `Minuto Inicio debe ser múltiplo de ${this.configuration.durationService}`;
      return false;
    }
    if (+this.endMinute % 5 !== 0 || +this.endMinute % 5 !== 0) {
      this.messageError = `Minuto Fin debe ser múltiplo de ${this.configuration.durationService}`;
      return false;
    }
    if (new Date(this.schedule.start!) >= new Date(this.schedule.end!)) {
      this.messageError = `Hora Fin debe ser mayor a Hora Inicio`;
      return false;
    }

    if (this.schedule.addDating == null) {
      this.messageError = `Ingresar la Cantidad Citas Adicionales`;
      return false;
    }
    if (+this.schedule.addDating > this.configuration.maxAddDating!) {
      this.messageError = `Cantidad Citas Adicionales debe ser como máximo ${this.configuration.maxAddDating}`;
      return false;
    }
    return true;
  }
}

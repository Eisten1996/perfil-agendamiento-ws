import {
  AfterViewInit,
  Component,
  Input,
  OnInit,
  SimpleChanges,
  ViewChild,
} from '@angular/core';
import { CalendarOptions, FullCalendarComponent } from '@fullcalendar/angular';
import esLocale from '@fullcalendar/core/locales/es';
import { DatePipe } from '@angular/common';
import { Draggable } from '@fullcalendar/interaction';
import { MatDialog } from '@angular/material/dialog';
import { CalendarEventComponent } from '../calendar-event/calendar-event.component';
import { Day, Schedule } from 'src/app/core/models/schedule.model';
import { Configuration } from 'src/app/core/models/configuration.model';
import { Counter } from 'src/app/core/models/counter.model';
import { CounterType } from 'src/app/core/models/counter_type.model';
import { ToastService } from 'src/app/core/services/toast.service';
import {
  BookingType,
  BookingTypeId,
} from 'src/app/core/models/booking_type.model';
import { ScheduleService } from 'src/app/core/services/schedule.service';
import { DateUtil } from 'src/app/core/utils/DateUtil';
import { DuplicateWeekComponent } from '../duplicate-week/duplicate-week.component';
import moment from 'moment';
@Component({
  selector: 'app-calendar-main',
  templateUrl: './calendar-main.component.html',
  styleUrls: ['./calendar-main.component.scss'],
})
export class CalendarMainComponent implements OnInit, AfterViewInit {
  @ViewChild('calendar') calendarComponent: FullCalendarComponent;

  BookingTypeId: BookingTypeId;

  @Input()
  public schedulingId: number;

  @Input()
  public schedules: Schedule[] = [];

  @Input()
  public bookingTypeSelected: BookingType;

  @Input()
  public counterSelected: Counter;

  @Input()
  public counterTypeSelected: CounterType;

  public dateUtil: DateUtil;

  public static dateOptions = {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
  } as const;
  constructor(
    public datepipe: DatePipe,
    private dialog: MatDialog,
    private toastService: ToastService,
    private scheduleService: ScheduleService
  ) {
    this.dateUtil = new DateUtil(this.datepipe);
  }

  ngOnInit(): void {}

  ngAfterViewInit() {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes.counterSelected?.currentValue) {
      let events = this.scheduleService.getSchedulesByFilters(
        this.schedules,
        this.counterSelected.id!,
        this.counterTypeSelected.id!,
        this.bookingTypeSelected.id!
      );

      this.addEventsOnCalendar(events);
    } else if (changes.bookingTypeSelected?.currentValue) {
    } else if (this.calendarComponent) {
      this.addEventsOnCalendar([]);
    }
  }

  calendarOptions: CalendarOptions = {
    initialView: 'timeGridWeek',

    height: 500,
    locale: esLocale,
    allDaySlot: false,
    droppable: true,
    editable: true,
    slotLabelFormat: {
      hour: 'numeric',
      minute: '2-digit',
      omitZeroMinute: false,
      hour12: false,
    },
    eventTimeFormat: { hour: '2-digit', minute: '2-digit', hour12: false },
    eventResize: this.resizeSchedule.bind(this),
    eventClick: this.editSchedule.bind(this),
    dateClick: this.addSchedule.bind(this),
    eventDrop: this.dropSchedule.bind(this),
    eventOverlap: function (stillEvent, movingEvent: any) {
      return stillEvent.allDay && movingEvent.allDay;
    },
    schedulerLicenseKey: 'GPL-My-Project-Is-Open-Source',
    themeSystem: 'bootstrap',
    headerToolbar: {
      right: 'prev,next today',
      center: 'title',
      left: '',
    },
    firstDay: 1,
  };

  private buildScheduleByEvent(schedule: any) {
    const s: Schedule = {
      schedulingId: this.schedulingId,
      counterTypeId: this.counterTypeSelected.id,
      bookingType: this.bookingTypeSelected.id,
      counterId: this.counterSelected.id,
      day: schedule.event.extendedProps.day,
      addDating: schedule.event.extendedProps.addDating,
      date: this.dateUtil.getFormatYearMonthDay(schedule.event.start)!,
      start: this.dateUtil.getFormatStandar(schedule.event.start)!,
      end: this.dateUtil.getFormatStandar(schedule.event.end)!,
    };
    return s;
  }

  private validateDay(start: Date, end: Date) {
    const dayStart = this.dateUtil.getDayNumber(start);
    const dayEnd = this.dateUtil.getDayNumber(end);

    return dayStart == dayEnd;
  }

  private updateSchedule(schedule: any, isDrop: boolean) {
    if (this.validateDay(schedule.event.start, schedule.event.end)) {
      const index = this.findIndexScheduleById(
        schedule.event.extendedProps.identify
      );
      const s = this.buildScheduleByEvent(schedule);
      if (isDrop) s.day = this.dateUtil.getDayNumber(schedule.event.start);

      s.identify = `H${s.day}${s.start}${s.end}`;
      schedule.event.remove();
      this.calendarComponent.getApi().addEvent(s);
      this.schedules[index] = s;
    } else {
      schedule.revert();
    }
  }

  public dropSchedule(schedule: any) {
    this.updateSchedule(schedule, true);
  }

  public resizeSchedule(schedule: any) {
    this.updateSchedule(schedule, false);
  }

  public editSchedule(schedule: any) {
    let addAditional = schedule.event.extendedProps.addDating;
    let day = schedule.event.extendedProps.day;
    let start = schedule.event.start;
    let end = schedule.event.end;

    const dialog = this.eventDialogEdit(
      'edit',
      start,
      end,
      day,
      addAditional,
      schedule.event
    );
    dialog.afterClosed().subscribe((data) => {
      if (data?.action === 'update') {
        const index = this.findIndexScheduleById(data.lastId);
        this.schedules[index] = data.schedule;
      }

      if (data?.action === 'delete') {
        const index = this.findIndexScheduleById(data.schedule.identify);
        if (index != -1) this.schedules.splice(index, 1);
      }
    });
  }

  public addSchedule(schedule: any) {
    if (this.counterSelected) {
      const dialog = this.eventDialogAdd('create', schedule.date);
      dialog.afterClosed().subscribe((data) => {
        if (data) {
          this.schedules.push(data.schedule);
        }
      });
    } else {
      this.showMessage();
    }
  }

  public eventDialogAdd(action_title: string, start: Date) {
    return this.dialog.open(CalendarEventComponent, {
      width: '420px',
      data: {
        action: action_title,
        confirmMessageButton: 'Guardar',
        cancelButton: true,
        date: start,
        schedulingId: this.schedulingId,
        counterId: this.counterSelected.id,
        counterTypeId: this.counterTypeSelected.id,
        bookingType: this.bookingTypeSelected.id,
        calendar: this.calendarComponent,
      },
    });
  }

  public eventDialogEdit(
    action: string,
    start: Date,
    end: Date,
    day: number,
    addDating: number,
    event: any
  ) {
    return this.dialog.open(CalendarEventComponent, {
      width: '420px',

      data: {
        action: action,
        confirmMessageButton: 'Guardar',
        cancelButton: true,
        start: start,
        end: end,
        day: day,
        addDating: addDating,
        schedulingId: this.schedulingId,
        counterId: this.counterSelected.id,
        counterTypeId: this.counterTypeSelected.id,
        bookingType: this.bookingTypeSelected.id,
        calendar: this.calendarComponent,
        event: event,
      },
    });
  }

  public addEventsOnCalendar(events: any) {
    this.calendarComponent.getApi().removeAllEvents();
    this.calendarComponent.getApi().addEventSource(events);
  }

  private findIndexScheduleById(identify: string) {
    return this.schedules.findIndex((o) => o.identify == identify);
  }

  private showMessage() {
    this.toastService.openSnackBar(
      `Por favor seleccione un${
        this.bookingTypeSelected?.id === BookingTypeId.CITA
          ? ' Asesor'
          : 'a Ventanilla'
      }`,
      'info',
      'fail'
    );
  }

  public copyWeek() {
    const currentDate = this.calendarComponent.getApi().getDate();
    const startWeek = moment(currentDate).startOf('isoWeek').toDate();

    const dialog = this.dialog.open(DuplicateWeekComponent, {
      width: '420px',

      data: {
        startWeek: startWeek,
        schedules: this.scheduleService.getSchedulesByFilters(
          this.schedules,
          this.counterSelected.id!,
          this.counterTypeSelected.id!,
          this.bookingTypeSelected.id!
        ),
      },
    });

    dialog.afterClosed().subscribe((data) => {
      if (data) {
        if (data.schedules) {
          data.schedules.forEach((s: any) => this.schedules.push(s));
          this.calendarComponent.getApi().addEventSource(data.schedules);
        }
      }
    });
  }
}

import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  SimpleChanges,
} from '@angular/core';
import { BranchType } from 'src/app/core/models/branch.model';
import { CounterBooking } from 'src/app/core/models/counter_booking.model';
import { CounterType } from 'src/app/core/models/counter_type.model';
import { Scheduling } from 'src/app/core/models/scheduling.model';
import {
  BookingType,
  BookingTypeId,
  BookingTypeName,
} from 'src/app/core/models/booking_type.model';
import { CounterService } from 'src/app/core/services/counter.service';
import { DialogComponent } from '../../dialog/dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { Counter } from 'src/app/core/models/counter.model';
import { AsesorService } from 'src/app/core/services/asesor.service';
import { ScheduleType } from 'src/app/core/models/schedule_type.mode';
import { Configuration } from 'src/app/core/models/configuration.model';
import { Schedule } from 'src/app/core/models/schedule.model';
import { ScheduleService } from 'src/app/core/services/schedule.service';

@Component({
  selector: 'app-schedules',
  templateUrl: './schedules.component.html',
  styleUrls: ['./schedules.component.scss'],
})
export class SchedulesComponent implements OnInit {
  @Input()
  public onEdit: boolean;

  @Input()
  public scheduling: Scheduling;

  @Output()
  public changeCounterTypeEmmiter: EventEmitter<Scheduling> = new EventEmitter();

  public counterSelected: Counter;

  public counterTypeSelected: CounterType;

  public bookingTypeSelected: BookingType;

  public schedules: Schedule[];

  public bookingTypes: BookingType[] = [];

  public counterTypes: CounterType[];

  public scheduleTypeList: ScheduleType[];

  public schedulesByCounterSelected: Schedule[] = [];

  public counterBooking: CounterBooking;

  public BookingTypeId = BookingTypeId;

  public changingBookingType: boolean = false;

  constructor(
    private counterService: CounterService,
    private scheduleService: ScheduleService,
    private asesorService: AsesorService,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {}

  async getCounterTypeList() {
    this.counterTypes = await this.counterService.getCounterTypeList(
      this.scheduling?.branchId!,
      this.scheduling.id!
    );
  }

  async getScheduleList() {
    this.schedules = await this.scheduleService.getScheduleList(
      this.scheduling.id!
    );
    this.schedules.forEach((s) => (s.identify = s.id))!;
  }

  async ngOnChanges(changes: SimpleChanges) {
    console.log(changes);

    if (changes.scheduling.currentValue) {
      this.scheduling = changes?.scheduling.currentValue;
      await this.getCounterTypeList();
      await this.getScheduleList();

      this.buildSchedulingType();
    }
  }

  private buildSchedulingType() {
    this.bookingTypes.push(this.buildTypes('N', BookingTypeName.NOT_BOOKING));
    this.bookingTypes.push(this.buildTypes('C', BookingTypeName.CITA));
    if (this.scheduling.typeAttention === BranchType.PRESENCIAL)
      this.bookingTypes.push(
        this.buildTypes('A', BookingTypeName.AGENDAMIENTO)
      );
  }

  public changeCounterType() {
    if (this.counterTypeSelected) {
      this.bookingTypeSelected = this.bookingTypes?.find(
        (t) => t.id === this.counterTypeSelected?.bookingType
      )!;
      this.changeCounterTypeConfirmed(this.counterTypeSelected.bookingType!);
    } else {
      this.bookingTypeSelected = undefined!;
    }
  }

  async getScheduleTypeList(counterTypeId: string, branchId: string) {
    this.changingBookingType = true;
    if (this.bookingTypeSelected.id === BookingTypeId.CITA) {
      this.scheduleTypeList = await this.asesorService.getAsesorList(
        counterTypeId,
        branchId
      );
    } else if (this.bookingTypeSelected.id === BookingTypeId.AGENDAMIENTO) {
      this.scheduleTypeList = await this.counterService.getCounterListByType(
        counterTypeId,
        branchId
      );
    }

    this.counterSelected = undefined!;
  }

  public changeBookingType() {
    console.log(this.onEdit);

    if (this.counterTypeSelected.bookingType === BookingTypeId.NOT_BOOKING) {
      this.changingBookingType = true;
      this.setBookingTypeToCounterType();
      this.getScheduleTypeList(
        this.counterTypeSelected.id!,
        this.scheduling.branchId!
      );
    } else {
      if (this.onEdit) this.cancelBookings();
      else {
        this.setBookingTypeToCounterType();
        this.changeCounterTypeConfirmed(this.bookingTypeSelected.id!);
      }
    }
  }

  public changeCounter() {
    console.log('test');
  }

  private setBookingTypeToCounterType() {
    this.counterTypeSelected.nick = `${this.counterTypeSelected.name} - ${this.bookingTypeSelected.name}`;
    this.counterTypeSelected.bookingType = this.bookingTypeSelected.id;
  }

  private buildTypes(id: string, name: string) {
    return {
      id,
      name,
    };
  }

  public cancelBookings() {
    const dialogRef = this.confirmDialog(
      '¿Está seguro de realizar este cambio?',
      undefined!,
      'Se cancelarán las reservas registradas para este tipo de ventanilla.',
      true
    );

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.changeCounterTypeEmmiter.emit(this.scheduling);
        this.setBookingTypeToCounterType();
        this.changeCounterTypeConfirmed(this.bookingTypeSelected.id!);
      } else {
        this.bookingTypeSelected = this.bookingTypes.find(
          (b) => this.counterTypeSelected.bookingType === b.id
        )!;
      }
    });
  }

  private changeCounterTypeConfirmed(typeId: string) {
    if (typeId !== BookingTypeId.NOT_BOOKING) {
      this.getScheduleTypeList(
        this.counterTypeSelected.id!,
        this.scheduling.branchId!
      );
    } else {
      this.changingBookingType = false;
      this.cleanCounters();
    }
  }

  private cleanCounters() {
    this.counterSelected = undefined!;
    this.scheduleTypeList = [];
  }

  public confirmDialog(
    title: string,
    list: any[],
    message: string,
    cancelButton: boolean
  ) {
    return this.dialog.open(DialogComponent, {
      width: '500px',

      data: {
        title: title,
        list: list,
        message: message,
        confirmMessageButton: 'Aceptar',
        cancelButton: cancelButton,
      },
    });
  }

  public save() {
    let listFiltered = this.scheduleService.filterOnlyCurrentDates(
      this.schedules
    );
    let counterTypesWithoutSchedules: any[] = [];
    this.counterTypes
      .filter((c) => c.bookingType != BookingTypeId.NOT_BOOKING)
      .forEach((c) => {
        let list = listFiltered.filter(
          (s) => s.bookingType === c.bookingType && s.counterTypeId === c.id
        );

        if (list.length == 0) counterTypesWithoutSchedules.push(c);
      });

    if (counterTypesWithoutSchedules.length == 0) {
      return true;
    } else {
      this.showCounterTypesWithoutSchedules(counterTypesWithoutSchedules);
      return false;
    }
  }

  private showCounterTypesWithoutSchedules(list: any[]) {
    this.confirmDialog(
      'No podrá guardar, debido a que los tipos de ventanilla:',
      list,
      `no tienen configurado horarios, se sugiere configurar los mismos, ó en su defecto configurar los tipo de ventanilla mencionados, como tipo de reserva NO PERMITE RESERVA`,
      false
    );
  }
}

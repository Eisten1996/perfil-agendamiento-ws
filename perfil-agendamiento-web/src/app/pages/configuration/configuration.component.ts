import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { AppConfig } from 'src/app/app.config.component';
import { Branch, BranchType } from 'src/app/core/models/branch.model';
import { Configuration } from 'src/app/core/models/configuration.model';
import { CounterBooking } from 'src/app/core/models/counter_booking.model';
import { Scheduling } from 'src/app/core/models/scheduling.model';
import { Stepper } from 'src/app/core/models/stepper.model';
import { BranchService } from 'src/app/core/services/branch.service';
import { ScheduleService } from 'src/app/core/services/schedule.service';
import { SchedulingService } from 'src/app/core/services/scheduling.service';
import { ToastService } from 'src/app/core/services/toast.service';
import { DialogComponent } from 'src/app/shared/dialog/dialog.component';
import { SchedulesComponent } from 'src/app/shared/schedules/schedules/schedules.component';
import { InformationComponent } from 'src/app/shared/scheduling-information/information/information.component';

@Component({
  selector: 'app-configuration',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.scss'],
})
export class ConfigurationComponent implements OnInit {
  public schedulingId: string;

  public onEdit: boolean;

  public selected: number = 1;

  public loading: boolean = true;

  public branches: Branch[];

  public configuration: Configuration;

  public scheduling: Scheduling;

  public preScheduling: Scheduling;

  public steppers: Stepper[] = [
    {
      step: 1,
      title: 'Configuración General',
      selected: true,
    },
    { step: 2, title: 'Configuración de Horarios', selected: false },
  ];

  public counterBooking: CounterBooking;

  @ViewChild('information') information: InformationComponent;
  @ViewChild('schedules') schedules: SchedulesComponent;

  constructor(
    private branchService: BranchService,
    private schedulingService: SchedulingService,
    private scheduleService: ScheduleService,
    private config: AppConfig,
    public dialog: MatDialog,
    private rout: ActivatedRoute,
    private router: Router,
    private toastService: ToastService
  ) {
    this.configuration = this.config.configuration;
  }

  ngOnInit(): void {
    this.init();
  }

  public cancel() {
    if (!this.loading) {
      const dialogRef = this.confirmDialog(
        '¿Está seguro de realizar esta acción?',
        'Perderá todos los cambios configurados en esta sección.',
        true
      );

      dialogRef.afterClosed().subscribe((result) => {
        if (result) this.router.navigate(['']);
      });
    }
  }

  public save($event: any, flag: boolean) {
    const dialogRef = this.confirmDialog(
      '¿Está seguro de cambiar de pestaña?',
      'Los cambios realizados serán guardados.',
      true
    );

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        if (flag) this.saveScheduling($event, 'continue');
        else this.saveCounterBooking('back');
      }
    });
  }

  public bookingsCanceled() {
    this.confirmDialog(
      'El cambio se ha realizado con éxito',
      'Se enviará un correo electrónico indicando la cancelación a todos los usuarios con reservas registradas para este tipo de ventanilla.',
      false
    );
  }

  public confirmDialog(title: string, message: string, cancelEnabled: boolean) {
    return this.dialog.open(DialogComponent, {
      width: '500px',
      data: {
        title: title,
        message: message,
        confirmMessageButton: 'Aceptar',
        cancelButton: cancelEnabled,
      },
    });
  }

  private init() {
    this.schedulingId = this.rout.snapshot.params['id'];
    if (this.schedulingId) this.onEdit = true;

    if (!this.onEdit) this.getBranchList();
    else this.getScheduling();
  }

  async nextStepButton($event: any) {
    if (!this.loading) {
      if ($event == 2) {
        if (this.information.save()) {
          if (!this.isSchedulingEqual(this.preScheduling, this.scheduling)) {
           
            this.save(this.information.scheduling, true);
          } else {
            // this.loading = true;
            this.selected = 2;
            this.setSelectedStep(this.selected);
          }
         
        }
      } else {
        if (this.schedules.save()) {
          this.save(null, false);
        }
      }
    }
  }

  private setSelectedStep(step: number) {
    this.steppers.forEach((s) => {
      if (s.step == step) {
        s.selected = true;
      } else {
        s.selected = false;
      }
    });
  }

  async getBranchList() {
    this.branches = await this.branchService.getBranchList();
    this.loading = false;
  }

  async getScheduling() {

    this.scheduling = await this.schedulingService.getSchedulingById(
      this.schedulingId
    );
    this.preScheduling = JSON.parse(JSON.stringify(this.scheduling));

    this.loading = false;
  }

  public schedulingEmitter($event: any) {
    this.scheduling = $event;
    this.save($event, true);
  }

  async saveScheduling($event: any, next: string) {
    this.scheduling = $event;
    this.loading = true;
    this.scheduling.id = await this.schedulingService.saveScheduling(
      this.scheduling
    );
    this.loading = false;
    this.showMessageSaved();
    if (next == 'continue') {
      this.preScheduling = JSON.parse(JSON.stringify(this.scheduling));
      this.selected = 2;
      this.setSelectedStep(this.selected);
    } else this.router.navigate(['']);
  }

  async acceptButton() {
    if (!this.loading) {
      if (this.selected == 1) {
        if (this.information.save()) {
          this.saveScheduling(this.information.scheduling, 'back');
        }
      } else if (this.selected == 2) {
        if (this.schedules.save()) {
          this.saveCounterBooking('finish');
        }
      }
    }
  }

  private showMessageSaved() {
    this.toastService.openSnackBar(this.getMessageSuccess(), 'done', 'success');
  }

  private getMessageSuccess() {
    return `Perfil de agendamiento ${
      this.onEdit ? 'guardado' : 'creado'
    } correctamente`;
  }

  async saveCounterBooking(next: string) {
    this.loading = true;
    this.counterBooking = {
      branchId: this.scheduling.branchId,
      bookingTypeList: this.schedules.counterTypes,
    };
    await this.schedulingService.saveCounterBookings(this.counterBooking);
    await this.scheduleService.saveSchedules(
      this.schedules.schedules,
      this.scheduling.id!
    );
    this.showMessageSaved();
    if (next === 'back') {
      this.selected = 1;
      this.setSelectedStep(this.selected);
    } else {
      this.router.navigate(['']);
    }
    this.loading = false;
  }

  changeCounterTypeEmmiter($event: any) {
    this.loading = true;
    this.bookingsCanceled();
    this.loading = false;
  }

  isSchedulingEqual(pre: Scheduling, post: Scheduling) {
    return (
      pre != undefined &&
      pre.minDays === post.minDays &&
      pre.maxDays === post.maxDays &&
      pre.toleranceTime === post.toleranceTime &&
      pre.multipleBookings === post.multipleBookings &&
      pre.confirmEmail === post.confirmEmail &&
      pre.confirmTime === post.confirmTime &&
      pre.unidConfirmTime === post.unidConfirmTime &&
      pre.services === post.services
    );
  }
}

import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  SimpleChanges,
} from '@angular/core';
import { Branch } from 'src/app/core/models/branch.model';
import { Configuration } from 'src/app/core/models/configuration.model';
import { Scheduling } from 'src/app/core/models/scheduling.model';
import { Service } from 'src/app/core/models/services.model';
import { ServiceService } from 'src/app/core/services/service.service';
import { InputUtil } from 'src/app/core/utils/InputUtil';

@Component({
  selector: 'app-information',
  templateUrl: './information.component.html',
  styleUrls: ['./information.component.scss'],
})
export class InformationComponent implements OnInit {
  @Input()
  public scheduling: Scheduling;

  @Input()
  public branches: Branch[];

  @Input()
  public configuration: Configuration;

  @Output()
  public schedulingEmitter: EventEmitter<Scheduling> = new EventEmitter<Scheduling>();

  public services: Service[];

  public branchSelected: Branch;

  public loading: boolean;

  public loadingEdit: boolean;

  public messageError: string;

  public inputUtil: InputUtil;

  @Input()
  public onEdit: boolean;

  constructor(private serviceService: ServiceService) {
    this.inputUtil = new InputUtil();
  }

  ngOnInit(): void {
    this.init();
  }

  private init() {
    this.loadScheduling();
  }

  async ngOnChanges(changes: SimpleChanges) {
    if (this.onEdit) {
      this.loadingEdit = true;

      if (changes.scheduling?.currentValue) {
        this.scheduling = changes?.scheduling.currentValue;
        this.branchSelected = {
          id: this.scheduling.branchId,
          name: this.scheduling.branchName,
        };
        await this.getServices();
      }
    }
  }

  private loadScheduling() {
    if (!this.scheduling) {
      this.buildScheduling();
    }
  }

  private buildScheduling() {
    this.scheduling = {
      id: 0,
      branchId: '0',
      maxDays: this.configuration.maxDays,
      minDays: 0,
      toleranceTime: this.configuration.minTolerance,
      multipleBookings: 0,
      confirmEmail: 1,
      confirmTime: 30,
      unidConfirmTime: 'm',
    };
  }

  public changeBranch() {
    if (this.branchSelected) {
      this.scheduling.branchId = this.branchSelected.id;
      this.scheduling.branchName = this.branchSelected.name;
      this.services = this.branchSelected.services!;
    } else {
      this.branchSelected = undefined!;
      this.services = undefined!;
    }
  }

  async getServices() {
    if (this.scheduling.branchId !== '0') {
      this.loading = true;
      this.services = await this.serviceService.getServiceListByBranch(
        this.scheduling.branchId!,
        this.scheduling.services!
      );
      this.loading = false;
      this.loadingEdit = false;
    } else {
      this.cleanScheduling();
    }
  }

  public cleanScheduling() {
    this.services = [];
    this.buildScheduling();
  }

  public save() {
    this.buildServices();
    return this.isValidated();
  }

  public saveScheduling() {
    if (this.save()) this.schedulingEmitter.emit(this.scheduling);
  }

  public buildServices() {
    this.scheduling.services = this.services
      ?.filter((s) => s.duration !== 0)
      .map((s) => `${s.id}-${s.duration}`)
      .join();
  }

  public setConfirmEmail($event: any) {
    this.scheduling.confirmEmail = $event.checked ? 1 : 0;
    this.scheduling.confirmTime = this.scheduling.confirmEmail ? 30 : 0;
    this.scheduling.unidConfirmTime = 'm';
  }

  public setConfirmMultipleBookings($event: any) {
    this.scheduling.multipleBookings = $event.checked ? 1 : 0;
  }

  public isValidated() {
    if (!this.scheduling) {
      return false;
    }
    if (this.scheduling.branchId === '0') {
      this.messageError = 'Seleccione una agencia';
      return false;
    }
    if (this.scheduling.maxDays == null) {
      this.messageError = 'Ingrese el Máximo de días para agendar';
      return false;
    }
    if (this.scheduling.minDays == null) {
      this.messageError = 'Ingrese el Mínimo de días para agendar';
      return false;
    }
    if (this.scheduling.maxDays > this.configuration.maxDays!) {
      this.messageError =
        'Máximo de días para agendar debe ser como máximo ' +
        this.configuration.maxDays;
      return false;
    }
    if (this.scheduling.maxDays <= this.scheduling.minDays) {
      this.messageError =
        'Mínimo de días para agendar debe ser menor que el Máximo de días para agendar';
      return false;
    }
    if (this.scheduling.toleranceTime == null) {
      this.messageError = 'Ingresar el tiempo de tolerancia';
      return false;
    }
    if (this.scheduling.toleranceTime > this.configuration.maxTolerance!) {
      this.messageError =
        'Tiempo de Tolerancia debe ser como máximo ' +
        this.configuration.maxTolerance;
      return false;
    }
    if (this.scheduling.toleranceTime < this.configuration.minTolerance!) {
      this.messageError =
        'Tolerancia debe ser como mínimo ' + this.configuration.minTolerance;
      return false;
    }
    if (this.scheduling.confirmEmail == 1) {
      if (this.scheduling.confirmTime == null) {
        this.messageError = 'Ingrese el tiempo máximo para confirmar correo';
        return false;
      }
      if (this.scheduling.confirmTime > this.configuration.maxConfirmTime!) {
        this.messageError =
          'Tiempo para confirmar correo debe ser como máximo ' +
          this.configuration.maxConfirmTime;
        return false;
      }
      if (this.scheduling.confirmTime < this.configuration.minConfirmTime!) {
        this.messageError =
          'Tiempo para confirmar correo debe ser como mínimo ' +
          this.configuration.minConfirmTime;
        return false;
      }
    }
    if (this.scheduling.services?.length === 0) {
      this.messageError =
        'Ingrese el tiempo de atención de al menos un servicio';
      return false;
    }

    if (!this.services) {
      return false;
    }
    if (this.services.length > 0) {
      
      const find = this.services.filter(
        (t) => (t.duration == null || t.duration! % this.configuration.durationService! !== 0)
      );
      if (find.length > 0) {
        this.messageError =
          'Tiempo de atención del servicio debe ser múltiplo de ' +
          this.configuration.durationService;
        return false;
      }
    }
    return true;
  }
}

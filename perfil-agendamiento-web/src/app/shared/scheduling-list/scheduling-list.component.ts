import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Scheduling } from 'src/app/core/models/scheduling.model';
import { SchedulingService } from 'src/app/core/services/scheduling.service';
import { DialogComponent } from '../dialog/dialog.component';

@Component({
  selector: 'app-scheduling-list',
  templateUrl: './scheduling-list.component.html',
  styleUrls: ['./scheduling-list.component.scss'],
})
export class SchedulingListComponent implements OnInit {
  public search: string;

  public schedulings: Scheduling[] = [];

  public schedulingsAll: Scheduling[] = [];

 

  @Output()
  public schedulingLoadedEvent: EventEmitter<boolean> = new EventEmitter<boolean>();

  @Output()
  public schedulingDeleteEvent: EventEmitter<string> = new EventEmitter<string>();

  @Output()
  public schedulingEditEvent: EventEmitter<number> = new EventEmitter<number>();

  constructor(
    private schedulingService: SchedulingService,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getSchedulingList();
  }

  async getSchedulingList() {
    this.schedulingService
      .getSchedulingList()
      .then((data) => {
        this.schedulingsAll = data;
        this.schedulings = this.schedulingsAll;
        this.schedulingLoadedEvent.emit(false);
      })
      .catch(() => {
        this.schedulingLoadedEvent.emit(false);
      });
  }

  public filterByInput() {
    if (this.search === '') this.schedulings = this.schedulingsAll;
    else {
      this.schedulings = this.schedulingsAll.filter((s) =>
        s.branchName
          ?.toLocaleLowerCase()
          .includes(this.search.toLocaleLowerCase())
      );
    }
  }

  public clearFilter() {
    this.search = '';
    this.schedulings = this.schedulingsAll;
  }

  public editScheduling(scheduling_id: number) {
    this.schedulingEditEvent.emit(scheduling_id);
  }

  public deleteScheduling(scheduling_id: number) {
    this.confirmDelete(
      scheduling_id,
      '¿Está seguro de que desea eliminar el Perfil de Agendamiento?',
      ''
    );
  }

  public confirmDelete(
    scheduling_id: number,
    title: string,
    message: string
  ): void {
    const dialogRef = this.dialog.open(DialogComponent, {
      width: '500px',
      data: {
        title: title,
        message: message,
        confirmMessageButton: 'Eliminar',
        cancelButton: true,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) this.delete(scheduling_id);
    });
  }

  async delete(scheduling_id: number) {
    this.schedulingLoadedEvent.emit(true);
    await this.schedulingService.deleteScheduling(scheduling_id);
    this.getSchedulingList();
    this.schedulingDeleteEvent.emit('Perfil de agendamiento eliminado satisfactoriamente');
    this.schedulingLoadedEvent.emit(false);
  }
}

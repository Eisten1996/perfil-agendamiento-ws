import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppConfig } from 'src/app/app.config.component';
import { ToastService } from 'src/app/core/services/toast.service';

@Component({
  selector: 'app-scheduling',
  templateUrl: './scheduling.component.html',
})
export class SchedulingComponent implements OnInit {
  public loading: boolean = true;
  public authorization: any;
  constructor(
    private router: Router,
    private toastService: ToastService,
    private config: AppConfig
  ) {
    this.authorization = this.config.authorization;
  }

  ngOnInit(): void {
  }

  public save() {}

  public schedulingLoadedEvent($event: any) {
    this.loading = $event;
  }

  public schedulingDeleteEvent($event: any) {
    this.toastService.openSnackBar($event, 'done', 'success');
  }

  public schedulingEditEvent($event: any) {
    this.loading = true;
    this.router.navigate([`configuration/${$event}`]);
  }
}

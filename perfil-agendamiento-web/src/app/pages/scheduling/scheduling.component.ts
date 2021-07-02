import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastService } from 'src/app/core/services/toast.service';

@Component({
  selector: 'app-scheduling',
  templateUrl: './scheduling.component.html',
})
export class SchedulingComponent implements OnInit {
  public loading: boolean = true;
  constructor(private router: Router, private toastService: ToastService) {}

  ngOnInit(): void {
    const message = localStorage.getItem('scheduling-success');
    if (message != null)
      this.toastService.openSnackBar(message, 'done', 'success');
    localStorage.removeItem('scheduling-success');
  }

  public save() {}

  public schedulingLoadedEvent($event: any) {
    this.loading = $event;
  }

  public schedulingDeleteEvent($event: any) {
    this.toastService.openSnackBar($event, 'done', 'success');
  }

  public schedulingEditEvent($event: any) {
    this.router.navigate([`configuration/${$event}`]);
  }

}

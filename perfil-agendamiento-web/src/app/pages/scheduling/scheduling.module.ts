import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SchedulingComponent } from './scheduling.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { SchedulingRoutingModule } from './scheduling-routing.module';
import { SchedulingListModule } from 'src/app/shared/scheduling-list/scheduling-list.module';

@NgModule({
  declarations: [SchedulingComponent],
  imports: [
    CommonModule,
    MatIconModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    SchedulingRoutingModule,
    SchedulingListModule
  ],
})
export class SchedulingModule {}

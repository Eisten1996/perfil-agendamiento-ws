import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SchedulesComponent } from './schedules/schedules.component';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DialogModule } from '../dialog/dialog.module';
import { CalendarMainModule } from '../calendar-main/calendar-main.module';



@NgModule({
  declarations: [SchedulesComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    DialogModule,
    CalendarMainModule
  ],
  exports: [SchedulesComponent]
})
export class SchedulesModule { }

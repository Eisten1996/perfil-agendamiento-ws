import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CalendarMainComponent } from './calendar-main.component';
import { FullCalendarModule } from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import resourceTimeGridPlugin from '@fullcalendar/resource-timegrid';
import { CalendarEventModule } from '../calendar-event/calendar-event.module';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { DuplicateWeekModule } from '../duplicate-week/duplicate-week.module';

FullCalendarModule.registerPlugins([
  dayGridPlugin,
  interactionPlugin,
  resourceTimeGridPlugin,
]);

@NgModule({
  declarations: [CalendarMainComponent],
  imports: [
    CommonModule,
    FullCalendarModule,
    CalendarEventModule,
    DuplicateWeekModule,
    MatIconModule,
    MatButtonModule
  ],
  exports: [CalendarMainComponent],
})
export class CalendarMainModule {}

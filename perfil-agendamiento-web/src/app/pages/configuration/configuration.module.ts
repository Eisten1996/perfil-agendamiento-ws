import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConfigurationRoutingModule } from './configuration-routing.module';
import { ConfigurationComponent } from './configuration.component';
import { SchedulingInformationModule } from 'src/app/shared/scheduling-information/scheduling-information.module';
import { StepperModule } from 'src/app/shared/stepper/stepper.module';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatButtonModule } from '@angular/material/button';
import { SchedulesModule } from 'src/app/shared/schedules/schedules.module';
import { DialogModule } from 'src/app/shared/dialog/dialog.module';

@NgModule({
  declarations: [ConfigurationComponent],
  imports: [
    CommonModule,
    ConfigurationRoutingModule,
    SchedulingInformationModule,
    SchedulesModule,
    StepperModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatButtonModule,
    DialogModule
  ],
})
export class ConfigurationModule {}

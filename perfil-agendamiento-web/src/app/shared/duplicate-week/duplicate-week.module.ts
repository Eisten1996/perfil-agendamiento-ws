import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DuplicateWeekComponent } from './duplicate-week.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCheckboxModule } from '@angular/material/checkbox';

@NgModule({
  declarations: [DuplicateWeekComponent],
  imports: [
    CommonModule,
    MatDialogModule,
    MatButtonModule,
    MatIconModule,
    MatCheckboxModule,
  ],
  exports: [DuplicateWeekComponent],
})
export class DuplicateWeekModule {}

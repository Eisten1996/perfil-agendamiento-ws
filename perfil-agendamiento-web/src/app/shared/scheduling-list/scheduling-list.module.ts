import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SchedulingListComponent } from './scheduling-list.component';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  declarations: [SchedulingListComponent],
  imports: [
    CommonModule,
    MatInputModule,
    FormsModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule
  ],
  exports: [SchedulingListComponent],
})
export class SchedulingListModule {}

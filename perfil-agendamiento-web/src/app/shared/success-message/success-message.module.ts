import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SuccessMessageComponent } from './success-message.component';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  declarations: [SuccessMessageComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule
  ],
  exports: [SuccessMessageComponent]
})
export class SuccessMessageModule { }


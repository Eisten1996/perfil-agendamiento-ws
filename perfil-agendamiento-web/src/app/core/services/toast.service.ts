import { Injectable } from '@angular/core';
import { SuccessMessageComponent } from 'src/app/shared/success-message/success-message.component';
import { MatSnackBar } from '@angular/material/snack-bar';
@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor(private _snackBar: MatSnackBar) { }

  openSnackBar(response: string, icon: string, state: string) {
    this._snackBar.openFromComponent(SuccessMessageComponent, {
      data: {
        message: response,
        icon: icon,
      },
      duration: 3500,
      horizontalPosition: 'right',
      verticalPosition: 'top',
      panelClass: [`${state}-snackbar`],
    });
  }
}

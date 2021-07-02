import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpResponse,
  HttpHandler,
  HttpEvent,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { map, catchError, timeout } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SuccessMessageComponent } from '../shared/success-message/success-message.component';

@Injectable()
export class HttpConfigInterceptor implements HttpInterceptor {
  headers = new HttpHeaders({
    'Cache-Control': 'no-cache',
    Pragma: 'no-cache',
  });

  constructor(private _snackBar: MatSnackBar) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next
      .handle(req.clone({  }))
      .pipe(
        timeout(60000),
        catchError((error) => {
          this.openSnackBar('Ha sucedido un error en el servidor', 'info', 'fail');
          return throwError({ ...error, customHttpError: true });
        })
      );
  }

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

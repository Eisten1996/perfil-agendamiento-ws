import {
  APP_INITIALIZER,
  CUSTOM_ELEMENTS_SCHEMA,
  LOCALE_ID,
  NgModule,
} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppConfig } from './app.config.component';
import { DatePipe } from '@angular/common';
import { registerLocaleData } from '@angular/common';
import localeEsPe from '@angular/common/locales/es-PE';
import { HttpConfigInterceptor } from './interceptor/httpconfig.interceptor';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { SuccessMessageModule } from './shared/success-message/success-message.module';

registerLocaleData(localeEsPe);
export function loadConfig(appConfig: AppConfig) {
  return () => appConfig.load();
}

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSnackBarModule,
    HttpClientModule,
    SuccessMessageModule,
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: loadConfig,
      deps: [AppConfig],
      multi: true,
    },
    DatePipe,
    { provide: LOCALE_ID, useValue: 'es-PE' },
    MatSnackBarModule,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpConfigInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AppModule {}

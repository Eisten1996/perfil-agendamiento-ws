import { Inject, Injectable } from '@angular/core';
import { Observable, Observer } from 'rxjs';
import { Configuration } from './core/models/configuration.model';
import { AuthorizationService } from './core/services/authorization.service';
import { ConfigurationService } from './core/services/configuration.service';

@Injectable({
  providedIn: 'root',
})
export class AppConfig {
  public configuration: Configuration;
  public authorization: any;

  constructor(
    private configurationService: ConfigurationService,
    private authorizationService: AuthorizationService
  ) {}

  async load(): Promise<any> {
    const auth = await this.getAuthorization();
    return new Observable((observer: Observer<any>) => {
      this.configurationService.getConfiguration().subscribe((data) => {
        if (auth) {
          this.configuration = data;
        this.observerComplete(observer);
        }
       
      });
    }).toPromise();
  }

  private observerComplete(observer: Observer<any>) {
    observer.next('');
    observer.complete();
  }

  async getAuthorization() {
    const params = { module: 'I20', submodule: 'E0026' };
    console.log('params: ', params);
    this.authorization = await this.authorizationService.getAuthorization(
      params
    );
    return this.authorization;
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Configuration } from '../models/configuration.model';

@Injectable({
  providedIn: 'root',
})
export class ConfigurationService {
  constructor(private http: HttpClient) {}

  public getConfiguration() {
    return this.http.get<Configuration>('configuration.json');
  }
}

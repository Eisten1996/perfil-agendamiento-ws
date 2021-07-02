import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Asesor } from '../models/asesor.model';

@Injectable({
  providedIn: 'root',
})
export class AsesorService {
  constructor(private http: HttpClient) {}

  public getAsesorList(id_type_counter: string, branch_id: string) {
    return this.http
      .get<Asesor[]>(
        `${environment.SCHEDULING_WS}/asesors?id_type_counter=${id_type_counter}&branch_id=${branch_id}`
      )
      .toPromise();
  }
}

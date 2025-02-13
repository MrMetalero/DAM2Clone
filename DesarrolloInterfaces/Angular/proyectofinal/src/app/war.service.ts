import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface WarStatus {
  warId: number;
  time: string;
  impactMultiplier: number;
  storyBeatId32: number;
  planetStatus: {
    index: number;
    owner: number;
    health: number;
    regenPerSecond: number;
    players: number;
  }[];
}

@Injectable({
  providedIn: 'root'
})
export class WarService {
  private apiUrl = 'https://your-api-endpoint.com/war-status';

  constructor(private http: HttpClient) {}

  fetchWarStatus(): Observable<WarStatus> {
    const headers = new HttpHeaders({
      'X-Super-Client': 'tu-aplicacion', // Reemplaza con el nombre de tu app
      'X-Super-Contact': 'tu-email@dominio.com' // Opcional
    });

    return this.http.get<WarStatus>(this.apiUrl, { headers });
  }
}


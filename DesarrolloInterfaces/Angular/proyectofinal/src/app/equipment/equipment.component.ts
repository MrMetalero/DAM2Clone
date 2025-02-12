import { Router, RouterModule, RouterLink } from '@angular/router';
import { DataService } from './../data.service';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';


interface PlanetStatus {
  index: number;
  owner: number;
  health: number;
  regenPerSecond: number;
  players: number;
}

interface WarStatus {
  warId: number;
  time: number;
  impactMultiplier: number;
  storyBeatId32: number;
  planetStatus: PlanetStatus[];
}
@Component({
  selector: 'app-equipment',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './equipment.component.html',
  styleUrl: './equipment.component.css'
})
export class EquipmentComponent {
  warStatus: WarStatus | null = null;
  constructor(dataService:DataService, private http: HttpClient){}
  
  ngOnInit(): void {
    this.fetchWarStatus();
  }

  fetchWarStatus(): void {
    this.http.get<WarStatus>('https://helldiverstrainingmanual.com/api/v1/war/status')
      .subscribe(
        (data) => {
          this.warStatus = data;
        },
        (error) => {
          console.error('Error fetching war status:', error);
        }
      );
  }

}

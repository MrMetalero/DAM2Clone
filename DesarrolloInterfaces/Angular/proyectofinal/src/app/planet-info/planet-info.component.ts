import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

interface Planet {
  name: string;
  sector: string;
  biome: {
    slug: string;
    description: string;
  } | null;
  environmentals: {
    name: string;
    description: string;
  }[];
}

@Component({
  selector: 'app-planet-info',
  templateUrl: './planet-info.component.html',
  standalone: true,
  styleUrls: ['./planet-info.component.css'],
  imports: [CommonModule,FormsModule],
  
})
export class PlanetInfoComponent implements OnInit {
  planets: { [key: string]: Planet } = {}; // Store planet data
  isLoading = true; // Loading state
  error: string | null = null; // Error state

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchPlanetData();
  }

  fetchPlanetData(): void {
    this.http
      .get<{ [key: string]: Planet }>('https://helldiverstrainingmanual.com/api/v1/planets')
      .subscribe({
        next: (data) => {
          this.planets = data;
          this.isLoading = false;
        },
        error: (err) => {
          this.error = 'Failed to fetch planet data. Please try again later.';
          this.isLoading = false;
          console.error('Error fetching planet data:', err);
        },
      });
  }

  getPlanetArray(): Planet[] {
    return Object.values(this.planets);
  }
}
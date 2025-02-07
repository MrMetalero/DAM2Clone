import { DataService } from './../data.service';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule,RouterLink],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  loggedInUser: string | null = null;

  constructor(private dataService: DataService,private router: Router ){
    this.loggedInUser = this.dataService.getLoggedInUser();
    console.log(this.loggedInUser);
    
  }
}

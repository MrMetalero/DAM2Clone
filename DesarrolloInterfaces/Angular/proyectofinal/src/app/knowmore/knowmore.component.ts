import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { DataService } from '../data.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-knowmore',
  imports: [CommonModule],
  standalone: true,
  templateUrl: './knowmore.component.html',
  styleUrl: './knowmore.component.css'
})
export class KnowmoreComponent {
  logedInUser:String | null | undefined

  constructor(private dataService: DataService,private router: Router){
    
  }

  getLoggedInUserService(){
    this.logedInUser = this.dataService.getLoggedInUser()
  }

}

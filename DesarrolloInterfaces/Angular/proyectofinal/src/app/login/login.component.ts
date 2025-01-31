import { DataService } from './../data.service';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Data } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginAttemptUsername?: String = '';
  loginAttemptPassword?: String = '';
  constructor(private dataService: DataService){ //passing the dataservice as parameter here injects it

  }

  onSubmit(){
    this.dataService.login(this.loginAttemptUsername,this.loginAttemptPassword)
  }


}

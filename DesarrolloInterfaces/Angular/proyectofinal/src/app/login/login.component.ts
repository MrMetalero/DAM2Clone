import { DataService } from './../data.service';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginAttemptUsername?: string = '';
  loginAttemptPassword?: string = '';
  errorMessage: string = '';

  constructor(private dataService: DataService,private router: Router){ //passing the dataservice as parameter here injects it

  }

  onSubmit(){
    if (this.dataService.login(this.loginAttemptUsername, this.loginAttemptPassword)) {
      console.log('Login successful for:', this.loginAttemptUsername);
      this.router.navigate(['/dashboard']); // Navigate on success
    } else {
      this.errorMessage = 'Invalid username or password';
      console.error('Login failed for:', this.loginAttemptUsername);
    }
  }


}

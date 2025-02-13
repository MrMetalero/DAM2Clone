import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { DoorRevealComponent } from '../door-reveal/door-reveal.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule,RouterLink,DoorRevealComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}

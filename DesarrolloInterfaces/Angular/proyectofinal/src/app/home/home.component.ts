import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { VideoLauncherComponent } from '../video-launcher/video-launcher.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule,RouterLink, VideoLauncherComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}

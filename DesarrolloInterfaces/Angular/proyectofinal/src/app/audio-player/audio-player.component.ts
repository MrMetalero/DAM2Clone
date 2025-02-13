import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Input, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-audio-player',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './audio-player.component.html',
  styleUrl: './audio-player.component.css'
})

export class AudioPlayerComponent implements OnInit, OnDestroy {
  @Input() audioFile: string = 'mainTheme.mp3'; 
  audio = new Audio();
  isPlaying = false;
  volume = 0.5; 

  ngOnInit() {
    this.audio.src = `assets/sounds/${this.audioFile}`;
    this.audio.volume = this.volume;
    this.audio.load();
  }

  togglePlay() {
    if (this.isPlaying) {
      this.audio.pause();
    } else {
      this.audio.play();
    }
    this.isPlaying = !this.isPlaying;
  }

  changeVolume(event: Event) {
    const target = event.target as HTMLInputElement;
    this.volume = Number(target.value);
    this.audio.volume = this.volume;
  }

  ngOnDestroy() {
    this.audio.pause();
    this.audio.src = '';
  }
}

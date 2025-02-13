import { CommonModule } from '@angular/common';
import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { AudioPlayerComponent } from '../audio-player/audio-player.component';

@Component({
  selector: 'app-video-launcher',
  standalone: true,
  imports: [CommonModule, AudioPlayerComponent],
  templateUrl: './video-launcher.component.html',
  styleUrls: ['./video-launcher.component.css']
})




export class VideoLauncherComponent implements OnInit, OnDestroy {
  videoElement: HTMLVideoElement;
  isVisible: boolean = true;
  videoPlaying: boolean = false; // Track if video is playing
  @ViewChild(AudioPlayerComponent) audioPlayerComponent!: AudioPlayerComponent;

  constructor() {
    this.videoElement = document.createElement('video');
  }

  ngOnInit() {
    // Listen for video events and manage the audio
    this.videoElement.addEventListener('play', this.onVideoPlay.bind(this));
    this.videoElement.addEventListener('ended', this.onVideoEnd.bind(this));
    this.videoElement.addEventListener('pause', this.onVideoEnd.bind(this));
  }

  // Triggered by a button click or another interaction
  playVideo() {
    // Ensure play() is called on user interaction
    if (!this.videoPlaying) {
      this.videoElement.src = 'assets/videos/intro.mp4';
      this.videoElement.play().catch(err => {
        console.log('Error playing video:', err);
      });
      this.videoPlaying = true; // Video is now playing
    }
  }

  closeVideo() {
    this.isVisible = false;
    this.videoElement.pause();
    this.videoElement.src = '';
    this.restoreAudioVolume(); // Restore volume when video is closed
  }

  // Handle the volume change during video play
  onVideoPlay() {
    if (this.audioPlayerComponent) {
      this.audioPlayerComponent.audio.volume = 0; // Lower audio volume to 0 when video plays
    }
  }

  // Handle the volume restore after video ends
  onVideoEnd() {
    if (this.audioPlayerComponent) {
      this.audioPlayerComponent.audio.volume = 0.5; // Restore audio volume to 50% when video ends
    }
    this.isVisible = false; // Hide the video after it ends
    this.videoPlaying = false; // Video is no longer playing
  }

  restoreAudioVolume() {
    if (this.audioPlayerComponent) {
      this.audioPlayerComponent.audio.volume = 0.5; // Ensure volume is reset to 50% if the video is closed manually
    }
  }

  ngOnDestroy() {
    if (this.videoElement) {
      this.videoElement.pause();
      this.videoElement.src = '';
    }
  }
}
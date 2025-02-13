import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-door-reveal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './door-reveal.component.html',
  styleUrl: './door-reveal.component.css'
})
export class DoorRevealComponent {
  isVisible = true;

  constructor() {
    setTimeout(() => {
      this.openDoors();
    }, 1000);
  }

  openDoors() {
    const leftDoor = document.querySelector('.left');
    const rightDoor = document.querySelector('.right');

    if (leftDoor && rightDoor) {
      leftDoor.classList.add('open');
      rightDoor.classList.add('open');
    }

    setTimeout(() => {
      this.isVisible = false;
    }, 1800);
  }
}

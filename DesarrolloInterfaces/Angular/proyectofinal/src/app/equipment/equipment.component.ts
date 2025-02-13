import { Component, OnInit } from '@angular/core';
import { WarService, WarStatus } from '../war.service';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-equipment',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './equipment.component.html',
  styleUrls: ['./equipment.component.css']
})


export class EquipmentComponent implements OnInit {
  firearms: string[] = [];
  armors: string[] = [];

  ngOnInit() {
    this.loadEquipment();
  }

  loadEquipment() {
    this.firearms = JSON.parse(localStorage.getItem('firearms') || '[]');
    this.armors = JSON.parse(localStorage.getItem('armors') || '[]');
  }

  addFirearm() {
    const newFirearm = prompt('Enter the name of the new firearm:');
    if (newFirearm) {
      this.firearms.push(newFirearm);
      localStorage.setItem('firearms', JSON.stringify(this.firearms));
    }
  }

  addArmor() {
    const newArmor = prompt('Enter the name of the new armor:');
    if (newArmor) {
      this.armors.push(newArmor);
      localStorage.setItem('armors', JSON.stringify(this.armors));
    }
  }
}

import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { HousingService } from '../housing.service';

@Component({
  selector: 'app-add-house-component',
  imports:[ReactiveFormsModule],
  templateUrl: './add-house-component.component.html',
  styleUrls: ['./add-house-component.component.css'],
  standalone: true,
})
export class AddHouseComponentComponent implements OnInit {
    applyForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl(''),
  });
  housingService = inject(HousingService);
  
  constructor() {

   }

   
  ngOnInit() {

  }

  submitNewHouse() {
    this.housingService.submitApplication(
      this.applyForm.value.firstName ?? '',
      this.applyForm.value.lastName ?? '',
      this.applyForm.value.email ?? '',
    );
  }

}

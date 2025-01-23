import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { HousingService } from '../housing.service';

@Component({
  selector: 'app-add-house-component',
  imports: [ReactiveFormsModule],
  templateUrl: './add-house-component.component.html',
  styleUrls: ['./add-house-component.component.css'],
  standalone: true,
})
export class AddHouseComponentComponent implements OnInit {
  newHouseForm = new FormGroup({
    housingName: new FormControl<string>(''), // Specify the type as string
    housingCity: new FormControl<string>(''), // Specify the type as string
    housingState: new FormControl<string>(''), // Specify the type as string
    housingPhoto: new FormControl<string | null>(null), // Specify the type as string or null
    housingNumber: new FormControl<number>(0), // Specify the type as number
    hasWifi: new FormControl<boolean>(false), // Specify the type as boolean
    hasLaundry: new FormControl<boolean>(false), // Specify the type as boolean
  });

  housingService = inject(HousingService);

  constructor() {}

  ngOnInit() {}

  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      const reader = new FileReader();

      reader.onload = (e: ProgressEvent<FileReader>) => {
        // Set the housingPhoto control to the data URL
        this.newHouseForm.patchValue({
          housingPhoto: e.target?.result as string, // Cast to string
        });
      };

      reader.readAsDataURL(file); // Read the file as a data URL
    }
  }

  submitNewHouse() {
    const formValues = this.newHouseForm.value;

    this.housingService.submitNewHouse(
      formValues.housingName!, // Use non-null assertion operator
      formValues.housingCity!, // Use non-null assertion operator
      formValues.housingState!, // Use non-null assertion operator
      formValues.housingPhoto!, // Use non-null assertion operator
      formValues.housingNumber!, // Use non-null assertion operator
      formValues.hasWifi!, // Use non-null assertion operator
      formValues.hasLaundry! // Use non-null assertion operator
    );

    this.newHouseForm.reset(); // Optional: Reset the form after submission
  }
}
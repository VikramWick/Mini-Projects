import { Component, NgModule } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { InsuranceService } from '../service/insurance';
import { CommonModule } from '@angular/common';
import { Navbar } from '../navbar/navbar';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ReactiveFormsModule,CommonModule,Navbar],
  templateUrl: './home.html',
})
export class HomeComponent {
  detailsForm: FormGroup;

  constructor(private fb: FormBuilder, private router: Router, private service: InsuranceService) { // Inject InsuranceService
    this.detailsForm = this.fb.group({
      age: ['', [Validators.required, Validators.min(18), Validators.max(100)]],
      salary: ['', Validators.required], // Changed from 'income' to 'salary'
      healthCondition: ['none', Validators.required]
    });
  }

  onSubmit() {
    if (this.detailsForm.valid) {
      // Save data to service to use in calculation later
      this.service.userDemographics = this.detailsForm.value;
      // Close modal manually (using button attributes in HTML) and navigate
      this.router.navigate(['/plans']);
    }
  }
}
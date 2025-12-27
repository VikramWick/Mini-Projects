import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { InsuranceService } from '../service/insurance';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.html',
  imports: [ReactiveFormsModule,CommonModule],
  standalone: true
})

export class BookingComponent implements OnInit {
  bookingForm: FormGroup;
  selectedPlan: any;
  premiumAmt: number = 0;

  constructor(
    private route: ActivatedRoute,
    private service: InsuranceService,
    private fb: FormBuilder,
    private router: Router
  ) {
    this.bookingForm = this.fb.group({
      name: ['', Validators.required],
      city: ['', Validators.required],
      phone: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      email: ['', [Validators.required, Validators.email]],
      age: [this.service.userDemographics?.age || '', Validators.required],
      paymentMode: ['credit card', Validators.required],
      cardNumber: ['', [Validators.required, Validators.pattern('^[0-9]{16}$')]], // Simple 16 digit check
      paymentFreq: ['yearly', Validators.required]
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.service.getPlanById(id).subscribe((res: any) => {
      // JSON server returns array for query params, pick first item
      this.selectedPlan = res[0];
      
      // Calculate final premium
      const age = this.bookingForm.get('age')?.value || 25;
      this.premiumAmt = this.service.calculatePremium(this.selectedPlan.baseAmt, age);
    });
  }

  confirmBooking() {
    if (this.bookingForm.invalid) return;

    const bookingData = {
      ...this.bookingForm.value,
      planId: this.selectedPlan.planId,
      planName: this.selectedPlan.planName,
      premiumAmt: this.premiumAmt,
      validity: this.selectedPlan.validity
    };

    this.service.bookPlan(bookingData).subscribe({
      next: () => {
        alert('Booking Successful! Thank you for choosing Raindrops.');
        this.router.navigate(['/']);
      },
      error: (err) => alert('Transaction failed. Please try again.')
    });
  }
}
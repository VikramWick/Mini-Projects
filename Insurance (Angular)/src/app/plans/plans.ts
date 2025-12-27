import { Component, OnInit, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { InsuranceService } from '../service/insurance';
import { Router } from '@angular/router';
import { CommonModule, NgIf } from '@angular/common';
import { Navbar } from '../navbar/navbar';

@Component({
  selector: 'app-plans',
  standalone: true,
  templateUrl: './plans.html',
  imports: [CommonModule,Navbar], // Required for *ngIf and *ngFor
  changeDetection: ChangeDetectionStrategy.Default
})
export class PlansComponent implements OnInit {
  plans: any[] = [];
  userAge: number = 25; // Default age if user didn't use the form
  userSalary: number = 0; // Default salary if not provided

  constructor(
    private service: InsuranceService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    // Load demographics if available
    if (this.service.userDemographics) {
      this.userAge = this.service.userDemographics.age;
      this.userSalary = this.service.userDemographics.salary || 0;
    }

    console.log('PlansComponent ngOnInit - userAge:', this.userAge, 'salary:', this.userSalary);

    // Fetch plans from service
    this.service.getPlans().subscribe({
      next: (data) => {
        console.log('PlansComponent - Raw data received from service:', data);

        if (Array.isArray(data)) {
          // Filter eligible plans and calculate premium
          this.plans = data
            .filter((plan: any) =>
              this.service.isEligible(plan, this.userAge, this.userSalary)
            )
            .map((plan: any) => ({
              ...plan,
              finalAmount: this.service.calculatePremium(plan.baseAmt || 0, this.userAge)
            }));

          console.log('PlansComponent - Processed plans:', this.plans);
        } else {
          console.error('PlansComponent - Data received is not an array:', data);
          this.plans = [];
        }

        // Trigger change detection
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('PlansComponent - Error fetching plans:', err);
        this.plans = [];
        this.cdr.detectChanges();
      }
    });
  }

  buyPlan(id: number) {
    this.router.navigate(['/book', id]);
  }
}

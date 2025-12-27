import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class InsuranceService {
  private apiUrl = 'http://localhost:3333';
  
  public userDemographics: { age: number; salary?: number } | null = null;

  constructor(private http: HttpClient) {}

  getPlans(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/plans`).pipe(
      catchError(this.handleError)
    );
  }

  getPlanById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/plans?planId=${id}`).pipe(
      catchError(this.handleError)
    );
  }

  bookPlan(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/bookings`, data);
  }

  // Premium calculation based on age
  calculatePremium(baseAmt: number, age: number): number {
    let multiplier = 1;
    if (age > 30) multiplier = 1.2;
    if (age > 50) multiplier = 1.5;
    return Math.round(baseAmt * multiplier);
  }

  /** ✅ Eligibility check based on age and salary */
  isEligible(plan: any, age: number, salary: number = 0): boolean {
    // Example rules (adjust as needed)
    if (plan.planName === 'Senior Shield') {
      // Only for people 60+
      return age >= 60;
    }

    if (plan.planName === 'Good Health Plan') {
      // Example: require salary >= 20000
      return salary >= 20000;
    }

    // Default: Care Life Plan available for anyone 18+
    return age >= 18;
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Error: ${error.error.message}`;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.error(errorMessage);
    return throwError(() => new Error(errorMessage));
  }
}

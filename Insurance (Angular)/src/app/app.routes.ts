import { RouterModule, Routes } from "@angular/router";
import { HomeComponent } from "./home/home";
import { About } from "./about/about";
import { PlansComponent } from "./plans/plans";
import { BookingComponent } from "./booking/booking";
import { NgModule } from "@angular/core";


export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'about', component: About },
  { path: 'plans', component: PlansComponent },
  { path: 'book/:id', component: BookingComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
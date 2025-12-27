import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {
  constructor(private router: Router) { }

  home():void{
    this.router.navigate(['/']);
  }

  about():void{
    this.router.navigate(['/about']);
  }

  plans():void{
    this.router.navigate(['/plans']);
  }

}

import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'drp-root',
  template: `
    <router-outlet
        (activate)='onActivate($event)'
    ></router-outlet>
  `,
  styles: []
})

export class AppComponent {
  constructor(
      private location: Location,
      private activeRoute : ActivatedRoute,
      private router: Router){}

  onActivate($event: any) {
    this.isAuthenticated = !!localStorage.getItem('jwt');
    if(!localStorage.getItem('jwt') && this.location.path() !== '/login'){
      this.router.navigate(['/login']);
    }
    if(localStorage.getItem('jwt') && this.location.path() === '/login'){
      this.router.navigate(['/dashboard']);
    }
  }
}

import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ChatService} from "./chat.service";
import { Location } from '@angular/common';

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
      private router: Router,
      private chatService: ChatService){}

  onActivate($event: any) {
    const estaLogado = this.chatService.estouLogado();
    if(this.chatService.estouLogado() && this.location.path() === '/entrar'){
      this.router.navigate(['/chat']);
    }else if(!this.chatService.estouLogado() && this.location.path() === '/chat'){
      this.router.navigate(['/entrar']);
    }
  }
}

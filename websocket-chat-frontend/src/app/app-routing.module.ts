import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginPageComponent} from "./login-page/login-page.component";
import {ChatPageComponent} from "./chat-page/chat-page.component";

const routes: Routes = [
    { path: 'entrar', component: LoginPageComponent },
    { path: '', redirectTo: '/entrar', pathMatch: 'full' },
    { path: 'chat', component: ChatPageComponent },
    { path: '**',  redirectTo: '/entrar', pathMatch: 'full' }
];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}
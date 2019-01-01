import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { ChatPageComponent } from './chat-page/chat-page.component';
import { ChatMensagensComponent } from './chat-mensagens/chat-mensagens.component';
import { UsuariosListaComponent } from './usuarios-lista/usuarios-lista.component';
import { FormEnviarMsgComponent } from './form-enviar-msg/form-enviar-msg.component';
import {AppRoutingModule} from "./app-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    LoginFormComponent,
    ChatPageComponent,
    ChatMensagensComponent,
    UsuariosListaComponent,
    FormEnviarMsgComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

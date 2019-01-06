import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {ChatService} from "../chat.service";
import {MatSnackBar} from "@angular/material";
import {Router} from "@angular/router";

@Component({
  selector: 'drp-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit {

  loginForm = new FormGroup({
    nome: new FormControl( '', {
      validators: [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(20)],
      asyncValidators: [this.verificarNomeDisponivel.bind(this)],
      updateOn: "blur"
    })
  })
  sexo: string = "masculino";

  carregando = false;

  constructor(private chatService: ChatService, public snackBar: MatSnackBar, private router: Router) {}

  ngOnInit() {
  }

  showErrorMessage() {
    this.snackBar.open('Ocorreu um erro, por favor recarregue a página e tente novamente','Ok', {
      duration: 3000,
    });
  }

  verificarNomeDisponivel(control): Promise<ValidationErrors | null>{
    return this.chatService.verificarNomeUsuarioDisponivel(control.value)
        .then(d => d ? null : {nomeIndisponivel: d})
        .catch(e => ({nomeIndisponivel: e}));
  }

  getErrorMessage(): string {
    const erroSincrono = this.loginForm.controls.nome.hasError('required') ? 'Por favor preencha o nome' :
        this.loginForm.controls.nome.hasError('minlength') ? 'O nome deve ter no minimo 3 caracteres' :
            this.loginForm.controls.nome.hasError('maxlength') ? 'O nome deve ter no máximo 20 caracteres' :
                '';
    const erroAssincrono = this.loginForm.controls.nome.hasError('nomeIndisponivel') ? 'Esse nome já está em uso' : '';

    return `${erroSincrono} ${erroAssincrono}`
  }

  login(){
    this.carregando = true;
    this.chatService.entrar(this.loginForm.controls.nome.value, this.sexo === 'masculino')
        .then(r => {
          this.carregando = false;
          this.router.navigate(['/chat']);
        })
        .catch(e => {
          this.showErrorMessage();
          this.carregando = false;
        })
  }
}

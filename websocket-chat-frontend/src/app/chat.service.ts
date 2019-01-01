import {Injectable} from '@angular/core';
import {Usuario} from "./usuario.model";
import {ChatObservavel} from "./chat-observavel";
import {ChatObservador} from "./chat-observador";
import {AcaoChat} from "./acaoChat.model";
import {TipoAcaoChat} from "./tipoAcaoChat.enum";

@Injectable({
  providedIn: 'root'
})
export class ChatService implements ChatObservavel{

  private chatConnection: WebSocket;

  private observadores: ChatObservador[] = [];

  constructor() { }

  private reagirMensagemWebsocket(mensagem: AcaoChat){
    switch (mensagem.tipoAcao) {
      case(TipoAcaoChat.MENSAGEM_PRIVADA):
        this.notificarNovaMensagemPrivada(mensagem);
        break;
      case(TipoAcaoChat.MENSAGEM_PUBLICA):
        this.notificarNovaMensagemPublica(mensagem);
        break;
      case(TipoAcaoChat.USUARIO_ENTROU):
        this.notificarUsuarioEntrou(mensagem);
        break;
      case(TipoAcaoChat.USUARIO_SAIU):
        this.notificarUsuarioSaiu(mensagem);
        break;
    }
  }

  public entrar(nomeUsuario): Promise<void> {
    return new Promise((resolve, reject) => {
      const loginRequest = {
        tipoAcao: TipoAcaoChat.USUARIO_ENTROU,
        payload: {
          '@type': 'UsuarioEntrou',
          usuario: nomeUsuario,
          dataEntrada: new Date().toLocaleString('pt-br')
        }
      }
      this.chatConnection = new WebSocket('ws://localhost:8080/chat');

      this.chatConnection.onopen = () => {
        this.chatConnection.send(JSON.stringify(loginRequest));
        sessionStorage.setItem('usuario-chat', nomeUsuario);
        setTimeout(resolve, 500);
      };

      this.chatConnection.onerror = function (error) {
        console.error('ATENÇÃO!!! Ocorreu um WebSocket Error ', error);
      };

      this.chatConnection.onmessage = m => this.reagirMensagemWebsocket(m.data);
    });
  }

  public estouLogado(): boolean{
    return !!sessionStorage.getItem('usuario-chat');
  }

  public meuUsuario(): String {
    return sessionStorage.getItem('usuario-chat');
  }

  public obterUsuarios(): Promise<Usuario[]> {
    return fetch('http://localhost:8080/api/usuarios/').then(r => r.json());
  }

  public enviarMensagemPrivada(destinatario: String, mensagem: String): void {
    const request = {
      tipoAcao: TipoAcaoChat.MENSAGEM_PRIVADA,
      payload: {
        '@type': 'Mensagem',
        usuarioDestinatario: destinatario,
        mensagem: mensagem,
        dataEnvio: new Date().toLocaleString('pt-br')
      }
    }

    this.chatConnection.send(JSON.stringify(request));
  }

  public enviarMensagemPublica(mensagem: String): void {
    const request = {
      tipoAcao: TipoAcaoChat.MENSAGEM_PUBLICA,
      payload: {
        '@type': 'Mensagem',
        mensagem: mensagem,
        dataEnvio: new Date().toLocaleString('pt-br')
      }
    }

    this.chatConnection.send(JSON.stringify(request));
  }

  public adicionarObservador(observador: ChatObservador): void {
    this.observadores.push(observador);
  }

  public notificarNovaMensagemPrivada(acaoChat: AcaoChat): void {
      this.observadores.forEach(o => o.reagirNovaMensagemPrivada(acaoChat.payload))
  }

  public notificarNovaMensagemPublica(acaoChat: AcaoChat): void {
    this.observadores.forEach(o => o.reagirNovaMensagemPrivada(acaoChat.payload))
  }

  public notificarUsuarioEntrou(acaoChat: AcaoChat): void {
    this.observadores.forEach(o => o.reagirNovaMensagemPrivada(acaoChat.payload))
  }

  public notificarUsuarioSaiu(acaoChat: AcaoChat): void {
    this.observadores.forEach(o => o.reagirNovaMensagemPrivada(acaoChat.payload))
  }
}

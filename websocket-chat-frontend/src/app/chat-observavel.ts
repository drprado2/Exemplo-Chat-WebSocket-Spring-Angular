import {ChatObservador} from "./chat-observador";
import {AcaoChat} from "./acaoChat.model";

export interface ChatObservavel {
    adicionarObservador(observador: ChatObservador): void;

    notificarUsuarioEntrou(acaoChat: AcaoChat): void;

    notificarUsuarioSaiu(acaoChat: AcaoChat): void;

    notificarNovaMensagemPrivada(acaoChat: AcaoChat): void;

    notificarNovaMensagemPublica(acaoChat: AcaoChat): void;
}
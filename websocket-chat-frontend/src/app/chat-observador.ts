import {UsuarioEntrou} from "./usuarioEntrou.model";
import {UsuarioSaiu} from "./usuarioSaiu.model";
import {Mensagem} from "./mensagem.model";

export interface ChatObservador {
    reagirUsuarioEntrou(dto: UsuarioEntrou): void;

    reagirUsuarioSaiu(dto: UsuarioSaiu): void;

    reagirNovaMensagemPrivada(dto: Mensagem): void;

    reagirNovaMensagemPublica(dto: Mensagem): void;
}
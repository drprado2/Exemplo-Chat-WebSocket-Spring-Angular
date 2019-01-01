import {TipoAcaoChat} from "./tipoAcaoChat.enum";
import {UsuarioEntrou} from "./usuarioEntrou.model";
import {UsuarioSaiu} from "./usuarioSaiu.model";
import {Mensagem} from "./mensagem.model";

export class AcaoChat {
    tipoAcao: TipoAcaoChat;
    payload: UsuarioEntrou | UsuarioSaiu | Mensagem;
}
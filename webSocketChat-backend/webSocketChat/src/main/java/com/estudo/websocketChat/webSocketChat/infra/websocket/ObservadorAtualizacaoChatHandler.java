package com.estudo.websocketChat.webSocketChat.infra.websocket;

import org.springframework.web.socket.TextMessage;

import java.io.IOException;

public interface ObservadorAtualizacaoChatHandler {

    void usuarioSaiu(String idSessao);

    void novaMensagemRecebida(TextMessage mensagem, String idSessao) throws IOException;
}

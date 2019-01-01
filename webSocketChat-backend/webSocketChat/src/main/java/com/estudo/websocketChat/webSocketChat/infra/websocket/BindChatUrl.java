package com.estudo.websocketChat.webSocketChat.infra.websocket;

import org.springframework.web.socket.TextMessage;

public interface BindChatUrl {
    void executar(TextMessage mensagem);
}

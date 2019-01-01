package com.estudo.websocketChat.webSocketChat.infra.websocket;

import java.io.IOException;
import java.util.Map;

public interface ChatWebSocketHandler {

    void adicionarObservadorAtualizacoes(ObservadorAtualizacaoChatHandler observador);

    void enviarMensagemParaSecao(String idSessao, String jsonConvertido) throws IOException;

    void enviarMensagemParaTodasSecoes(String mensagemJsonConvertido) throws IOException;
}

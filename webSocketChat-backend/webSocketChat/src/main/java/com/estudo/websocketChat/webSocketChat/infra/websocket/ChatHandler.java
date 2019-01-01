package com.estudo.websocketChat.webSocketChat.infra.websocket;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Scope("singleton")
public class ChatHandler extends TextWebSocketHandler implements ChatWebSocketHandler {

    private static volatile List<WebSocketSession> sessoes = new CopyOnWriteArrayList<>();

    private static volatile List<ObservadorAtualizacaoChatHandler> observadoresAtualizacoes = new ArrayList<>();

    private WebSocketSession encontrarSessao(String idSessao){
        return sessoes.stream().filter(s -> s.getId().equals(idSessao)).findFirst().get();
    }

    private void notificarSessaoAcabou(String idSessao){
        observadoresAtualizacoes.forEach(o -> o.usuarioSaiu(idSessao));
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
        for (ObservadorAtualizacaoChatHandler o : observadoresAtualizacoes)
            o.novaMensagemRecebida(message, session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        WebSocketSession sessionToRemove = encontrarSessao(session.getId());
        sessoes.remove(sessionToRemove);
        notificarSessaoAcabou(session.getId());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessoes.add(session);
    }

    @Override
    public void adicionarObservadorAtualizacoes(ObservadorAtualizacaoChatHandler observador) {
        this.observadoresAtualizacoes.add(observador);
    }

    public void enviarMensagemParaSecao(String idSessao, String jsonConvertido) throws IOException {
        WebSocketSession sessao = encontrarSessao(idSessao);
        sessao.sendMessage(new TextMessage(jsonConvertido));
    }

    public void enviarMensagemParaTodasSecoes(String mensagemJsonConvertido) throws IOException {
        TextMessage mensagem = new TextMessage(mensagemJsonConvertido);
        for (WebSocketSession s : sessoes)
            s.sendMessage(mensagem);
    }
}


package com.estudo.websocketChat.webSocketChat.domain.usuarios;

import com.estudo.websocketChat.webSocketChat.domain.dtos.AcaoChatDto;
import com.estudo.websocketChat.webSocketChat.domain.dtos.MensagemDto;
import com.estudo.websocketChat.webSocketChat.domain.dtos.UsuarioEntrouDto;
import com.estudo.websocketChat.webSocketChat.domain.dtos.UsuarioSaiuDto;
import com.estudo.websocketChat.webSocketChat.infra.websocket.ChatWebSocketHandler;
import com.estudo.websocketChat.webSocketChat.infra.websocket.ObservadorAtualizacaoChatHandler;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@DependsOn("chatHandler")
@Scope("singleton")
public class ServicoUsuario implements ObservadorAtualizacaoChatHandler, ServicoAplicacaoUsuario {

    private static volatile Set<Usuario> usuariosAtivos = new HashSet<>();

    private ObjectMapper jsonParser;

    @Autowired
    ChatWebSocketHandler chatHandler;

    private Usuario encontrarUsuarioPorId(String id){
        return usuariosAtivos.stream().filter(u -> u.getId().equals(id)).findFirst().get();
    }

    private Usuario encontrarUsuarioPorNome(String nome){
        return usuariosAtivos.stream().filter(u -> u.getNome().equals(nome)).findFirst().get();
    }

    @PostConstruct
    public void init(){
        chatHandler.adicionarObservadorAtualizacoes(this);

        jsonParser = new ObjectMapper();
        jsonParser.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonParser.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jsonParser.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jsonParser.registerModule(new Jdk8Module());
        jsonParser.registerModule(new ParameterNamesModule());
        jsonParser.registerModule(new JavaTimeModule());
    }

    public void usuarioEntrou(UsuarioEntrouDto dto, String idSessao) {
        Usuario usuario = new Usuario(idSessao, dto.getUsuario(), LocalDateTime.now());
        usuariosAtivos.add(usuario);

        try {
            String mensagem = jsonParser.writeValueAsString(new AcaoChatDto(TipoAcaoChat.USUARIO_ENTROU, dto));
            chatHandler.enviarMensagemParaTodasSecoes(mensagem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void usuarioSaiu(String idSessao) {
        Usuario usuario = encontrarUsuarioPorId(idSessao);
        usuariosAtivos.remove(usuario);

        UsuarioSaiuDto dto = new UsuarioSaiuDto();
        dto.setUsuario(usuario.getNome());
        dto.setDataEntrada(usuario.getDataEntrada());
        dto.setDataSaida(LocalDateTime.now());

        try {
            String mensagem = jsonParser.writeValueAsString(new AcaoChatDto(TipoAcaoChat.USUARIO_SAIU, dto));
            chatHandler.enviarMensagemParaTodasSecoes(mensagem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void novaMensagemRecebida(TextMessage mensagem, String idSessao) throws IOException {
        AcaoChatDto acaoChatDto = jsonParser.readValue(mensagem.getPayload(), AcaoChatDto.class);

        switch (acaoChatDto.getTipoAcao()){
            case USUARIO_ENTROU:
                usuarioEntrou((UsuarioEntrouDto)acaoChatDto.getPayload(), idSessao);
                break;
            case MENSAGEM_PRIVADA:
                tratarMensagemPrivadaRecebida((MensagemDto) acaoChatDto.getPayload(), idSessao);
                break;
            case MENSAGEM_PUBLICA:
                tratarMensagemPublicaRecebida((MensagemDto) acaoChatDto.getPayload(), idSessao);
                break;
            default:
                throw new NotImplementedException();
        }
    }

    public void tratarMensagemPublicaRecebida(MensagemDto mensagem, String idSessao) {
        try {
            Usuario remetente = encontrarUsuarioPorId(idSessao);
            mensagem.setUsuarioRemetente(Optional.of(remetente.getNome()));
            String msg = jsonParser.writeValueAsString(new AcaoChatDto(TipoAcaoChat.MENSAGEM_PUBLICA, mensagem));
            chatHandler.enviarMensagemParaTodasSecoes(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tratarMensagemPrivadaRecebida(MensagemDto mensagem, String idSessao) {
        try {
            Usuario remetente = encontrarUsuarioPorId(idSessao);
            Usuario destinatario = encontrarUsuarioPorNome(mensagem.getUsuarioDestinatario().get());
            mensagem.setUsuarioRemetente(Optional.of(remetente.getNome()));
            String msg = jsonParser.writeValueAsString(new AcaoChatDto(TipoAcaoChat.MENSAGEM_PRIVADA, mensagem));
            chatHandler.enviarMensagemParaSecao(destinatario.getId(), msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean nomeUsuarioDisponivel(String nome) {
        return usuariosAtivos.stream().noneMatch(u -> u.getNome().equals(nome));
    }

    @Override
    public List<Usuario> obterUsuarios() {
        return usuariosAtivos.stream().collect(Collectors.toList());
    }
}

package com.estudo.websocketChat.webSocketChat.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public class MensagemDto extends AcaoPayload {

    private String mensagem;

    private Optional<String> usuarioRemetente;

    private Optional<String> usuarioDestinatario;

    private LocalDateTime dataEnvio;

    @NotNull
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Nullable
    public Optional<String> getUsuarioRemetente() {
        return usuarioRemetente;
    }

    public void setUsuarioRemetente(Optional<String> usuarioRemetente) {
        this.usuarioRemetente = usuarioRemetente;
    }

    @Nullable
    public Optional<String> getUsuarioDestinatario() {
        return usuarioDestinatario;
    }

    public void setUsuarioDestinatario(Optional<String> usuarioDestinatario) {
        this.usuarioDestinatario = usuarioDestinatario;
    }

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @NotNull
    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}

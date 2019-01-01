package com.estudo.websocketChat.webSocketChat.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MensagemDto.class, name = "Mensagem"),
        @JsonSubTypes.Type(value = UsuarioEntrouDto.class, name = "UsuarioEntrou"),
        @JsonSubTypes.Type(value = UsuarioSaiuDto.class, name = "UsuarioSaiu")}
)
public abstract class AcaoPayload {
}

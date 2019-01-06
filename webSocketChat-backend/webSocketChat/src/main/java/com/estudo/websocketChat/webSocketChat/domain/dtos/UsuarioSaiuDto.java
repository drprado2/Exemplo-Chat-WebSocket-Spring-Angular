package com.estudo.websocketChat.webSocketChat.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public class UsuarioSaiuDto extends AcaoPayload {

    private String Usuario;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;

    @NotBlank
    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @NotNull
    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @NotNull
    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }
}

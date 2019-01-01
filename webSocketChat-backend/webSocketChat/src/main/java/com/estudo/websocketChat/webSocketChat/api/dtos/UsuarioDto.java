package com.estudo.websocketChat.webSocketChat.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class UsuarioDto {
    private String nome;
    private LocalDateTime dataEntrada;

    @NotNull
    @NotBlank
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @NotNull
    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public UsuarioDto(String nome, LocalDateTime dataEntrada) {
        this.nome = nome;
        this.dataEntrada = dataEntrada;
    }
}

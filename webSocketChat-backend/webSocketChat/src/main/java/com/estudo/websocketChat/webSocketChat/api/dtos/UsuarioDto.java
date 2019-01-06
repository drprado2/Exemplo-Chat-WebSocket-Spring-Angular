package com.estudo.websocketChat.webSocketChat.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class UsuarioDto {
    private String id;
    private String nome;
    private LocalDateTime dataEntrada;
    private boolean masculino;

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

    public boolean isMasculino() {
        return masculino;
    }

    public void setMasculino(boolean masculino) {
        this.masculino = masculino;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UsuarioDto(String id, String nome, LocalDateTime dataEntrada, boolean masculino) {
        this.id = id;
        this.nome = nome;
        this.dataEntrada = dataEntrada;
        this.masculino = masculino;
    }
}

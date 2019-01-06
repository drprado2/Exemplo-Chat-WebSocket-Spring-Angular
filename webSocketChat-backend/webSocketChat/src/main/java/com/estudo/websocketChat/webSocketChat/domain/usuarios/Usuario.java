package com.estudo.websocketChat.webSocketChat.domain.usuarios;

import java.time.LocalDateTime;

public class Usuario {
    private String id;
    private String nome;
    private LocalDateTime dataEntrada;
    private boolean masculino;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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

    public Usuario(String id, String nome, LocalDateTime dataEntrada, boolean masculino) {
        this.id = id;
        this.nome = nome;
        this.dataEntrada = dataEntrada;
        this.masculino = masculino;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass().getTypeName().equals(this.getClass().getTypeName())
                && ((Usuario)obj).id == id;
    }
}

package com.estudo.websocketChat.webSocketChat.domain.usuarios;

import java.time.LocalDateTime;

public class Usuario {
    private String id;
    private String nome;
    private LocalDateTime dataEntrada;

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

    public Usuario(String id, String nome, LocalDateTime dataEntrada) {
        this.id = id;
        this.nome = nome;
        this.dataEntrada = dataEntrada;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass().getTypeName().equals(this.getClass().getTypeName())
                && ((Usuario)obj).id == id;
    }
}

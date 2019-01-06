package com.estudo.websocketChat.webSocketChat.domain.usuarios;

import java.util.List;

public interface ServicoAplicacaoUsuario {
    public boolean nomeUsuarioDisponivel(String nome);

    public List<Usuario> obterUsuarios();

    public int obterQuantidadeUsuarioLogado();
}

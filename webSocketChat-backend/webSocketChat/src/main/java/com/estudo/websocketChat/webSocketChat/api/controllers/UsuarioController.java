package com.estudo.websocketChat.webSocketChat.api.controllers;

import com.estudo.websocketChat.webSocketChat.api.dtos.UsuarioDto;
import com.estudo.websocketChat.webSocketChat.domain.usuarios.ServicoAplicacaoUsuario;
import com.estudo.websocketChat.webSocketChat.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private ServicoAplicacaoUsuario servicoUsuario;

    @GetMapping("/disponivel")
    public ResponseEntity nomeDisponivel(@RequestParam(name = "nome") String nome){
        if(servicoUsuario.nomeUsuarioDisponivel(nome))
            return ResponseEntity.ok(true);
        else
            return ResponseEntity.badRequest().body(false);
    }

    @GetMapping("/")
    public ResponseEntity<List<UsuarioDto>> obterTodos(){
        List<Usuario> usuarios = servicoUsuario.obterUsuarios();
        List<UsuarioDto> response = usuarios.stream().map(u -> new UsuarioDto(u.getId(), u.getNome(), u.getDataEntrada(), u.isMasculino())).collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/quantidade-logado")
    public ResponseEntity<Integer> obterQuantidadeLogado(){
        return ResponseEntity.ok().body(servicoUsuario.obterQuantidadeUsuarioLogado());
    }
}

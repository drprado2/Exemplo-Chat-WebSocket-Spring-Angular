package com.estudo.websocketChat.webSocketChat.domain.dtos;

import com.estudo.websocketChat.webSocketChat.domain.usuarios.TipoAcaoChat;

public class AcaoChatDto {

    private TipoAcaoChat tipoAcao;

    private AcaoPayload payload;

    public TipoAcaoChat getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(TipoAcaoChat tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public AcaoPayload getPayload() {
        return payload;
    }

    public void setPayload(AcaoPayload payload) {
        this.payload = payload;
    }

    public AcaoChatDto(TipoAcaoChat tipoAcao, AcaoPayload payload) {
        this.tipoAcao = tipoAcao;
        this.payload = payload;
    }
}

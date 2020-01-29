package com.matijacob.springboot.backend.chat.models.service;

import com.matijacob.springboot.backend.chat.models.documents.Mensaje;

import java.util.List;

public interface ChatService {

    public List<Mensaje> obtenerUltimos15Mensajes();
    public Mensaje guardar(Mensaje mensaje);
}

package com.matijacob.springboot.backend.chat.models.service;

import com.matijacob.springboot.backend.chat.models.dao.ChatRepository;
import com.matijacob.springboot.backend.chat.models.documents.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public List<Mensaje> obtenerUltimos15Mensajes() {
        return chatRepository.findFirst15ByOrderByFechaDesc();
    }

    @Override
    public Mensaje guardar(Mensaje mensaje) {
        return chatRepository.save(mensaje);
    }
}

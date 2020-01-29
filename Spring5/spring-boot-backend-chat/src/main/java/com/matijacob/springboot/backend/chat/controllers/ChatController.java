package com.matijacob.springboot.backend.chat.controllers;

import com.matijacob.springboot.backend.chat.models.documents.Mensaje;
import com.matijacob.springboot.backend.chat.models.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Random;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate webSocket;

    private String[] colores = {"red", "green", "purple", "brown", "magenta", "orange"};

    @MessageMapping("/mensaje")
    @SendTo("/chat/mensaje")
    public Mensaje recibeMensaje(Mensaje mensaje){

        mensaje.setFecha(new Date().getTime());
        if(mensaje.getTipo().equals("NUEVO_USUARIO")){
            mensaje.setColor(colores[new Random().nextInt(colores.length)]);
            mensaje.setTexto("Nuevo usuario");
        } else {
            chatService.guardar(mensaje);
        }


        return mensaje;
    }

    @MessageMapping("/escribiendo")
    @SendTo("/chat/escribiendo")
    public String writing(String username){
        return  username.concat(" está escribiendo...");
    }

    @MessageMapping("/historial")
    public void historial(String clienteId){
        webSocket.convertAndSend("/chat/historial/"+ clienteId, chatService.obtenerUltimos15Mensajes());
    }
}

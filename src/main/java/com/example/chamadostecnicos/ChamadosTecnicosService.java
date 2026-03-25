package com.example.chamadostecnicos;

import java.time.LocalDateTime;

public class ChamadosTecnicosService {



    public Ticket createTicket(Long id, String name, String description, LocalDateTime createdAt, Status status){
        Ticket ticket = new Ticket(id, name, description, createdAt, status);
        return ticket;
    }


}

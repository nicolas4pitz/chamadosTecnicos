package com.example.chamadostecnicos.dto;

import com.example.chamadostecnicos.Status;
import com.example.chamadostecnicos.Ticket;

import java.time.LocalDateTime;

public record TicketsResponse(Long id, String name, String description, LocalDateTime createdAt, Status status) {

    public static TicketsResponse from(Ticket ticket){
        return new TicketsResponse(ticket.getId(), ticket.getName(), ticket.getDescription(), ticket.getCreatedAt(), ticket.getStatus());
    }

}

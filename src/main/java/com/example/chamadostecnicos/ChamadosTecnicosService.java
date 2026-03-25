package com.example.chamadostecnicos;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadosTecnicosService {

    private final ArrayList<Ticket> tickets = new ArrayList<>();
    private Long nextId = 1L;

    public Ticket createTicket(String name, String description, LocalDateTime createdAt, Status status) {
        LocalDateTime ticketCreatedAt = createdAt != null ? createdAt : LocalDateTime.now();
        Status ticketStatus = status != null ? status : Status.OPEN;

        Ticket ticket = new Ticket(nextId++, name, description, ticketCreatedAt, ticketStatus);
        tickets.add(ticket);
        return ticket;
    }

    public List<Ticket> findAll() {
        return List.copyOf(tickets);
    }

    public Optional<Ticket> findById(Long id) {
        return tickets.stream().filter(ticket -> ticket.getId().equals(id)).findFirst();
    }

    public Optional<Ticket> updateTicket(Long id, Status status) {
        Optional<Ticket> optionalTicket = findById(id);
        if (optionalTicket.isEmpty()) {
            return Optional.empty();
        }

        Ticket ticket = optionalTicket.get();
        ticket.setStatus(status != null ? status : ticket.getStatus());
        return Optional.of(ticket);
    }

    public boolean deleteTicket(Long id) {
        return tickets.removeIf(ticket -> ticket.getId().equals(id));
    }
}

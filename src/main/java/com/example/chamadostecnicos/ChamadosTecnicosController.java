package com.example.chamadostecnicos;

import com.example.chamadostecnicos.dto.TicketsCreateRequest;
import com.example.chamadostecnicos.dto.TicketsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/tickets")
public class ChamadosTecnicosController {

    ArrayList<Ticket> arr = new ArrayList();
    private ChamadosTecnicosService service;
    static Long ticketId;

    @PostMapping
    public ResponseEntity<TicketsResponse> adicionarTicket(@RequestBody TicketsCreateRequest body){
        Ticket ticket = service.createTicket(ticketId, body.name(), body.description(), body.createdAt(), body.status());
        return ResponseEntity.status(HttpStatus.CREATED).body(TicketsResponse.from(ticket));
    }



}

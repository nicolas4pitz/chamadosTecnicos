package com.example.chamadostecnicos;

import com.example.chamadostecnicos.dto.TicketsCreateRequest;
import com.example.chamadostecnicos.dto.TicketsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class ChamadosTecnicosController {

    private final ChamadosTecnicosService service;

    public ChamadosTecnicosController(ChamadosTecnicosService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TicketsResponse> adicionarTicket(@RequestBody TicketsCreateRequest body) {
        Ticket ticket = service.createTicket(body.name(), body.description(), body.createdAt(), body.status());
        return ResponseEntity.status(HttpStatus.CREATED).body(TicketsResponse.from(ticket));
    }

    @GetMapping
    public ResponseEntity<List<TicketsResponse>> listarTickets() {
        List<TicketsResponse> tickets = service.findAll().stream().map(TicketsResponse::from).toList();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketsResponse> buscarTicketPorId(@PathVariable Long id) {
        return service.findById(id)
                .map(TicketsResponse::from)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketsResponse> atualizarTicket(@PathVariable Long id, @RequestBody TicketsCreateRequest body) {
        return service.updateTicket(id, body.status())
                .map(TicketsResponse::from)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> deletarTicket(@PathVariable Long id) {
        boolean deleted = service.deleteTicket(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket nao encontrado");
        }

        return ResponseEntity.ok("Ticket removido com sucesso");
    }


}

package com.example.chamadostecnicos.dto;

import com.example.chamadostecnicos.Status;

import java.time.LocalDateTime;

public record TicketsCreateRequest(String name, String description, LocalDateTime createdAt, Status status) {
}

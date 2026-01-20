package com.f1fantasy.dto;

import java.time.LocalDateTime;

public record CreateRaceRequest(
        String name,
        String circuit,
        LocalDateTime date,
        Integer season) {
}

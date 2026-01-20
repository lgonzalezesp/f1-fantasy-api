package com.f1fantasy.dto;

import com.f1fantasy.entity.RaceStatus;
import java.time.LocalDateTime;

public record RaceResponse(
        Long id,
        String name,
        String circuit,
        LocalDateTime date,
        Integer season,
        RaceStatus status) {
}

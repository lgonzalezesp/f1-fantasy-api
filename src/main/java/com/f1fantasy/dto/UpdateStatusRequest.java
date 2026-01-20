package com.f1fantasy.dto;

import com.f1fantasy.entity.RaceStatus;

public record UpdateStatusRequest(
        RaceStatus status) {
}

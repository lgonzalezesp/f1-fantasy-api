package com.f1fantasy.controller;

import com.f1fantasy.dto.CreateRaceRequest;
import com.f1fantasy.dto.RaceResponse;
import com.f1fantasy.dto.UpdateStatusRequest;
import com.f1fantasy.entity.RaceStatus;
import com.f1fantasy.service.RaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RaceController {

    private final RaceService raceService;

    @GetMapping("/races/next")
    public ResponseEntity<RaceResponse> getNextRace() {
        return ResponseEntity.ok(raceService.getNextRace());
    }

    @GetMapping("/races")
    public ResponseEntity<List<RaceResponse>> getAllRaces(@RequestParam Integer season) {
        return ResponseEntity.ok(raceService.getAllRaces(season));
    }

    @PostMapping("/admin/races")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RaceResponse> createRace(@RequestBody CreateRaceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(raceService.createRace(request));
    }

    @PatchMapping("/admin/races/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RaceResponse> updateStatus(
            @PathVariable Long id,
            @RequestBody(required = false) UpdateStatusRequest body,
            @RequestParam(required = false) RaceStatus status) {
        RaceStatus newStatus = (body != null && body.status() != null) ? body.status() : status;
        if (newStatus == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(raceService.updateRaceStatus(id, newStatus));
    }
}

package com.f1fantasy.service;

import com.f1fantasy.dto.CreateRaceRequest;
import com.f1fantasy.dto.RaceResponse;
import com.f1fantasy.entity.RaceStatus;

import java.util.List;

public interface RaceService {
    RaceResponse getNextRace();

    List<RaceResponse> getAllRaces(Integer season);

    RaceResponse createRace(CreateRaceRequest request);

    RaceResponse updateRaceStatus(Long id, RaceStatus status);
}

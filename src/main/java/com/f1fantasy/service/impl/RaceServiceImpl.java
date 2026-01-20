package com.f1fantasy.service.impl;

import com.f1fantasy.dto.CreateRaceRequest;
import com.f1fantasy.dto.RaceResponse;
import com.f1fantasy.entity.Race;
import com.f1fantasy.entity.RaceStatus;
import com.f1fantasy.repository.RaceRepository;
import com.f1fantasy.service.RaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;

    @Override
    public RaceResponse getNextRace() {
        return raceRepository.findFirstByDateAfterOrderByDateAsc(LocalDateTime.now())
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Override
    public List<RaceResponse> getAllRaces(Integer season) {
        return raceRepository.findAllBySeasonOrderByDateAsc(season).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RaceResponse createRace(CreateRaceRequest request) {
        Race race = Race.builder()
                .name(request.name())
                .circuit(request.circuit())
                .date(request.date())
                .season(request.season())
                .status(RaceStatus.SCHEDULED)
                .build();
        return mapToResponse(raceRepository.save(race));
    }

    @Override
    public RaceResponse updateRaceStatus(Long id, RaceStatus status) {
        Race race = raceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Race not found"));

        // Simple state transition validation could go here if needed
        race.setStatus(status);
        return mapToResponse(raceRepository.save(race));
    }

    private RaceResponse mapToResponse(Race race) {
        return new RaceResponse(
                race.getId(),
                race.getName(),
                race.getCircuit(),
                race.getDate(),
                race.getSeason(),
                race.getStatus());
    }
}

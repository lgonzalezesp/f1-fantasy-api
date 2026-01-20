package com.f1fantasy.repository;

import com.f1fantasy.entity.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RaceRepository extends JpaRepository<Race, Long> {
    Optional<Race> findFirstByDateAfterOrderByDateAsc(LocalDateTime date);

    List<Race> findAllBySeasonOrderByDateAsc(Integer season);
}

package tech.tomberg.tombergapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.tomberg.tombergapi.entity.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
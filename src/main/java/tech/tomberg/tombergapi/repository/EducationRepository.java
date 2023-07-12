package tech.tomberg.tombergapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.tomberg.tombergapi.entity.Education;

public interface EducationRepository extends JpaRepository<Education, Long> {
}
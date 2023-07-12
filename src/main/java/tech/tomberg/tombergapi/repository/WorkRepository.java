package tech.tomberg.tombergapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.tomberg.tombergapi.entity.Work;

public interface WorkRepository extends JpaRepository<Work, Long> {
}
package tech.tomberg.tombergapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.tomberg.tombergapi.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
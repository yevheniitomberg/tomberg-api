package tech.tomberg.tombergapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.tomberg.tombergapi.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
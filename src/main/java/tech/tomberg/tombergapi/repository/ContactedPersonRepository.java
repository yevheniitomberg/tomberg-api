package tech.tomberg.tombergapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.tomberg.tombergapi.entity.ContactedPerson;

public interface ContactedPersonRepository extends JpaRepository<ContactedPerson, String> {
}
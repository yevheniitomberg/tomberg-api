package tech.tomberg.tombergapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.tomberg.tombergapi.entity.ContactedPerson;

@RepositoryRestResource(exported = false)
public interface ContactedPersonRepository extends JpaRepository<ContactedPerson, String> {
}
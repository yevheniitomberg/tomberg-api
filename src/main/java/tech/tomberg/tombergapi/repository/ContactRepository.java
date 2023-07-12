package tech.tomberg.tombergapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.tomberg.tombergapi.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
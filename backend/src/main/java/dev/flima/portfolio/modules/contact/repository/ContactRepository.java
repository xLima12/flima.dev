package dev.flima.portfolio.modules.contact.repository;

import dev.flima.portfolio.modules.contact.model.ContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<ContactModel, UUID> {
}

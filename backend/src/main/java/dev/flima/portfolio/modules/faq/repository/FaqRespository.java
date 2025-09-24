package dev.flima.portfolio.modules.faq.repository;

import dev.flima.portfolio.modules.faq.model.FaqModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FaqRespository extends JpaRepository<FaqModel, UUID> {
}

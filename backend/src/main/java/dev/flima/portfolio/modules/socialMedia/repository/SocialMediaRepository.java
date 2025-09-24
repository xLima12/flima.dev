package dev.flima.portfolio.modules.socialMedia.repository;

import dev.flima.portfolio.modules.socialMedia.model.SocialMediaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SocialMediaRepository extends JpaRepository<SocialMediaModel, UUID> {
}

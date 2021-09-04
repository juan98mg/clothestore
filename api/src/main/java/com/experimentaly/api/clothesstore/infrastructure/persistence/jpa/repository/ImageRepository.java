package com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.repository;

import java.util.UUID;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, UUID> {

}

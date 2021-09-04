package com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.repository;

import java.util.Optional;
import java.util.UUID;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, UUID> {


    Optional<CountryEntity> findByNameAndDeletedAtIsNull(String name);


}

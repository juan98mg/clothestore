package com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.repository;

import java.util.UUID;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository
        extends JpaRepository<ProductEntity, UUID>, JpaSpecificationExecutor<ProductEntity> {

    @Query(value = "SELECT AVG(times_searched) From tbl_products", nativeQuery = true)
    float getAveragePopularity();

}

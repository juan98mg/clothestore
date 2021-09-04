package com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.repository;

import java.util.UUID;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, UUID> {


    @Modifying
    @Query(value = "delete from tbl_images where product_id =:productId", nativeQuery = true)
    void delteByProduct(@Param("productId") UUID productId);

}

package com.demo.crud.repository;

import com.demo.crud.domain.RefRatingCrr;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefRatingCrr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefRatingCrrRepository extends JpaRepository<RefRatingCrr, Long> {

}

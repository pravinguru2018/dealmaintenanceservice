package com.demo.crud.repository;

import com.demo.crud.domain.RefRatingFitch;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefRatingFitch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefRatingFitchRepository extends JpaRepository<RefRatingFitch, Long> {

}

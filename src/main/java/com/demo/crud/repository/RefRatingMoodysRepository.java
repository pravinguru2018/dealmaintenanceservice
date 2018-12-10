package com.demo.crud.repository;

import com.demo.crud.domain.RefRatingMoodys;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefRatingMoodys entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefRatingMoodysRepository extends JpaRepository<RefRatingMoodys, Long> {

}

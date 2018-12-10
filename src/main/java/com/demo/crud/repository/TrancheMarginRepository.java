package com.demo.crud.repository;

import com.demo.crud.domain.TrancheMargin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TrancheMargin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrancheMarginRepository extends JpaRepository<TrancheMargin, Long> {

}

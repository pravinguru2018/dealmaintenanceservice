package com.demo.crud.repository;

import com.demo.crud.domain.Deal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Deal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {

}

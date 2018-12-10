package com.demo.crud.repository;

import com.demo.crud.domain.RefDealStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefDealStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefDealStatusRepository extends JpaRepository<RefDealStatus, Long> {

}

package com.demo.crud.repository;

import com.demo.crud.domain.RefBookingEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefBookingEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefBookingEntityRepository extends JpaRepository<RefBookingEntity, Long> {

}

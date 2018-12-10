package com.demo.crud.repository;

import com.demo.crud.domain.RefFacilityType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefFacilityType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefFacilityTypeRepository extends JpaRepository<RefFacilityType, Long> {

}

package com.demo.crud.repository;

import com.demo.crud.domain.RefPraSector;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefPraSector entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefPraSectorRepository extends JpaRepository<RefPraSector, Long> {

}

package com.demo.crud.repository;

import com.demo.crud.domain.RefCovenant;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefCovenant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefCovenantRepository extends JpaRepository<RefCovenant, Long> {

}

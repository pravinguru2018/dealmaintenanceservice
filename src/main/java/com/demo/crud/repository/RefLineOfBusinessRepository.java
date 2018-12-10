package com.demo.crud.repository;

import com.demo.crud.domain.RefLineOfBusiness;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefLineOfBusiness entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefLineOfBusinessRepository extends JpaRepository<RefLineOfBusiness, Long> {

}

package com.demo.crud.repository;

import com.demo.crud.domain.RefCountry;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefCountry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefCountryRepository extends JpaRepository<RefCountry, Long> {

}

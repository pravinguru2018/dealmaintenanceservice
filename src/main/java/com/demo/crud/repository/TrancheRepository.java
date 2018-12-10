package com.demo.crud.repository;

import com.demo.crud.domain.Tranche;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Tranche entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrancheRepository extends JpaRepository<Tranche, Long> {

}

package com.demo.crud.repository;

import com.demo.crud.domain.RefRatingSAndP;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefRatingSAndP entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefRatingSAndPRepository extends JpaRepository<RefRatingSAndP, Long> {

}

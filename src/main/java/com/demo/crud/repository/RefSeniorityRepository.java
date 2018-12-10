package com.demo.crud.repository;

import com.demo.crud.domain.RefSeniority;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefSeniority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefSeniorityRepository extends JpaRepository<RefSeniority, Long> {

}

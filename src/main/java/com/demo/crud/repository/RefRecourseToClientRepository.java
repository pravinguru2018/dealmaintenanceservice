package com.demo.crud.repository;

import com.demo.crud.domain.RefRecourseToClient;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefRecourseToClient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefRecourseToClientRepository extends JpaRepository<RefRecourseToClient, Long> {

}

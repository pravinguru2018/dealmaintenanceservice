package com.demo.crud.repository;

import com.demo.crud.domain.RefOriginationTeam;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefOriginationTeam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefOriginationTeamRepository extends JpaRepository<RefOriginationTeam, Long> {

}

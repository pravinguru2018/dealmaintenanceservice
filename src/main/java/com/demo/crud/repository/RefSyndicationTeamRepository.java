package com.demo.crud.repository;

import com.demo.crud.domain.RefSyndicationTeam;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefSyndicationTeam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefSyndicationTeamRepository extends JpaRepository<RefSyndicationTeam, Long> {

}

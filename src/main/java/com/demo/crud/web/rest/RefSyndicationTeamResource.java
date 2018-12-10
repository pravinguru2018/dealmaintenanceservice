package com.demo.crud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.crud.domain.RefSyndicationTeam;
import com.demo.crud.repository.RefSyndicationTeamRepository;
import com.demo.crud.web.rest.errors.BadRequestAlertException;
import com.demo.crud.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RefSyndicationTeam.
 */
@RestController
@RequestMapping("/api")
public class RefSyndicationTeamResource {

    private final Logger log = LoggerFactory.getLogger(RefSyndicationTeamResource.class);

    private static final String ENTITY_NAME = "refSyndicationTeam";

    private final RefSyndicationTeamRepository refSyndicationTeamRepository;

    public RefSyndicationTeamResource(RefSyndicationTeamRepository refSyndicationTeamRepository) {
        this.refSyndicationTeamRepository = refSyndicationTeamRepository;
    }

    /**
     * POST  /ref-syndication-teams : Create a new refSyndicationTeam.
     *
     * @param refSyndicationTeam the refSyndicationTeam to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refSyndicationTeam, or with status 400 (Bad Request) if the refSyndicationTeam has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-syndication-teams")
    @Timed
    public ResponseEntity<RefSyndicationTeam> createRefSyndicationTeam(@RequestBody RefSyndicationTeam refSyndicationTeam) throws URISyntaxException {
        log.debug("REST request to save RefSyndicationTeam : {}", refSyndicationTeam);
        if (refSyndicationTeam.getId() != null) {
            throw new BadRequestAlertException("A new refSyndicationTeam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefSyndicationTeam result = refSyndicationTeamRepository.save(refSyndicationTeam);
        return ResponseEntity.created(new URI("/api/ref-syndication-teams/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-syndication-teams : Updates an existing refSyndicationTeam.
     *
     * @param refSyndicationTeam the refSyndicationTeam to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refSyndicationTeam,
     * or with status 400 (Bad Request) if the refSyndicationTeam is not valid,
     * or with status 500 (Internal Server Error) if the refSyndicationTeam couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-syndication-teams")
    @Timed
    public ResponseEntity<RefSyndicationTeam> updateRefSyndicationTeam(@RequestBody RefSyndicationTeam refSyndicationTeam) throws URISyntaxException {
        log.debug("REST request to update RefSyndicationTeam : {}", refSyndicationTeam);
        if (refSyndicationTeam.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefSyndicationTeam result = refSyndicationTeamRepository.save(refSyndicationTeam);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refSyndicationTeam.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-syndication-teams : get all the refSyndicationTeams.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refSyndicationTeams in body
     */
    @GetMapping("/ref-syndication-teams")
    @Timed
    public List<RefSyndicationTeam> getAllRefSyndicationTeams() {
        log.debug("REST request to get all RefSyndicationTeams");
        return refSyndicationTeamRepository.findAll();
    }

    /**
     * GET  /ref-syndication-teams/:id : get the "id" refSyndicationTeam.
     *
     * @param id the id of the refSyndicationTeam to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refSyndicationTeam, or with status 404 (Not Found)
     */
    @GetMapping("/ref-syndication-teams/{id}")
    @Timed
    public ResponseEntity<RefSyndicationTeam> getRefSyndicationTeam(@PathVariable Long id) {
        log.debug("REST request to get RefSyndicationTeam : {}", id);
        Optional<RefSyndicationTeam> refSyndicationTeam = refSyndicationTeamRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(refSyndicationTeam);
    }

    /**
     * DELETE  /ref-syndication-teams/:id : delete the "id" refSyndicationTeam.
     *
     * @param id the id of the refSyndicationTeam to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-syndication-teams/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefSyndicationTeam(@PathVariable Long id) {
        log.debug("REST request to delete RefSyndicationTeam : {}", id);

        refSyndicationTeamRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

package com.demo.crud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.crud.domain.RefOriginationTeam;
import com.demo.crud.repository.RefOriginationTeamRepository;
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
 * REST controller for managing RefOriginationTeam.
 */
@RestController
@RequestMapping("/api")
public class RefOriginationTeamResource {

    private final Logger log = LoggerFactory.getLogger(RefOriginationTeamResource.class);

    private static final String ENTITY_NAME = "refOriginationTeam";

    private final RefOriginationTeamRepository refOriginationTeamRepository;

    public RefOriginationTeamResource(RefOriginationTeamRepository refOriginationTeamRepository) {
        this.refOriginationTeamRepository = refOriginationTeamRepository;
    }

    /**
     * POST  /ref-origination-teams : Create a new refOriginationTeam.
     *
     * @param refOriginationTeam the refOriginationTeam to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refOriginationTeam, or with status 400 (Bad Request) if the refOriginationTeam has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-origination-teams")
    @Timed
    public ResponseEntity<RefOriginationTeam> createRefOriginationTeam(@RequestBody RefOriginationTeam refOriginationTeam) throws URISyntaxException {
        log.debug("REST request to save RefOriginationTeam : {}", refOriginationTeam);
        if (refOriginationTeam.getId() != null) {
            throw new BadRequestAlertException("A new refOriginationTeam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefOriginationTeam result = refOriginationTeamRepository.save(refOriginationTeam);
        return ResponseEntity.created(new URI("/api/ref-origination-teams/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-origination-teams : Updates an existing refOriginationTeam.
     *
     * @param refOriginationTeam the refOriginationTeam to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refOriginationTeam,
     * or with status 400 (Bad Request) if the refOriginationTeam is not valid,
     * or with status 500 (Internal Server Error) if the refOriginationTeam couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-origination-teams")
    @Timed
    public ResponseEntity<RefOriginationTeam> updateRefOriginationTeam(@RequestBody RefOriginationTeam refOriginationTeam) throws URISyntaxException {
        log.debug("REST request to update RefOriginationTeam : {}", refOriginationTeam);
        if (refOriginationTeam.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefOriginationTeam result = refOriginationTeamRepository.save(refOriginationTeam);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refOriginationTeam.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-origination-teams : get all the refOriginationTeams.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refOriginationTeams in body
     */
    @GetMapping("/ref-origination-teams")
    @Timed
    public List<RefOriginationTeam> getAllRefOriginationTeams() {
        log.debug("REST request to get all RefOriginationTeams");
        return refOriginationTeamRepository.findAll();
    }

    /**
     * GET  /ref-origination-teams/:id : get the "id" refOriginationTeam.
     *
     * @param id the id of the refOriginationTeam to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refOriginationTeam, or with status 404 (Not Found)
     */
    @GetMapping("/ref-origination-teams/{id}")
    @Timed
    public ResponseEntity<RefOriginationTeam> getRefOriginationTeam(@PathVariable Long id) {
        log.debug("REST request to get RefOriginationTeam : {}", id);
        Optional<RefOriginationTeam> refOriginationTeam = refOriginationTeamRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(refOriginationTeam);
    }

    /**
     * DELETE  /ref-origination-teams/:id : delete the "id" refOriginationTeam.
     *
     * @param id the id of the refOriginationTeam to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-origination-teams/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefOriginationTeam(@PathVariable Long id) {
        log.debug("REST request to delete RefOriginationTeam : {}", id);

        refOriginationTeamRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

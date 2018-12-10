package com.demo.crud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.crud.domain.RefSeniority;
import com.demo.crud.repository.RefSeniorityRepository;
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
 * REST controller for managing RefSeniority.
 */
@RestController
@RequestMapping("/api")
public class RefSeniorityResource {

    private final Logger log = LoggerFactory.getLogger(RefSeniorityResource.class);

    private static final String ENTITY_NAME = "refSeniority";

    private final RefSeniorityRepository refSeniorityRepository;

    public RefSeniorityResource(RefSeniorityRepository refSeniorityRepository) {
        this.refSeniorityRepository = refSeniorityRepository;
    }

    /**
     * POST  /ref-seniorities : Create a new refSeniority.
     *
     * @param refSeniority the refSeniority to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refSeniority, or with status 400 (Bad Request) if the refSeniority has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-seniorities")
    @Timed
    public ResponseEntity<RefSeniority> createRefSeniority(@RequestBody RefSeniority refSeniority) throws URISyntaxException {
        log.debug("REST request to save RefSeniority : {}", refSeniority);
        if (refSeniority.getId() != null) {
            throw new BadRequestAlertException("A new refSeniority cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefSeniority result = refSeniorityRepository.save(refSeniority);
        return ResponseEntity.created(new URI("/api/ref-seniorities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-seniorities : Updates an existing refSeniority.
     *
     * @param refSeniority the refSeniority to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refSeniority,
     * or with status 400 (Bad Request) if the refSeniority is not valid,
     * or with status 500 (Internal Server Error) if the refSeniority couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-seniorities")
    @Timed
    public ResponseEntity<RefSeniority> updateRefSeniority(@RequestBody RefSeniority refSeniority) throws URISyntaxException {
        log.debug("REST request to update RefSeniority : {}", refSeniority);
        if (refSeniority.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefSeniority result = refSeniorityRepository.save(refSeniority);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refSeniority.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-seniorities : get all the refSeniorities.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refSeniorities in body
     */
    @GetMapping("/ref-seniorities")
    @Timed
    public List<RefSeniority> getAllRefSeniorities() {
        log.debug("REST request to get all RefSeniorities");
        return refSeniorityRepository.findAll();
    }

    /**
     * GET  /ref-seniorities/:id : get the "id" refSeniority.
     *
     * @param id the id of the refSeniority to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refSeniority, or with status 404 (Not Found)
     */
    @GetMapping("/ref-seniorities/{id}")
    @Timed
    public ResponseEntity<RefSeniority> getRefSeniority(@PathVariable Long id) {
        log.debug("REST request to get RefSeniority : {}", id);
        Optional<RefSeniority> refSeniority = refSeniorityRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(refSeniority);
    }

    /**
     * DELETE  /ref-seniorities/:id : delete the "id" refSeniority.
     *
     * @param id the id of the refSeniority to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-seniorities/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefSeniority(@PathVariable Long id) {
        log.debug("REST request to delete RefSeniority : {}", id);

        refSeniorityRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

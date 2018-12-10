package com.demo.crud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.crud.domain.RefRatingCrr;
import com.demo.crud.repository.RefRatingCrrRepository;
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
 * REST controller for managing RefRatingCrr.
 */
@RestController
@RequestMapping("/api")
public class RefRatingCrrResource {

    private final Logger log = LoggerFactory.getLogger(RefRatingCrrResource.class);

    private static final String ENTITY_NAME = "refRatingCrr";

    private final RefRatingCrrRepository refRatingCrrRepository;

    public RefRatingCrrResource(RefRatingCrrRepository refRatingCrrRepository) {
        this.refRatingCrrRepository = refRatingCrrRepository;
    }

    /**
     * POST  /ref-rating-crrs : Create a new refRatingCrr.
     *
     * @param refRatingCrr the refRatingCrr to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refRatingCrr, or with status 400 (Bad Request) if the refRatingCrr has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-rating-crrs")
    @Timed
    public ResponseEntity<RefRatingCrr> createRefRatingCrr(@RequestBody RefRatingCrr refRatingCrr) throws URISyntaxException {
        log.debug("REST request to save RefRatingCrr : {}", refRatingCrr);
        if (refRatingCrr.getId() != null) {
            throw new BadRequestAlertException("A new refRatingCrr cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefRatingCrr result = refRatingCrrRepository.save(refRatingCrr);
        return ResponseEntity.created(new URI("/api/ref-rating-crrs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-rating-crrs : Updates an existing refRatingCrr.
     *
     * @param refRatingCrr the refRatingCrr to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refRatingCrr,
     * or with status 400 (Bad Request) if the refRatingCrr is not valid,
     * or with status 500 (Internal Server Error) if the refRatingCrr couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-rating-crrs")
    @Timed
    public ResponseEntity<RefRatingCrr> updateRefRatingCrr(@RequestBody RefRatingCrr refRatingCrr) throws URISyntaxException {
        log.debug("REST request to update RefRatingCrr : {}", refRatingCrr);
        if (refRatingCrr.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefRatingCrr result = refRatingCrrRepository.save(refRatingCrr);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refRatingCrr.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-rating-crrs : get all the refRatingCrrs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refRatingCrrs in body
     */
    @GetMapping("/ref-rating-crrs")
    @Timed
    public List<RefRatingCrr> getAllRefRatingCrrs() {
        log.debug("REST request to get all RefRatingCrrs");
        return refRatingCrrRepository.findAll();
    }

    /**
     * GET  /ref-rating-crrs/:id : get the "id" refRatingCrr.
     *
     * @param id the id of the refRatingCrr to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refRatingCrr, or with status 404 (Not Found)
     */
    @GetMapping("/ref-rating-crrs/{id}")
    @Timed
    public ResponseEntity<RefRatingCrr> getRefRatingCrr(@PathVariable Long id) {
        log.debug("REST request to get RefRatingCrr : {}", id);
        Optional<RefRatingCrr> refRatingCrr = refRatingCrrRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(refRatingCrr);
    }

    /**
     * DELETE  /ref-rating-crrs/:id : delete the "id" refRatingCrr.
     *
     * @param id the id of the refRatingCrr to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-rating-crrs/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefRatingCrr(@PathVariable Long id) {
        log.debug("REST request to delete RefRatingCrr : {}", id);

        refRatingCrrRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

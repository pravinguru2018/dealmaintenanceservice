package com.demo.crud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.crud.domain.RefLineOfBusiness;
import com.demo.crud.repository.RefLineOfBusinessRepository;
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
 * REST controller for managing RefLineOfBusiness.
 */
@RestController
@RequestMapping("/api")
public class RefLineOfBusinessResource {

    private final Logger log = LoggerFactory.getLogger(RefLineOfBusinessResource.class);

    private static final String ENTITY_NAME = "refLineOfBusiness";

    private final RefLineOfBusinessRepository refLineOfBusinessRepository;

    public RefLineOfBusinessResource(RefLineOfBusinessRepository refLineOfBusinessRepository) {
        this.refLineOfBusinessRepository = refLineOfBusinessRepository;
    }

    /**
     * POST  /ref-line-of-businesses : Create a new refLineOfBusiness.
     *
     * @param refLineOfBusiness the refLineOfBusiness to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refLineOfBusiness, or with status 400 (Bad Request) if the refLineOfBusiness has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-line-of-businesses")
    @Timed
    public ResponseEntity<RefLineOfBusiness> createRefLineOfBusiness(@RequestBody RefLineOfBusiness refLineOfBusiness) throws URISyntaxException {
        log.debug("REST request to save RefLineOfBusiness : {}", refLineOfBusiness);
        if (refLineOfBusiness.getId() != null) {
            throw new BadRequestAlertException("A new refLineOfBusiness cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefLineOfBusiness result = refLineOfBusinessRepository.save(refLineOfBusiness);
        return ResponseEntity.created(new URI("/api/ref-line-of-businesses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-line-of-businesses : Updates an existing refLineOfBusiness.
     *
     * @param refLineOfBusiness the refLineOfBusiness to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refLineOfBusiness,
     * or with status 400 (Bad Request) if the refLineOfBusiness is not valid,
     * or with status 500 (Internal Server Error) if the refLineOfBusiness couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-line-of-businesses")
    @Timed
    public ResponseEntity<RefLineOfBusiness> updateRefLineOfBusiness(@RequestBody RefLineOfBusiness refLineOfBusiness) throws URISyntaxException {
        log.debug("REST request to update RefLineOfBusiness : {}", refLineOfBusiness);
        if (refLineOfBusiness.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefLineOfBusiness result = refLineOfBusinessRepository.save(refLineOfBusiness);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refLineOfBusiness.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-line-of-businesses : get all the refLineOfBusinesses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refLineOfBusinesses in body
     */
    @GetMapping("/ref-line-of-businesses")
    @Timed
    public List<RefLineOfBusiness> getAllRefLineOfBusinesses() {
        log.debug("REST request to get all RefLineOfBusinesses");
        return refLineOfBusinessRepository.findAll();
    }

    /**
     * GET  /ref-line-of-businesses/:id : get the "id" refLineOfBusiness.
     *
     * @param id the id of the refLineOfBusiness to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refLineOfBusiness, or with status 404 (Not Found)
     */
    @GetMapping("/ref-line-of-businesses/{id}")
    @Timed
    public ResponseEntity<RefLineOfBusiness> getRefLineOfBusiness(@PathVariable Long id) {
        log.debug("REST request to get RefLineOfBusiness : {}", id);
        Optional<RefLineOfBusiness> refLineOfBusiness = refLineOfBusinessRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(refLineOfBusiness);
    }

    /**
     * DELETE  /ref-line-of-businesses/:id : delete the "id" refLineOfBusiness.
     *
     * @param id the id of the refLineOfBusiness to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-line-of-businesses/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefLineOfBusiness(@PathVariable Long id) {
        log.debug("REST request to delete RefLineOfBusiness : {}", id);

        refLineOfBusinessRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

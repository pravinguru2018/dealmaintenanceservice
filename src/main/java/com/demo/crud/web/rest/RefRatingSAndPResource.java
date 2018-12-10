package com.demo.crud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.crud.domain.RefRatingSAndP;
import com.demo.crud.repository.RefRatingSAndPRepository;
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
 * REST controller for managing RefRatingSAndP.
 */
@RestController
@RequestMapping("/api")
public class RefRatingSAndPResource {

    private final Logger log = LoggerFactory.getLogger(RefRatingSAndPResource.class);

    private static final String ENTITY_NAME = "refRatingSAndP";

    private final RefRatingSAndPRepository refRatingSAndPRepository;

    public RefRatingSAndPResource(RefRatingSAndPRepository refRatingSAndPRepository) {
        this.refRatingSAndPRepository = refRatingSAndPRepository;
    }

    /**
     * POST  /ref-rating-s-and-ps : Create a new refRatingSAndP.
     *
     * @param refRatingSAndP the refRatingSAndP to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refRatingSAndP, or with status 400 (Bad Request) if the refRatingSAndP has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-rating-s-and-ps")
    @Timed
    public ResponseEntity<RefRatingSAndP> createRefRatingSAndP(@RequestBody RefRatingSAndP refRatingSAndP) throws URISyntaxException {
        log.debug("REST request to save RefRatingSAndP : {}", refRatingSAndP);
        if (refRatingSAndP.getId() != null) {
            throw new BadRequestAlertException("A new refRatingSAndP cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefRatingSAndP result = refRatingSAndPRepository.save(refRatingSAndP);
        return ResponseEntity.created(new URI("/api/ref-rating-s-and-ps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-rating-s-and-ps : Updates an existing refRatingSAndP.
     *
     * @param refRatingSAndP the refRatingSAndP to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refRatingSAndP,
     * or with status 400 (Bad Request) if the refRatingSAndP is not valid,
     * or with status 500 (Internal Server Error) if the refRatingSAndP couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-rating-s-and-ps")
    @Timed
    public ResponseEntity<RefRatingSAndP> updateRefRatingSAndP(@RequestBody RefRatingSAndP refRatingSAndP) throws URISyntaxException {
        log.debug("REST request to update RefRatingSAndP : {}", refRatingSAndP);
        if (refRatingSAndP.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefRatingSAndP result = refRatingSAndPRepository.save(refRatingSAndP);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refRatingSAndP.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-rating-s-and-ps : get all the refRatingSAndPS.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refRatingSAndPS in body
     */
    @GetMapping("/ref-rating-s-and-ps")
    @Timed
    public List<RefRatingSAndP> getAllRefRatingSAndPS() {
        log.debug("REST request to get all RefRatingSAndPS");
        return refRatingSAndPRepository.findAll();
    }

    /**
     * GET  /ref-rating-s-and-ps/:id : get the "id" refRatingSAndP.
     *
     * @param id the id of the refRatingSAndP to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refRatingSAndP, or with status 404 (Not Found)
     */
    @GetMapping("/ref-rating-s-and-ps/{id}")
    @Timed
    public ResponseEntity<RefRatingSAndP> getRefRatingSAndP(@PathVariable Long id) {
        log.debug("REST request to get RefRatingSAndP : {}", id);
        Optional<RefRatingSAndP> refRatingSAndP = refRatingSAndPRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(refRatingSAndP);
    }

    /**
     * DELETE  /ref-rating-s-and-ps/:id : delete the "id" refRatingSAndP.
     *
     * @param id the id of the refRatingSAndP to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-rating-s-and-ps/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefRatingSAndP(@PathVariable Long id) {
        log.debug("REST request to delete RefRatingSAndP : {}", id);

        refRatingSAndPRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

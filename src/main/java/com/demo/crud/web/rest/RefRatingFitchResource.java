package com.demo.crud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.crud.domain.RefRatingFitch;
import com.demo.crud.repository.RefRatingFitchRepository;
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
 * REST controller for managing RefRatingFitch.
 */
@RestController
@RequestMapping("/api")
public class RefRatingFitchResource {

    private final Logger log = LoggerFactory.getLogger(RefRatingFitchResource.class);

    private static final String ENTITY_NAME = "refRatingFitch";

    private final RefRatingFitchRepository refRatingFitchRepository;

    public RefRatingFitchResource(RefRatingFitchRepository refRatingFitchRepository) {
        this.refRatingFitchRepository = refRatingFitchRepository;
    }

    /**
     * POST  /ref-rating-fitches : Create a new refRatingFitch.
     *
     * @param refRatingFitch the refRatingFitch to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refRatingFitch, or with status 400 (Bad Request) if the refRatingFitch has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-rating-fitches")
    @Timed
    public ResponseEntity<RefRatingFitch> createRefRatingFitch(@RequestBody RefRatingFitch refRatingFitch) throws URISyntaxException {
        log.debug("REST request to save RefRatingFitch : {}", refRatingFitch);
        if (refRatingFitch.getId() != null) {
            throw new BadRequestAlertException("A new refRatingFitch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefRatingFitch result = refRatingFitchRepository.save(refRatingFitch);
        return ResponseEntity.created(new URI("/api/ref-rating-fitches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-rating-fitches : Updates an existing refRatingFitch.
     *
     * @param refRatingFitch the refRatingFitch to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refRatingFitch,
     * or with status 400 (Bad Request) if the refRatingFitch is not valid,
     * or with status 500 (Internal Server Error) if the refRatingFitch couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-rating-fitches")
    @Timed
    public ResponseEntity<RefRatingFitch> updateRefRatingFitch(@RequestBody RefRatingFitch refRatingFitch) throws URISyntaxException {
        log.debug("REST request to update RefRatingFitch : {}", refRatingFitch);
        if (refRatingFitch.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefRatingFitch result = refRatingFitchRepository.save(refRatingFitch);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refRatingFitch.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-rating-fitches : get all the refRatingFitches.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refRatingFitches in body
     */
    @GetMapping("/ref-rating-fitches")
    @Timed
    public List<RefRatingFitch> getAllRefRatingFitches() {
        log.debug("REST request to get all RefRatingFitches");
        return refRatingFitchRepository.findAll();
    }

    /**
     * GET  /ref-rating-fitches/:id : get the "id" refRatingFitch.
     *
     * @param id the id of the refRatingFitch to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refRatingFitch, or with status 404 (Not Found)
     */
    @GetMapping("/ref-rating-fitches/{id}")
    @Timed
    public ResponseEntity<RefRatingFitch> getRefRatingFitch(@PathVariable Long id) {
        log.debug("REST request to get RefRatingFitch : {}", id);
        Optional<RefRatingFitch> refRatingFitch = refRatingFitchRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(refRatingFitch);
    }

    /**
     * DELETE  /ref-rating-fitches/:id : delete the "id" refRatingFitch.
     *
     * @param id the id of the refRatingFitch to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-rating-fitches/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefRatingFitch(@PathVariable Long id) {
        log.debug("REST request to delete RefRatingFitch : {}", id);

        refRatingFitchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

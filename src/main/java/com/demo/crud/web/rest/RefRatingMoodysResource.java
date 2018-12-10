package com.demo.crud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.crud.domain.RefRatingMoodys;
import com.demo.crud.repository.RefRatingMoodysRepository;
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
 * REST controller for managing RefRatingMoodys.
 */
@RestController
@RequestMapping("/api")
public class RefRatingMoodysResource {

    private final Logger log = LoggerFactory.getLogger(RefRatingMoodysResource.class);

    private static final String ENTITY_NAME = "refRatingMoodys";

    private final RefRatingMoodysRepository refRatingMoodysRepository;

    public RefRatingMoodysResource(RefRatingMoodysRepository refRatingMoodysRepository) {
        this.refRatingMoodysRepository = refRatingMoodysRepository;
    }

    /**
     * POST  /ref-rating-moodys : Create a new refRatingMoodys.
     *
     * @param refRatingMoodys the refRatingMoodys to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refRatingMoodys, or with status 400 (Bad Request) if the refRatingMoodys has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-rating-moodys")
    @Timed
    public ResponseEntity<RefRatingMoodys> createRefRatingMoodys(@RequestBody RefRatingMoodys refRatingMoodys) throws URISyntaxException {
        log.debug("REST request to save RefRatingMoodys : {}", refRatingMoodys);
        if (refRatingMoodys.getId() != null) {
            throw new BadRequestAlertException("A new refRatingMoodys cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefRatingMoodys result = refRatingMoodysRepository.save(refRatingMoodys);
        return ResponseEntity.created(new URI("/api/ref-rating-moodys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-rating-moodys : Updates an existing refRatingMoodys.
     *
     * @param refRatingMoodys the refRatingMoodys to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refRatingMoodys,
     * or with status 400 (Bad Request) if the refRatingMoodys is not valid,
     * or with status 500 (Internal Server Error) if the refRatingMoodys couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-rating-moodys")
    @Timed
    public ResponseEntity<RefRatingMoodys> updateRefRatingMoodys(@RequestBody RefRatingMoodys refRatingMoodys) throws URISyntaxException {
        log.debug("REST request to update RefRatingMoodys : {}", refRatingMoodys);
        if (refRatingMoodys.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefRatingMoodys result = refRatingMoodysRepository.save(refRatingMoodys);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refRatingMoodys.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-rating-moodys : get all the refRatingMoodys.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refRatingMoodys in body
     */
    @GetMapping("/ref-rating-moodys")
    @Timed
    public List<RefRatingMoodys> getAllRefRatingMoodys() {
        log.debug("REST request to get all RefRatingMoodys");
        return refRatingMoodysRepository.findAll();
    }

    /**
     * GET  /ref-rating-moodys/:id : get the "id" refRatingMoodys.
     *
     * @param id the id of the refRatingMoodys to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refRatingMoodys, or with status 404 (Not Found)
     */
    @GetMapping("/ref-rating-moodys/{id}")
    @Timed
    public ResponseEntity<RefRatingMoodys> getRefRatingMoodys(@PathVariable Long id) {
        log.debug("REST request to get RefRatingMoodys : {}", id);
        Optional<RefRatingMoodys> refRatingMoodys = refRatingMoodysRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(refRatingMoodys);
    }

    /**
     * DELETE  /ref-rating-moodys/:id : delete the "id" refRatingMoodys.
     *
     * @param id the id of the refRatingMoodys to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-rating-moodys/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefRatingMoodys(@PathVariable Long id) {
        log.debug("REST request to delete RefRatingMoodys : {}", id);

        refRatingMoodysRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

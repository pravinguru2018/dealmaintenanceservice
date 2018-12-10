package com.demo.crud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.crud.domain.RefDealStatus;
import com.demo.crud.repository.RefDealStatusRepository;
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
 * REST controller for managing RefDealStatus.
 */
@RestController
@RequestMapping("/api")
public class RefDealStatusResource {

    private final Logger log = LoggerFactory.getLogger(RefDealStatusResource.class);

    private static final String ENTITY_NAME = "refDealStatus";

    private final RefDealStatusRepository refDealStatusRepository;

    public RefDealStatusResource(RefDealStatusRepository refDealStatusRepository) {
        this.refDealStatusRepository = refDealStatusRepository;
    }

    /**
     * POST  /ref-deal-statuses : Create a new refDealStatus.
     *
     * @param refDealStatus the refDealStatus to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refDealStatus, or with status 400 (Bad Request) if the refDealStatus has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-deal-statuses")
    @Timed
    public ResponseEntity<RefDealStatus> createRefDealStatus(@RequestBody RefDealStatus refDealStatus) throws URISyntaxException {
        log.debug("REST request to save RefDealStatus : {}", refDealStatus);
        if (refDealStatus.getId() != null) {
            throw new BadRequestAlertException("A new refDealStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefDealStatus result = refDealStatusRepository.save(refDealStatus);
        return ResponseEntity.created(new URI("/api/ref-deal-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-deal-statuses : Updates an existing refDealStatus.
     *
     * @param refDealStatus the refDealStatus to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refDealStatus,
     * or with status 400 (Bad Request) if the refDealStatus is not valid,
     * or with status 500 (Internal Server Error) if the refDealStatus couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-deal-statuses")
    @Timed
    public ResponseEntity<RefDealStatus> updateRefDealStatus(@RequestBody RefDealStatus refDealStatus) throws URISyntaxException {
        log.debug("REST request to update RefDealStatus : {}", refDealStatus);
        if (refDealStatus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefDealStatus result = refDealStatusRepository.save(refDealStatus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refDealStatus.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-deal-statuses : get all the refDealStatuses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refDealStatuses in body
     */
    @GetMapping("/ref-deal-statuses")
    @Timed
    public List<RefDealStatus> getAllRefDealStatuses() {
        log.debug("REST request to get all RefDealStatuses");
        return refDealStatusRepository.findAll();
    }

    /**
     * GET  /ref-deal-statuses/:id : get the "id" refDealStatus.
     *
     * @param id the id of the refDealStatus to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refDealStatus, or with status 404 (Not Found)
     */
    @GetMapping("/ref-deal-statuses/{id}")
    @Timed
    public ResponseEntity<RefDealStatus> getRefDealStatus(@PathVariable Long id) {
        log.debug("REST request to get RefDealStatus : {}", id);
        Optional<RefDealStatus> refDealStatus = refDealStatusRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(refDealStatus);
    }

    /**
     * DELETE  /ref-deal-statuses/:id : delete the "id" refDealStatus.
     *
     * @param id the id of the refDealStatus to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-deal-statuses/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefDealStatus(@PathVariable Long id) {
        log.debug("REST request to delete RefDealStatus : {}", id);

        refDealStatusRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

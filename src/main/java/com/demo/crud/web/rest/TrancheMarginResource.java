package com.demo.crud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.crud.domain.TrancheMargin;
import com.demo.crud.repository.TrancheMarginRepository;
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
 * REST controller for managing TrancheMargin.
 */
@RestController
@RequestMapping("/api")
public class TrancheMarginResource {

    private final Logger log = LoggerFactory.getLogger(TrancheMarginResource.class);

    private static final String ENTITY_NAME = "trancheMargin";

    private final TrancheMarginRepository trancheMarginRepository;

    public TrancheMarginResource(TrancheMarginRepository trancheMarginRepository) {
        this.trancheMarginRepository = trancheMarginRepository;
    }

    /**
     * POST  /tranche-margins : Create a new trancheMargin.
     *
     * @param trancheMargin the trancheMargin to create
     * @return the ResponseEntity with status 201 (Created) and with body the new trancheMargin, or with status 400 (Bad Request) if the trancheMargin has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tranche-margins")
    @Timed
    public ResponseEntity<TrancheMargin> createTrancheMargin(@RequestBody TrancheMargin trancheMargin) throws URISyntaxException {
        log.debug("REST request to save TrancheMargin : {}", trancheMargin);
        if (trancheMargin.getId() != null) {
            throw new BadRequestAlertException("A new trancheMargin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrancheMargin result = trancheMarginRepository.save(trancheMargin);
        return ResponseEntity.created(new URI("/api/tranche-margins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tranche-margins : Updates an existing trancheMargin.
     *
     * @param trancheMargin the trancheMargin to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated trancheMargin,
     * or with status 400 (Bad Request) if the trancheMargin is not valid,
     * or with status 500 (Internal Server Error) if the trancheMargin couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tranche-margins")
    @Timed
    public ResponseEntity<TrancheMargin> updateTrancheMargin(@RequestBody TrancheMargin trancheMargin) throws URISyntaxException {
        log.debug("REST request to update TrancheMargin : {}", trancheMargin);
        if (trancheMargin.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TrancheMargin result = trancheMarginRepository.save(trancheMargin);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, trancheMargin.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tranche-margins : get all the trancheMargins.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of trancheMargins in body
     */
    @GetMapping("/tranche-margins")
    @Timed
    public List<TrancheMargin> getAllTrancheMargins() {
        log.debug("REST request to get all TrancheMargins");
        return trancheMarginRepository.findAll();
    }

    /**
     * GET  /tranche-margins/:id : get the "id" trancheMargin.
     *
     * @param id the id of the trancheMargin to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the trancheMargin, or with status 404 (Not Found)
     */
    @GetMapping("/tranche-margins/{id}")
    @Timed
    public ResponseEntity<TrancheMargin> getTrancheMargin(@PathVariable Long id) {
        log.debug("REST request to get TrancheMargin : {}", id);
        Optional<TrancheMargin> trancheMargin = trancheMarginRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(trancheMargin);
    }

    /**
     * DELETE  /tranche-margins/:id : delete the "id" trancheMargin.
     *
     * @param id the id of the trancheMargin to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tranche-margins/{id}")
    @Timed
    public ResponseEntity<Void> deleteTrancheMargin(@PathVariable Long id) {
        log.debug("REST request to delete TrancheMargin : {}", id);

        trancheMarginRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

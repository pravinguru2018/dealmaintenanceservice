package com.demo.crud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.crud.domain.Tranche;
import com.demo.crud.repository.TrancheRepository;
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
 * REST controller for managing Tranche.
 */
@RestController
@RequestMapping("/api")
public class TrancheResource {

    private final Logger log = LoggerFactory.getLogger(TrancheResource.class);

    private static final String ENTITY_NAME = "tranche";

    private final TrancheRepository trancheRepository;

    public TrancheResource(TrancheRepository trancheRepository) {
        this.trancheRepository = trancheRepository;
    }

    /**
     * POST  /tranches : Create a new tranche.
     *
     * @param tranche the tranche to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tranche, or with status 400 (Bad Request) if the tranche has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tranches")
    @Timed
    public ResponseEntity<Tranche> createTranche(@RequestBody Tranche tranche) throws URISyntaxException {
        log.debug("REST request to save Tranche : {}", tranche);
        if (tranche.getId() != null) {
            throw new BadRequestAlertException("A new tranche cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Tranche result = trancheRepository.save(tranche);
        return ResponseEntity.created(new URI("/api/tranches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tranches : Updates an existing tranche.
     *
     * @param tranche the tranche to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tranche,
     * or with status 400 (Bad Request) if the tranche is not valid,
     * or with status 500 (Internal Server Error) if the tranche couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tranches")
    @Timed
    public ResponseEntity<Tranche> updateTranche(@RequestBody Tranche tranche) throws URISyntaxException {
        log.debug("REST request to update Tranche : {}", tranche);
        if (tranche.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Tranche result = trancheRepository.save(tranche);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tranche.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tranches : get all the tranches.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tranches in body
     */
    @GetMapping("/tranches")
    @Timed
    public List<Tranche> getAllTranches() {
        log.debug("REST request to get all Tranches");
        return trancheRepository.findAll();
    }

    /**
     * GET  /tranches/:id : get the "id" tranche.
     *
     * @param id the id of the tranche to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tranche, or with status 404 (Not Found)
     */
    @GetMapping("/tranches/{id}")
    @Timed
    public ResponseEntity<Tranche> getTranche(@PathVariable Long id) {
        log.debug("REST request to get Tranche : {}", id);
        Optional<Tranche> tranche = trancheRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tranche);
    }

    /**
     * DELETE  /tranches/:id : delete the "id" tranche.
     *
     * @param id the id of the tranche to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tranches/{id}")
    @Timed
    public ResponseEntity<Void> deleteTranche(@PathVariable Long id) {
        log.debug("REST request to delete Tranche : {}", id);

        trancheRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

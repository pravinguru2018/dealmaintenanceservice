package com.demo.crud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.crud.domain.RefPraSector;
import com.demo.crud.repository.RefPraSectorRepository;
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
 * REST controller for managing RefPraSector.
 */
@RestController
@RequestMapping("/api")
public class RefPraSectorResource {

    private final Logger log = LoggerFactory.getLogger(RefPraSectorResource.class);

    private static final String ENTITY_NAME = "refPraSector";

    private final RefPraSectorRepository refPraSectorRepository;

    public RefPraSectorResource(RefPraSectorRepository refPraSectorRepository) {
        this.refPraSectorRepository = refPraSectorRepository;
    }

    /**
     * POST  /ref-pra-sectors : Create a new refPraSector.
     *
     * @param refPraSector the refPraSector to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refPraSector, or with status 400 (Bad Request) if the refPraSector has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-pra-sectors")
    @Timed
    public ResponseEntity<RefPraSector> createRefPraSector(@RequestBody RefPraSector refPraSector) throws URISyntaxException {
        log.debug("REST request to save RefPraSector : {}", refPraSector);
        if (refPraSector.getId() != null) {
            throw new BadRequestAlertException("A new refPraSector cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefPraSector result = refPraSectorRepository.save(refPraSector);
        return ResponseEntity.created(new URI("/api/ref-pra-sectors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-pra-sectors : Updates an existing refPraSector.
     *
     * @param refPraSector the refPraSector to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refPraSector,
     * or with status 400 (Bad Request) if the refPraSector is not valid,
     * or with status 500 (Internal Server Error) if the refPraSector couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-pra-sectors")
    @Timed
    public ResponseEntity<RefPraSector> updateRefPraSector(@RequestBody RefPraSector refPraSector) throws URISyntaxException {
        log.debug("REST request to update RefPraSector : {}", refPraSector);
        if (refPraSector.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefPraSector result = refPraSectorRepository.save(refPraSector);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refPraSector.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-pra-sectors : get all the refPraSectors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refPraSectors in body
     */
    @GetMapping("/ref-pra-sectors")
    @Timed
    public List<RefPraSector> getAllRefPraSectors() {
        log.debug("REST request to get all RefPraSectors");
        return refPraSectorRepository.findAll();
    }

    /**
     * GET  /ref-pra-sectors/:id : get the "id" refPraSector.
     *
     * @param id the id of the refPraSector to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refPraSector, or with status 404 (Not Found)
     */
    @GetMapping("/ref-pra-sectors/{id}")
    @Timed
    public ResponseEntity<RefPraSector> getRefPraSector(@PathVariable Long id) {
        log.debug("REST request to get RefPraSector : {}", id);
        Optional<RefPraSector> refPraSector = refPraSectorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(refPraSector);
    }

    /**
     * DELETE  /ref-pra-sectors/:id : delete the "id" refPraSector.
     *
     * @param id the id of the refPraSector to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-pra-sectors/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefPraSector(@PathVariable Long id) {
        log.debug("REST request to delete RefPraSector : {}", id);

        refPraSectorRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

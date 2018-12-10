package com.demo.crud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.crud.domain.RefCovenant;
import com.demo.crud.repository.RefCovenantRepository;
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
 * REST controller for managing RefCovenant.
 */
@RestController
@RequestMapping("/api")
public class RefCovenantResource {

    private final Logger log = LoggerFactory.getLogger(RefCovenantResource.class);

    private static final String ENTITY_NAME = "refCovenant";

    private final RefCovenantRepository refCovenantRepository;

    public RefCovenantResource(RefCovenantRepository refCovenantRepository) {
        this.refCovenantRepository = refCovenantRepository;
    }

    /**
     * POST  /ref-covenants : Create a new refCovenant.
     *
     * @param refCovenant the refCovenant to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refCovenant, or with status 400 (Bad Request) if the refCovenant has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-covenants")
    @Timed
    public ResponseEntity<RefCovenant> createRefCovenant(@RequestBody RefCovenant refCovenant) throws URISyntaxException {
        log.debug("REST request to save RefCovenant : {}", refCovenant);
        if (refCovenant.getId() != null) {
            throw new BadRequestAlertException("A new refCovenant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefCovenant result = refCovenantRepository.save(refCovenant);
        return ResponseEntity.created(new URI("/api/ref-covenants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-covenants : Updates an existing refCovenant.
     *
     * @param refCovenant the refCovenant to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refCovenant,
     * or with status 400 (Bad Request) if the refCovenant is not valid,
     * or with status 500 (Internal Server Error) if the refCovenant couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-covenants")
    @Timed
    public ResponseEntity<RefCovenant> updateRefCovenant(@RequestBody RefCovenant refCovenant) throws URISyntaxException {
        log.debug("REST request to update RefCovenant : {}", refCovenant);
        if (refCovenant.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefCovenant result = refCovenantRepository.save(refCovenant);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refCovenant.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-covenants : get all the refCovenants.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refCovenants in body
     */
    @GetMapping("/ref-covenants")
    @Timed
    public List<RefCovenant> getAllRefCovenants() {
        log.debug("REST request to get all RefCovenants");
        return refCovenantRepository.findAll();
    }

    /**
     * GET  /ref-covenants/:id : get the "id" refCovenant.
     *
     * @param id the id of the refCovenant to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refCovenant, or with status 404 (Not Found)
     */
    @GetMapping("/ref-covenants/{id}")
    @Timed
    public ResponseEntity<RefCovenant> getRefCovenant(@PathVariable Long id) {
        log.debug("REST request to get RefCovenant : {}", id);
        Optional<RefCovenant> refCovenant = refCovenantRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(refCovenant);
    }

    /**
     * DELETE  /ref-covenants/:id : delete the "id" refCovenant.
     *
     * @param id the id of the refCovenant to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-covenants/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefCovenant(@PathVariable Long id) {
        log.debug("REST request to delete RefCovenant : {}", id);

        refCovenantRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

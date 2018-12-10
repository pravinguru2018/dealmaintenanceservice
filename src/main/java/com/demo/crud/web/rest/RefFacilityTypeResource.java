package com.demo.crud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.crud.domain.RefFacilityType;
import com.demo.crud.repository.RefFacilityTypeRepository;
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
 * REST controller for managing RefFacilityType.
 */
@RestController
@RequestMapping("/api")
public class RefFacilityTypeResource {

    private final Logger log = LoggerFactory.getLogger(RefFacilityTypeResource.class);

    private static final String ENTITY_NAME = "refFacilityType";

    private final RefFacilityTypeRepository refFacilityTypeRepository;

    public RefFacilityTypeResource(RefFacilityTypeRepository refFacilityTypeRepository) {
        this.refFacilityTypeRepository = refFacilityTypeRepository;
    }

    /**
     * POST  /ref-facility-types : Create a new refFacilityType.
     *
     * @param refFacilityType the refFacilityType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refFacilityType, or with status 400 (Bad Request) if the refFacilityType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-facility-types")
    @Timed
    public ResponseEntity<RefFacilityType> createRefFacilityType(@RequestBody RefFacilityType refFacilityType) throws URISyntaxException {
        log.debug("REST request to save RefFacilityType : {}", refFacilityType);
        if (refFacilityType.getId() != null) {
            throw new BadRequestAlertException("A new refFacilityType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefFacilityType result = refFacilityTypeRepository.save(refFacilityType);
        return ResponseEntity.created(new URI("/api/ref-facility-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-facility-types : Updates an existing refFacilityType.
     *
     * @param refFacilityType the refFacilityType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refFacilityType,
     * or with status 400 (Bad Request) if the refFacilityType is not valid,
     * or with status 500 (Internal Server Error) if the refFacilityType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-facility-types")
    @Timed
    public ResponseEntity<RefFacilityType> updateRefFacilityType(@RequestBody RefFacilityType refFacilityType) throws URISyntaxException {
        log.debug("REST request to update RefFacilityType : {}", refFacilityType);
        if (refFacilityType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefFacilityType result = refFacilityTypeRepository.save(refFacilityType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refFacilityType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-facility-types : get all the refFacilityTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refFacilityTypes in body
     */
    @GetMapping("/ref-facility-types")
    @Timed
    public List<RefFacilityType> getAllRefFacilityTypes() {
        log.debug("REST request to get all RefFacilityTypes");
        return refFacilityTypeRepository.findAll();
    }

    /**
     * GET  /ref-facility-types/:id : get the "id" refFacilityType.
     *
     * @param id the id of the refFacilityType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refFacilityType, or with status 404 (Not Found)
     */
    @GetMapping("/ref-facility-types/{id}")
    @Timed
    public ResponseEntity<RefFacilityType> getRefFacilityType(@PathVariable Long id) {
        log.debug("REST request to get RefFacilityType : {}", id);
        Optional<RefFacilityType> refFacilityType = refFacilityTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(refFacilityType);
    }

    /**
     * DELETE  /ref-facility-types/:id : delete the "id" refFacilityType.
     *
     * @param id the id of the refFacilityType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-facility-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefFacilityType(@PathVariable Long id) {
        log.debug("REST request to delete RefFacilityType : {}", id);

        refFacilityTypeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

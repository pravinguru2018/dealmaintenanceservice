package com.demo.crud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.crud.domain.RefBookingEntity;
import com.demo.crud.repository.RefBookingEntityRepository;
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
 * REST controller for managing RefBookingEntity.
 */
@RestController
@RequestMapping("/api")
public class RefBookingEntityResource {

    private final Logger log = LoggerFactory.getLogger(RefBookingEntityResource.class);

    private static final String ENTITY_NAME = "refBookingEntity";

    private final RefBookingEntityRepository refBookingEntityRepository;

    public RefBookingEntityResource(RefBookingEntityRepository refBookingEntityRepository) {
        this.refBookingEntityRepository = refBookingEntityRepository;
    }

    /**
     * POST  /ref-booking-entities : Create a new refBookingEntity.
     *
     * @param refBookingEntity the refBookingEntity to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refBookingEntity, or with status 400 (Bad Request) if the refBookingEntity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-booking-entities")
    @Timed
    public ResponseEntity<RefBookingEntity> createRefBookingEntity(@RequestBody RefBookingEntity refBookingEntity) throws URISyntaxException {
        log.debug("REST request to save RefBookingEntity : {}", refBookingEntity);
        if (refBookingEntity.getId() != null) {
            throw new BadRequestAlertException("A new refBookingEntity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefBookingEntity result = refBookingEntityRepository.save(refBookingEntity);
        return ResponseEntity.created(new URI("/api/ref-booking-entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-booking-entities : Updates an existing refBookingEntity.
     *
     * @param refBookingEntity the refBookingEntity to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refBookingEntity,
     * or with status 400 (Bad Request) if the refBookingEntity is not valid,
     * or with status 500 (Internal Server Error) if the refBookingEntity couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-booking-entities")
    @Timed
    public ResponseEntity<RefBookingEntity> updateRefBookingEntity(@RequestBody RefBookingEntity refBookingEntity) throws URISyntaxException {
        log.debug("REST request to update RefBookingEntity : {}", refBookingEntity);
        if (refBookingEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefBookingEntity result = refBookingEntityRepository.save(refBookingEntity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refBookingEntity.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-booking-entities : get all the refBookingEntities.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refBookingEntities in body
     */
    @GetMapping("/ref-booking-entities")
    @Timed
    public List<RefBookingEntity> getAllRefBookingEntities() {
        log.debug("REST request to get all RefBookingEntities");
        return refBookingEntityRepository.findAll();
    }

    /**
     * GET  /ref-booking-entities/:id : get the "id" refBookingEntity.
     *
     * @param id the id of the refBookingEntity to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refBookingEntity, or with status 404 (Not Found)
     */
    @GetMapping("/ref-booking-entities/{id}")
    @Timed
    public ResponseEntity<RefBookingEntity> getRefBookingEntity(@PathVariable Long id) {
        log.debug("REST request to get RefBookingEntity : {}", id);
        Optional<RefBookingEntity> refBookingEntity = refBookingEntityRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(refBookingEntity);
    }

    /**
     * DELETE  /ref-booking-entities/:id : delete the "id" refBookingEntity.
     *
     * @param id the id of the refBookingEntity to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-booking-entities/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefBookingEntity(@PathVariable Long id) {
        log.debug("REST request to delete RefBookingEntity : {}", id);

        refBookingEntityRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

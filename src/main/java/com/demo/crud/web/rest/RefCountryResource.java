package com.demo.crud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.crud.domain.RefCountry;
import com.demo.crud.repository.RefCountryRepository;
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
 * REST controller for managing RefCountry.
 */
@RestController
@RequestMapping("/api")
public class RefCountryResource {

    private final Logger log = LoggerFactory.getLogger(RefCountryResource.class);

    private static final String ENTITY_NAME = "refCountry";

    private final RefCountryRepository refCountryRepository;

    public RefCountryResource(RefCountryRepository refCountryRepository) {
        this.refCountryRepository = refCountryRepository;
    }

    /**
     * POST  /ref-countries : Create a new refCountry.
     *
     * @param refCountry the refCountry to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refCountry, or with status 400 (Bad Request) if the refCountry has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-countries")
    @Timed
    public ResponseEntity<RefCountry> createRefCountry(@RequestBody RefCountry refCountry) throws URISyntaxException {
        log.debug("REST request to save RefCountry : {}", refCountry);
        if (refCountry.getId() != null) {
            throw new BadRequestAlertException("A new refCountry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefCountry result = refCountryRepository.save(refCountry);
        return ResponseEntity.created(new URI("/api/ref-countries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-countries : Updates an existing refCountry.
     *
     * @param refCountry the refCountry to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refCountry,
     * or with status 400 (Bad Request) if the refCountry is not valid,
     * or with status 500 (Internal Server Error) if the refCountry couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-countries")
    @Timed
    public ResponseEntity<RefCountry> updateRefCountry(@RequestBody RefCountry refCountry) throws URISyntaxException {
        log.debug("REST request to update RefCountry : {}", refCountry);
        if (refCountry.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefCountry result = refCountryRepository.save(refCountry);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refCountry.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-countries : get all the refCountries.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refCountries in body
     */
    @GetMapping("/ref-countries")
    @Timed
    public List<RefCountry> getAllRefCountries() {
        log.debug("REST request to get all RefCountries");
        return refCountryRepository.findAll();
    }

    /**
     * GET  /ref-countries/:id : get the "id" refCountry.
     *
     * @param id the id of the refCountry to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refCountry, or with status 404 (Not Found)
     */
    @GetMapping("/ref-countries/{id}")
    @Timed
    public ResponseEntity<RefCountry> getRefCountry(@PathVariable Long id) {
        log.debug("REST request to get RefCountry : {}", id);
        Optional<RefCountry> refCountry = refCountryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(refCountry);
    }

    /**
     * DELETE  /ref-countries/:id : delete the "id" refCountry.
     *
     * @param id the id of the refCountry to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-countries/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefCountry(@PathVariable Long id) {
        log.debug("REST request to delete RefCountry : {}", id);

        refCountryRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

package com.demo.crud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.demo.crud.domain.RefRecourseToClient;
import com.demo.crud.repository.RefRecourseToClientRepository;
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
 * REST controller for managing RefRecourseToClient.
 */
@RestController
@RequestMapping("/api")
public class RefRecourseToClientResource {

    private final Logger log = LoggerFactory.getLogger(RefRecourseToClientResource.class);

    private static final String ENTITY_NAME = "refRecourseToClient";

    private final RefRecourseToClientRepository refRecourseToClientRepository;

    public RefRecourseToClientResource(RefRecourseToClientRepository refRecourseToClientRepository) {
        this.refRecourseToClientRepository = refRecourseToClientRepository;
    }

    /**
     * POST  /ref-recourse-to-clients : Create a new refRecourseToClient.
     *
     * @param refRecourseToClient the refRecourseToClient to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refRecourseToClient, or with status 400 (Bad Request) if the refRecourseToClient has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-recourse-to-clients")
    @Timed
    public ResponseEntity<RefRecourseToClient> createRefRecourseToClient(@RequestBody RefRecourseToClient refRecourseToClient) throws URISyntaxException {
        log.debug("REST request to save RefRecourseToClient : {}", refRecourseToClient);
        if (refRecourseToClient.getId() != null) {
            throw new BadRequestAlertException("A new refRecourseToClient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefRecourseToClient result = refRecourseToClientRepository.save(refRecourseToClient);
        return ResponseEntity.created(new URI("/api/ref-recourse-to-clients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-recourse-to-clients : Updates an existing refRecourseToClient.
     *
     * @param refRecourseToClient the refRecourseToClient to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refRecourseToClient,
     * or with status 400 (Bad Request) if the refRecourseToClient is not valid,
     * or with status 500 (Internal Server Error) if the refRecourseToClient couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-recourse-to-clients")
    @Timed
    public ResponseEntity<RefRecourseToClient> updateRefRecourseToClient(@RequestBody RefRecourseToClient refRecourseToClient) throws URISyntaxException {
        log.debug("REST request to update RefRecourseToClient : {}", refRecourseToClient);
        if (refRecourseToClient.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefRecourseToClient result = refRecourseToClientRepository.save(refRecourseToClient);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refRecourseToClient.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-recourse-to-clients : get all the refRecourseToClients.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refRecourseToClients in body
     */
    @GetMapping("/ref-recourse-to-clients")
    @Timed
    public List<RefRecourseToClient> getAllRefRecourseToClients() {
        log.debug("REST request to get all RefRecourseToClients");
        return refRecourseToClientRepository.findAll();
    }

    /**
     * GET  /ref-recourse-to-clients/:id : get the "id" refRecourseToClient.
     *
     * @param id the id of the refRecourseToClient to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refRecourseToClient, or with status 404 (Not Found)
     */
    @GetMapping("/ref-recourse-to-clients/{id}")
    @Timed
    public ResponseEntity<RefRecourseToClient> getRefRecourseToClient(@PathVariable Long id) {
        log.debug("REST request to get RefRecourseToClient : {}", id);
        Optional<RefRecourseToClient> refRecourseToClient = refRecourseToClientRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(refRecourseToClient);
    }

    /**
     * DELETE  /ref-recourse-to-clients/:id : delete the "id" refRecourseToClient.
     *
     * @param id the id of the refRecourseToClient to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-recourse-to-clients/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefRecourseToClient(@PathVariable Long id) {
        log.debug("REST request to delete RefRecourseToClient : {}", id);

        refRecourseToClientRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

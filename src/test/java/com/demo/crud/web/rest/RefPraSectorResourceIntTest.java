package com.demo.crud.web.rest;

import com.demo.crud.DealmaintenanceserviceApp;

import com.demo.crud.domain.RefPraSector;
import com.demo.crud.repository.RefPraSectorRepository;
import com.demo.crud.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.demo.crud.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RefPraSectorResource REST controller.
 *
 * @see RefPraSectorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DealmaintenanceserviceApp.class)
public class RefPraSectorResourceIntTest {

    private static final Integer DEFAULT_PRA_SECTOR_ID = 1;
    private static final Integer UPDATED_PRA_SECTOR_ID = 2;

    private static final String DEFAULT_PRA_SECTOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRA_SECTOR_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATED_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private RefPraSectorRepository refPraSectorRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefPraSectorMockMvc;

    private RefPraSector refPraSector;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefPraSectorResource refPraSectorResource = new RefPraSectorResource(refPraSectorRepository);
        this.restRefPraSectorMockMvc = MockMvcBuilders.standaloneSetup(refPraSectorResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RefPraSector createEntity(EntityManager em) {
        RefPraSector refPraSector = new RefPraSector()
            .praSectorId(DEFAULT_PRA_SECTOR_ID)
            .praSectorName(DEFAULT_PRA_SECTOR_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return refPraSector;
    }

    @Before
    public void initTest() {
        refPraSector = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefPraSector() throws Exception {
        int databaseSizeBeforeCreate = refPraSectorRepository.findAll().size();

        // Create the RefPraSector
        restRefPraSectorMockMvc.perform(post("/api/ref-pra-sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refPraSector)))
            .andExpect(status().isCreated());

        // Validate the RefPraSector in the database
        List<RefPraSector> refPraSectorList = refPraSectorRepository.findAll();
        assertThat(refPraSectorList).hasSize(databaseSizeBeforeCreate + 1);
        RefPraSector testRefPraSector = refPraSectorList.get(refPraSectorList.size() - 1);
        assertThat(testRefPraSector.getPraSectorId()).isEqualTo(DEFAULT_PRA_SECTOR_ID);
        assertThat(testRefPraSector.getPraSectorName()).isEqualTo(DEFAULT_PRA_SECTOR_NAME);
        assertThat(testRefPraSector.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRefPraSector.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRefPraSector.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testRefPraSector.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testRefPraSector.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createRefPraSectorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refPraSectorRepository.findAll().size();

        // Create the RefPraSector with an existing ID
        refPraSector.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefPraSectorMockMvc.perform(post("/api/ref-pra-sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refPraSector)))
            .andExpect(status().isBadRequest());

        // Validate the RefPraSector in the database
        List<RefPraSector> refPraSectorList = refPraSectorRepository.findAll();
        assertThat(refPraSectorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRefPraSectors() throws Exception {
        // Initialize the database
        refPraSectorRepository.saveAndFlush(refPraSector);

        // Get all the refPraSectorList
        restRefPraSectorMockMvc.perform(get("/api/ref-pra-sectors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refPraSector.getId().intValue())))
            .andExpect(jsonPath("$.[*].praSectorId").value(hasItem(DEFAULT_PRA_SECTOR_ID)))
            .andExpect(jsonPath("$.[*].praSectorName").value(hasItem(DEFAULT_PRA_SECTOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getRefPraSector() throws Exception {
        // Initialize the database
        refPraSectorRepository.saveAndFlush(refPraSector);

        // Get the refPraSector
        restRefPraSectorMockMvc.perform(get("/api/ref-pra-sectors/{id}", refPraSector.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refPraSector.getId().intValue()))
            .andExpect(jsonPath("$.praSectorId").value(DEFAULT_PRA_SECTOR_ID))
            .andExpect(jsonPath("$.praSectorName").value(DEFAULT_PRA_SECTOR_NAME.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefPraSector() throws Exception {
        // Get the refPraSector
        restRefPraSectorMockMvc.perform(get("/api/ref-pra-sectors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefPraSector() throws Exception {
        // Initialize the database
        refPraSectorRepository.saveAndFlush(refPraSector);

        int databaseSizeBeforeUpdate = refPraSectorRepository.findAll().size();

        // Update the refPraSector
        RefPraSector updatedRefPraSector = refPraSectorRepository.findById(refPraSector.getId()).get();
        // Disconnect from session so that the updates on updatedRefPraSector are not directly saved in db
        em.detach(updatedRefPraSector);
        updatedRefPraSector
            .praSectorId(UPDATED_PRA_SECTOR_ID)
            .praSectorName(UPDATED_PRA_SECTOR_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restRefPraSectorMockMvc.perform(put("/api/ref-pra-sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefPraSector)))
            .andExpect(status().isOk());

        // Validate the RefPraSector in the database
        List<RefPraSector> refPraSectorList = refPraSectorRepository.findAll();
        assertThat(refPraSectorList).hasSize(databaseSizeBeforeUpdate);
        RefPraSector testRefPraSector = refPraSectorList.get(refPraSectorList.size() - 1);
        assertThat(testRefPraSector.getPraSectorId()).isEqualTo(UPDATED_PRA_SECTOR_ID);
        assertThat(testRefPraSector.getPraSectorName()).isEqualTo(UPDATED_PRA_SECTOR_NAME);
        assertThat(testRefPraSector.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRefPraSector.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRefPraSector.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testRefPraSector.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testRefPraSector.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingRefPraSector() throws Exception {
        int databaseSizeBeforeUpdate = refPraSectorRepository.findAll().size();

        // Create the RefPraSector

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefPraSectorMockMvc.perform(put("/api/ref-pra-sectors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refPraSector)))
            .andExpect(status().isBadRequest());

        // Validate the RefPraSector in the database
        List<RefPraSector> refPraSectorList = refPraSectorRepository.findAll();
        assertThat(refPraSectorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefPraSector() throws Exception {
        // Initialize the database
        refPraSectorRepository.saveAndFlush(refPraSector);

        int databaseSizeBeforeDelete = refPraSectorRepository.findAll().size();

        // Get the refPraSector
        restRefPraSectorMockMvc.perform(delete("/api/ref-pra-sectors/{id}", refPraSector.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefPraSector> refPraSectorList = refPraSectorRepository.findAll();
        assertThat(refPraSectorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefPraSector.class);
        RefPraSector refPraSector1 = new RefPraSector();
        refPraSector1.setId(1L);
        RefPraSector refPraSector2 = new RefPraSector();
        refPraSector2.setId(refPraSector1.getId());
        assertThat(refPraSector1).isEqualTo(refPraSector2);
        refPraSector2.setId(2L);
        assertThat(refPraSector1).isNotEqualTo(refPraSector2);
        refPraSector1.setId(null);
        assertThat(refPraSector1).isNotEqualTo(refPraSector2);
    }
}

package com.demo.crud.web.rest;

import com.demo.crud.DealmaintenanceserviceApp;

import com.demo.crud.domain.RefCovenant;
import com.demo.crud.repository.RefCovenantRepository;
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
 * Test class for the RefCovenantResource REST controller.
 *
 * @see RefCovenantResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DealmaintenanceserviceApp.class)
public class RefCovenantResourceIntTest {

    private static final Integer DEFAULT_COVENANT_ID = 1;
    private static final Integer UPDATED_COVENANT_ID = 2;

    private static final String DEFAULT_COVENANT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COVENANT_NAME = "BBBBBBBBBB";

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
    private RefCovenantRepository refCovenantRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefCovenantMockMvc;

    private RefCovenant refCovenant;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefCovenantResource refCovenantResource = new RefCovenantResource(refCovenantRepository);
        this.restRefCovenantMockMvc = MockMvcBuilders.standaloneSetup(refCovenantResource)
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
    public static RefCovenant createEntity(EntityManager em) {
        RefCovenant refCovenant = new RefCovenant()
            .covenantId(DEFAULT_COVENANT_ID)
            .covenantName(DEFAULT_COVENANT_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return refCovenant;
    }

    @Before
    public void initTest() {
        refCovenant = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefCovenant() throws Exception {
        int databaseSizeBeforeCreate = refCovenantRepository.findAll().size();

        // Create the RefCovenant
        restRefCovenantMockMvc.perform(post("/api/ref-covenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refCovenant)))
            .andExpect(status().isCreated());

        // Validate the RefCovenant in the database
        List<RefCovenant> refCovenantList = refCovenantRepository.findAll();
        assertThat(refCovenantList).hasSize(databaseSizeBeforeCreate + 1);
        RefCovenant testRefCovenant = refCovenantList.get(refCovenantList.size() - 1);
        assertThat(testRefCovenant.getCovenantId()).isEqualTo(DEFAULT_COVENANT_ID);
        assertThat(testRefCovenant.getCovenantName()).isEqualTo(DEFAULT_COVENANT_NAME);
        assertThat(testRefCovenant.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRefCovenant.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRefCovenant.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testRefCovenant.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testRefCovenant.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createRefCovenantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refCovenantRepository.findAll().size();

        // Create the RefCovenant with an existing ID
        refCovenant.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefCovenantMockMvc.perform(post("/api/ref-covenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refCovenant)))
            .andExpect(status().isBadRequest());

        // Validate the RefCovenant in the database
        List<RefCovenant> refCovenantList = refCovenantRepository.findAll();
        assertThat(refCovenantList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRefCovenants() throws Exception {
        // Initialize the database
        refCovenantRepository.saveAndFlush(refCovenant);

        // Get all the refCovenantList
        restRefCovenantMockMvc.perform(get("/api/ref-covenants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refCovenant.getId().intValue())))
            .andExpect(jsonPath("$.[*].covenantId").value(hasItem(DEFAULT_COVENANT_ID)))
            .andExpect(jsonPath("$.[*].covenantName").value(hasItem(DEFAULT_COVENANT_NAME.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getRefCovenant() throws Exception {
        // Initialize the database
        refCovenantRepository.saveAndFlush(refCovenant);

        // Get the refCovenant
        restRefCovenantMockMvc.perform(get("/api/ref-covenants/{id}", refCovenant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refCovenant.getId().intValue()))
            .andExpect(jsonPath("$.covenantId").value(DEFAULT_COVENANT_ID))
            .andExpect(jsonPath("$.covenantName").value(DEFAULT_COVENANT_NAME.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefCovenant() throws Exception {
        // Get the refCovenant
        restRefCovenantMockMvc.perform(get("/api/ref-covenants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefCovenant() throws Exception {
        // Initialize the database
        refCovenantRepository.saveAndFlush(refCovenant);

        int databaseSizeBeforeUpdate = refCovenantRepository.findAll().size();

        // Update the refCovenant
        RefCovenant updatedRefCovenant = refCovenantRepository.findById(refCovenant.getId()).get();
        // Disconnect from session so that the updates on updatedRefCovenant are not directly saved in db
        em.detach(updatedRefCovenant);
        updatedRefCovenant
            .covenantId(UPDATED_COVENANT_ID)
            .covenantName(UPDATED_COVENANT_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restRefCovenantMockMvc.perform(put("/api/ref-covenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefCovenant)))
            .andExpect(status().isOk());

        // Validate the RefCovenant in the database
        List<RefCovenant> refCovenantList = refCovenantRepository.findAll();
        assertThat(refCovenantList).hasSize(databaseSizeBeforeUpdate);
        RefCovenant testRefCovenant = refCovenantList.get(refCovenantList.size() - 1);
        assertThat(testRefCovenant.getCovenantId()).isEqualTo(UPDATED_COVENANT_ID);
        assertThat(testRefCovenant.getCovenantName()).isEqualTo(UPDATED_COVENANT_NAME);
        assertThat(testRefCovenant.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRefCovenant.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRefCovenant.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testRefCovenant.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testRefCovenant.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingRefCovenant() throws Exception {
        int databaseSizeBeforeUpdate = refCovenantRepository.findAll().size();

        // Create the RefCovenant

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefCovenantMockMvc.perform(put("/api/ref-covenants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refCovenant)))
            .andExpect(status().isBadRequest());

        // Validate the RefCovenant in the database
        List<RefCovenant> refCovenantList = refCovenantRepository.findAll();
        assertThat(refCovenantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefCovenant() throws Exception {
        // Initialize the database
        refCovenantRepository.saveAndFlush(refCovenant);

        int databaseSizeBeforeDelete = refCovenantRepository.findAll().size();

        // Get the refCovenant
        restRefCovenantMockMvc.perform(delete("/api/ref-covenants/{id}", refCovenant.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefCovenant> refCovenantList = refCovenantRepository.findAll();
        assertThat(refCovenantList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefCovenant.class);
        RefCovenant refCovenant1 = new RefCovenant();
        refCovenant1.setId(1L);
        RefCovenant refCovenant2 = new RefCovenant();
        refCovenant2.setId(refCovenant1.getId());
        assertThat(refCovenant1).isEqualTo(refCovenant2);
        refCovenant2.setId(2L);
        assertThat(refCovenant1).isNotEqualTo(refCovenant2);
        refCovenant1.setId(null);
        assertThat(refCovenant1).isNotEqualTo(refCovenant2);
    }
}

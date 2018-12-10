package com.demo.crud.web.rest;

import com.demo.crud.DealmaintenanceserviceApp;

import com.demo.crud.domain.RefSeniority;
import com.demo.crud.repository.RefSeniorityRepository;
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
 * Test class for the RefSeniorityResource REST controller.
 *
 * @see RefSeniorityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DealmaintenanceserviceApp.class)
public class RefSeniorityResourceIntTest {

    private static final Integer DEFAULT_SENIORITY_ID = 1;
    private static final Integer UPDATED_SENIORITY_ID = 2;

    private static final String DEFAULT_SENIORITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SENIORITY_NAME = "BBBBBBBBBB";

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
    private RefSeniorityRepository refSeniorityRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefSeniorityMockMvc;

    private RefSeniority refSeniority;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefSeniorityResource refSeniorityResource = new RefSeniorityResource(refSeniorityRepository);
        this.restRefSeniorityMockMvc = MockMvcBuilders.standaloneSetup(refSeniorityResource)
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
    public static RefSeniority createEntity(EntityManager em) {
        RefSeniority refSeniority = new RefSeniority()
            .seniorityId(DEFAULT_SENIORITY_ID)
            .seniorityName(DEFAULT_SENIORITY_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return refSeniority;
    }

    @Before
    public void initTest() {
        refSeniority = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefSeniority() throws Exception {
        int databaseSizeBeforeCreate = refSeniorityRepository.findAll().size();

        // Create the RefSeniority
        restRefSeniorityMockMvc.perform(post("/api/ref-seniorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refSeniority)))
            .andExpect(status().isCreated());

        // Validate the RefSeniority in the database
        List<RefSeniority> refSeniorityList = refSeniorityRepository.findAll();
        assertThat(refSeniorityList).hasSize(databaseSizeBeforeCreate + 1);
        RefSeniority testRefSeniority = refSeniorityList.get(refSeniorityList.size() - 1);
        assertThat(testRefSeniority.getSeniorityId()).isEqualTo(DEFAULT_SENIORITY_ID);
        assertThat(testRefSeniority.getSeniorityName()).isEqualTo(DEFAULT_SENIORITY_NAME);
        assertThat(testRefSeniority.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRefSeniority.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRefSeniority.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testRefSeniority.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testRefSeniority.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createRefSeniorityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refSeniorityRepository.findAll().size();

        // Create the RefSeniority with an existing ID
        refSeniority.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefSeniorityMockMvc.perform(post("/api/ref-seniorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refSeniority)))
            .andExpect(status().isBadRequest());

        // Validate the RefSeniority in the database
        List<RefSeniority> refSeniorityList = refSeniorityRepository.findAll();
        assertThat(refSeniorityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRefSeniorities() throws Exception {
        // Initialize the database
        refSeniorityRepository.saveAndFlush(refSeniority);

        // Get all the refSeniorityList
        restRefSeniorityMockMvc.perform(get("/api/ref-seniorities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refSeniority.getId().intValue())))
            .andExpect(jsonPath("$.[*].seniorityId").value(hasItem(DEFAULT_SENIORITY_ID)))
            .andExpect(jsonPath("$.[*].seniorityName").value(hasItem(DEFAULT_SENIORITY_NAME.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getRefSeniority() throws Exception {
        // Initialize the database
        refSeniorityRepository.saveAndFlush(refSeniority);

        // Get the refSeniority
        restRefSeniorityMockMvc.perform(get("/api/ref-seniorities/{id}", refSeniority.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refSeniority.getId().intValue()))
            .andExpect(jsonPath("$.seniorityId").value(DEFAULT_SENIORITY_ID))
            .andExpect(jsonPath("$.seniorityName").value(DEFAULT_SENIORITY_NAME.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefSeniority() throws Exception {
        // Get the refSeniority
        restRefSeniorityMockMvc.perform(get("/api/ref-seniorities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefSeniority() throws Exception {
        // Initialize the database
        refSeniorityRepository.saveAndFlush(refSeniority);

        int databaseSizeBeforeUpdate = refSeniorityRepository.findAll().size();

        // Update the refSeniority
        RefSeniority updatedRefSeniority = refSeniorityRepository.findById(refSeniority.getId()).get();
        // Disconnect from session so that the updates on updatedRefSeniority are not directly saved in db
        em.detach(updatedRefSeniority);
        updatedRefSeniority
            .seniorityId(UPDATED_SENIORITY_ID)
            .seniorityName(UPDATED_SENIORITY_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restRefSeniorityMockMvc.perform(put("/api/ref-seniorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefSeniority)))
            .andExpect(status().isOk());

        // Validate the RefSeniority in the database
        List<RefSeniority> refSeniorityList = refSeniorityRepository.findAll();
        assertThat(refSeniorityList).hasSize(databaseSizeBeforeUpdate);
        RefSeniority testRefSeniority = refSeniorityList.get(refSeniorityList.size() - 1);
        assertThat(testRefSeniority.getSeniorityId()).isEqualTo(UPDATED_SENIORITY_ID);
        assertThat(testRefSeniority.getSeniorityName()).isEqualTo(UPDATED_SENIORITY_NAME);
        assertThat(testRefSeniority.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRefSeniority.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRefSeniority.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testRefSeniority.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testRefSeniority.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingRefSeniority() throws Exception {
        int databaseSizeBeforeUpdate = refSeniorityRepository.findAll().size();

        // Create the RefSeniority

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefSeniorityMockMvc.perform(put("/api/ref-seniorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refSeniority)))
            .andExpect(status().isBadRequest());

        // Validate the RefSeniority in the database
        List<RefSeniority> refSeniorityList = refSeniorityRepository.findAll();
        assertThat(refSeniorityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefSeniority() throws Exception {
        // Initialize the database
        refSeniorityRepository.saveAndFlush(refSeniority);

        int databaseSizeBeforeDelete = refSeniorityRepository.findAll().size();

        // Get the refSeniority
        restRefSeniorityMockMvc.perform(delete("/api/ref-seniorities/{id}", refSeniority.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefSeniority> refSeniorityList = refSeniorityRepository.findAll();
        assertThat(refSeniorityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefSeniority.class);
        RefSeniority refSeniority1 = new RefSeniority();
        refSeniority1.setId(1L);
        RefSeniority refSeniority2 = new RefSeniority();
        refSeniority2.setId(refSeniority1.getId());
        assertThat(refSeniority1).isEqualTo(refSeniority2);
        refSeniority2.setId(2L);
        assertThat(refSeniority1).isNotEqualTo(refSeniority2);
        refSeniority1.setId(null);
        assertThat(refSeniority1).isNotEqualTo(refSeniority2);
    }
}

package com.demo.crud.web.rest;

import com.demo.crud.DealmaintenanceserviceApp;

import com.demo.crud.domain.RefLineOfBusiness;
import com.demo.crud.repository.RefLineOfBusinessRepository;
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
 * Test class for the RefLineOfBusinessResource REST controller.
 *
 * @see RefLineOfBusinessResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DealmaintenanceserviceApp.class)
public class RefLineOfBusinessResourceIntTest {

    private static final Integer DEFAULT_LINE_OF_BUSINESS_ID = 1;
    private static final Integer UPDATED_LINE_OF_BUSINESS_ID = 2;

    private static final String DEFAULT_LINE_OF_BUSINESS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LINE_OF_BUSINESS_NAME = "BBBBBBBBBB";

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
    private RefLineOfBusinessRepository refLineOfBusinessRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefLineOfBusinessMockMvc;

    private RefLineOfBusiness refLineOfBusiness;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefLineOfBusinessResource refLineOfBusinessResource = new RefLineOfBusinessResource(refLineOfBusinessRepository);
        this.restRefLineOfBusinessMockMvc = MockMvcBuilders.standaloneSetup(refLineOfBusinessResource)
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
    public static RefLineOfBusiness createEntity(EntityManager em) {
        RefLineOfBusiness refLineOfBusiness = new RefLineOfBusiness()
            .lineOfBusinessId(DEFAULT_LINE_OF_BUSINESS_ID)
            .lineOfBusinessName(DEFAULT_LINE_OF_BUSINESS_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return refLineOfBusiness;
    }

    @Before
    public void initTest() {
        refLineOfBusiness = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefLineOfBusiness() throws Exception {
        int databaseSizeBeforeCreate = refLineOfBusinessRepository.findAll().size();

        // Create the RefLineOfBusiness
        restRefLineOfBusinessMockMvc.perform(post("/api/ref-line-of-businesses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refLineOfBusiness)))
            .andExpect(status().isCreated());

        // Validate the RefLineOfBusiness in the database
        List<RefLineOfBusiness> refLineOfBusinessList = refLineOfBusinessRepository.findAll();
        assertThat(refLineOfBusinessList).hasSize(databaseSizeBeforeCreate + 1);
        RefLineOfBusiness testRefLineOfBusiness = refLineOfBusinessList.get(refLineOfBusinessList.size() - 1);
        assertThat(testRefLineOfBusiness.getLineOfBusinessId()).isEqualTo(DEFAULT_LINE_OF_BUSINESS_ID);
        assertThat(testRefLineOfBusiness.getLineOfBusinessName()).isEqualTo(DEFAULT_LINE_OF_BUSINESS_NAME);
        assertThat(testRefLineOfBusiness.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRefLineOfBusiness.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRefLineOfBusiness.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testRefLineOfBusiness.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testRefLineOfBusiness.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createRefLineOfBusinessWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refLineOfBusinessRepository.findAll().size();

        // Create the RefLineOfBusiness with an existing ID
        refLineOfBusiness.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefLineOfBusinessMockMvc.perform(post("/api/ref-line-of-businesses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refLineOfBusiness)))
            .andExpect(status().isBadRequest());

        // Validate the RefLineOfBusiness in the database
        List<RefLineOfBusiness> refLineOfBusinessList = refLineOfBusinessRepository.findAll();
        assertThat(refLineOfBusinessList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRefLineOfBusinesses() throws Exception {
        // Initialize the database
        refLineOfBusinessRepository.saveAndFlush(refLineOfBusiness);

        // Get all the refLineOfBusinessList
        restRefLineOfBusinessMockMvc.perform(get("/api/ref-line-of-businesses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refLineOfBusiness.getId().intValue())))
            .andExpect(jsonPath("$.[*].lineOfBusinessId").value(hasItem(DEFAULT_LINE_OF_BUSINESS_ID)))
            .andExpect(jsonPath("$.[*].lineOfBusinessName").value(hasItem(DEFAULT_LINE_OF_BUSINESS_NAME.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getRefLineOfBusiness() throws Exception {
        // Initialize the database
        refLineOfBusinessRepository.saveAndFlush(refLineOfBusiness);

        // Get the refLineOfBusiness
        restRefLineOfBusinessMockMvc.perform(get("/api/ref-line-of-businesses/{id}", refLineOfBusiness.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refLineOfBusiness.getId().intValue()))
            .andExpect(jsonPath("$.lineOfBusinessId").value(DEFAULT_LINE_OF_BUSINESS_ID))
            .andExpect(jsonPath("$.lineOfBusinessName").value(DEFAULT_LINE_OF_BUSINESS_NAME.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefLineOfBusiness() throws Exception {
        // Get the refLineOfBusiness
        restRefLineOfBusinessMockMvc.perform(get("/api/ref-line-of-businesses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefLineOfBusiness() throws Exception {
        // Initialize the database
        refLineOfBusinessRepository.saveAndFlush(refLineOfBusiness);

        int databaseSizeBeforeUpdate = refLineOfBusinessRepository.findAll().size();

        // Update the refLineOfBusiness
        RefLineOfBusiness updatedRefLineOfBusiness = refLineOfBusinessRepository.findById(refLineOfBusiness.getId()).get();
        // Disconnect from session so that the updates on updatedRefLineOfBusiness are not directly saved in db
        em.detach(updatedRefLineOfBusiness);
        updatedRefLineOfBusiness
            .lineOfBusinessId(UPDATED_LINE_OF_BUSINESS_ID)
            .lineOfBusinessName(UPDATED_LINE_OF_BUSINESS_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restRefLineOfBusinessMockMvc.perform(put("/api/ref-line-of-businesses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefLineOfBusiness)))
            .andExpect(status().isOk());

        // Validate the RefLineOfBusiness in the database
        List<RefLineOfBusiness> refLineOfBusinessList = refLineOfBusinessRepository.findAll();
        assertThat(refLineOfBusinessList).hasSize(databaseSizeBeforeUpdate);
        RefLineOfBusiness testRefLineOfBusiness = refLineOfBusinessList.get(refLineOfBusinessList.size() - 1);
        assertThat(testRefLineOfBusiness.getLineOfBusinessId()).isEqualTo(UPDATED_LINE_OF_BUSINESS_ID);
        assertThat(testRefLineOfBusiness.getLineOfBusinessName()).isEqualTo(UPDATED_LINE_OF_BUSINESS_NAME);
        assertThat(testRefLineOfBusiness.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRefLineOfBusiness.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRefLineOfBusiness.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testRefLineOfBusiness.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testRefLineOfBusiness.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingRefLineOfBusiness() throws Exception {
        int databaseSizeBeforeUpdate = refLineOfBusinessRepository.findAll().size();

        // Create the RefLineOfBusiness

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefLineOfBusinessMockMvc.perform(put("/api/ref-line-of-businesses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refLineOfBusiness)))
            .andExpect(status().isBadRequest());

        // Validate the RefLineOfBusiness in the database
        List<RefLineOfBusiness> refLineOfBusinessList = refLineOfBusinessRepository.findAll();
        assertThat(refLineOfBusinessList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefLineOfBusiness() throws Exception {
        // Initialize the database
        refLineOfBusinessRepository.saveAndFlush(refLineOfBusiness);

        int databaseSizeBeforeDelete = refLineOfBusinessRepository.findAll().size();

        // Get the refLineOfBusiness
        restRefLineOfBusinessMockMvc.perform(delete("/api/ref-line-of-businesses/{id}", refLineOfBusiness.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefLineOfBusiness> refLineOfBusinessList = refLineOfBusinessRepository.findAll();
        assertThat(refLineOfBusinessList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefLineOfBusiness.class);
        RefLineOfBusiness refLineOfBusiness1 = new RefLineOfBusiness();
        refLineOfBusiness1.setId(1L);
        RefLineOfBusiness refLineOfBusiness2 = new RefLineOfBusiness();
        refLineOfBusiness2.setId(refLineOfBusiness1.getId());
        assertThat(refLineOfBusiness1).isEqualTo(refLineOfBusiness2);
        refLineOfBusiness2.setId(2L);
        assertThat(refLineOfBusiness1).isNotEqualTo(refLineOfBusiness2);
        refLineOfBusiness1.setId(null);
        assertThat(refLineOfBusiness1).isNotEqualTo(refLineOfBusiness2);
    }
}

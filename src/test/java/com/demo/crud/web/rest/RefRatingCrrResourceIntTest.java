package com.demo.crud.web.rest;

import com.demo.crud.DealmaintenanceserviceApp;

import com.demo.crud.domain.RefRatingCrr;
import com.demo.crud.repository.RefRatingCrrRepository;
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
 * Test class for the RefRatingCrrResource REST controller.
 *
 * @see RefRatingCrrResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DealmaintenanceserviceApp.class)
public class RefRatingCrrResourceIntTest {

    private static final Integer DEFAULT_RATING_CRR_ID = 1;
    private static final Integer UPDATED_RATING_CRR_ID = 2;

    private static final String DEFAULT_RATING_CRR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RATING_CRR_NAME = "BBBBBBBBBB";

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
    private RefRatingCrrRepository refRatingCrrRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefRatingCrrMockMvc;

    private RefRatingCrr refRatingCrr;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefRatingCrrResource refRatingCrrResource = new RefRatingCrrResource(refRatingCrrRepository);
        this.restRefRatingCrrMockMvc = MockMvcBuilders.standaloneSetup(refRatingCrrResource)
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
    public static RefRatingCrr createEntity(EntityManager em) {
        RefRatingCrr refRatingCrr = new RefRatingCrr()
            .ratingCrrId(DEFAULT_RATING_CRR_ID)
            .ratingCrrName(DEFAULT_RATING_CRR_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return refRatingCrr;
    }

    @Before
    public void initTest() {
        refRatingCrr = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefRatingCrr() throws Exception {
        int databaseSizeBeforeCreate = refRatingCrrRepository.findAll().size();

        // Create the RefRatingCrr
        restRefRatingCrrMockMvc.perform(post("/api/ref-rating-crrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRatingCrr)))
            .andExpect(status().isCreated());

        // Validate the RefRatingCrr in the database
        List<RefRatingCrr> refRatingCrrList = refRatingCrrRepository.findAll();
        assertThat(refRatingCrrList).hasSize(databaseSizeBeforeCreate + 1);
        RefRatingCrr testRefRatingCrr = refRatingCrrList.get(refRatingCrrList.size() - 1);
        assertThat(testRefRatingCrr.getRatingCrrId()).isEqualTo(DEFAULT_RATING_CRR_ID);
        assertThat(testRefRatingCrr.getRatingCrrName()).isEqualTo(DEFAULT_RATING_CRR_NAME);
        assertThat(testRefRatingCrr.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRefRatingCrr.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRefRatingCrr.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testRefRatingCrr.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testRefRatingCrr.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createRefRatingCrrWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refRatingCrrRepository.findAll().size();

        // Create the RefRatingCrr with an existing ID
        refRatingCrr.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefRatingCrrMockMvc.perform(post("/api/ref-rating-crrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRatingCrr)))
            .andExpect(status().isBadRequest());

        // Validate the RefRatingCrr in the database
        List<RefRatingCrr> refRatingCrrList = refRatingCrrRepository.findAll();
        assertThat(refRatingCrrList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRefRatingCrrs() throws Exception {
        // Initialize the database
        refRatingCrrRepository.saveAndFlush(refRatingCrr);

        // Get all the refRatingCrrList
        restRefRatingCrrMockMvc.perform(get("/api/ref-rating-crrs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refRatingCrr.getId().intValue())))
            .andExpect(jsonPath("$.[*].ratingCrrId").value(hasItem(DEFAULT_RATING_CRR_ID)))
            .andExpect(jsonPath("$.[*].ratingCrrName").value(hasItem(DEFAULT_RATING_CRR_NAME.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getRefRatingCrr() throws Exception {
        // Initialize the database
        refRatingCrrRepository.saveAndFlush(refRatingCrr);

        // Get the refRatingCrr
        restRefRatingCrrMockMvc.perform(get("/api/ref-rating-crrs/{id}", refRatingCrr.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refRatingCrr.getId().intValue()))
            .andExpect(jsonPath("$.ratingCrrId").value(DEFAULT_RATING_CRR_ID))
            .andExpect(jsonPath("$.ratingCrrName").value(DEFAULT_RATING_CRR_NAME.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefRatingCrr() throws Exception {
        // Get the refRatingCrr
        restRefRatingCrrMockMvc.perform(get("/api/ref-rating-crrs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefRatingCrr() throws Exception {
        // Initialize the database
        refRatingCrrRepository.saveAndFlush(refRatingCrr);

        int databaseSizeBeforeUpdate = refRatingCrrRepository.findAll().size();

        // Update the refRatingCrr
        RefRatingCrr updatedRefRatingCrr = refRatingCrrRepository.findById(refRatingCrr.getId()).get();
        // Disconnect from session so that the updates on updatedRefRatingCrr are not directly saved in db
        em.detach(updatedRefRatingCrr);
        updatedRefRatingCrr
            .ratingCrrId(UPDATED_RATING_CRR_ID)
            .ratingCrrName(UPDATED_RATING_CRR_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restRefRatingCrrMockMvc.perform(put("/api/ref-rating-crrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefRatingCrr)))
            .andExpect(status().isOk());

        // Validate the RefRatingCrr in the database
        List<RefRatingCrr> refRatingCrrList = refRatingCrrRepository.findAll();
        assertThat(refRatingCrrList).hasSize(databaseSizeBeforeUpdate);
        RefRatingCrr testRefRatingCrr = refRatingCrrList.get(refRatingCrrList.size() - 1);
        assertThat(testRefRatingCrr.getRatingCrrId()).isEqualTo(UPDATED_RATING_CRR_ID);
        assertThat(testRefRatingCrr.getRatingCrrName()).isEqualTo(UPDATED_RATING_CRR_NAME);
        assertThat(testRefRatingCrr.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRefRatingCrr.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRefRatingCrr.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testRefRatingCrr.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testRefRatingCrr.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingRefRatingCrr() throws Exception {
        int databaseSizeBeforeUpdate = refRatingCrrRepository.findAll().size();

        // Create the RefRatingCrr

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefRatingCrrMockMvc.perform(put("/api/ref-rating-crrs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRatingCrr)))
            .andExpect(status().isBadRequest());

        // Validate the RefRatingCrr in the database
        List<RefRatingCrr> refRatingCrrList = refRatingCrrRepository.findAll();
        assertThat(refRatingCrrList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefRatingCrr() throws Exception {
        // Initialize the database
        refRatingCrrRepository.saveAndFlush(refRatingCrr);

        int databaseSizeBeforeDelete = refRatingCrrRepository.findAll().size();

        // Get the refRatingCrr
        restRefRatingCrrMockMvc.perform(delete("/api/ref-rating-crrs/{id}", refRatingCrr.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefRatingCrr> refRatingCrrList = refRatingCrrRepository.findAll();
        assertThat(refRatingCrrList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefRatingCrr.class);
        RefRatingCrr refRatingCrr1 = new RefRatingCrr();
        refRatingCrr1.setId(1L);
        RefRatingCrr refRatingCrr2 = new RefRatingCrr();
        refRatingCrr2.setId(refRatingCrr1.getId());
        assertThat(refRatingCrr1).isEqualTo(refRatingCrr2);
        refRatingCrr2.setId(2L);
        assertThat(refRatingCrr1).isNotEqualTo(refRatingCrr2);
        refRatingCrr1.setId(null);
        assertThat(refRatingCrr1).isNotEqualTo(refRatingCrr2);
    }
}

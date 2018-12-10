package com.demo.crud.web.rest;

import com.demo.crud.DealmaintenanceserviceApp;

import com.demo.crud.domain.RefRatingFitch;
import com.demo.crud.repository.RefRatingFitchRepository;
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
 * Test class for the RefRatingFitchResource REST controller.
 *
 * @see RefRatingFitchResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DealmaintenanceserviceApp.class)
public class RefRatingFitchResourceIntTest {

    private static final Integer DEFAULT_RATING_FITCH_ID = 1;
    private static final Integer UPDATED_RATING_FITCH_ID = 2;

    private static final String DEFAULT_RATING_FITCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RATING_FITCH_NAME = "BBBBBBBBBB";

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
    private RefRatingFitchRepository refRatingFitchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefRatingFitchMockMvc;

    private RefRatingFitch refRatingFitch;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefRatingFitchResource refRatingFitchResource = new RefRatingFitchResource(refRatingFitchRepository);
        this.restRefRatingFitchMockMvc = MockMvcBuilders.standaloneSetup(refRatingFitchResource)
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
    public static RefRatingFitch createEntity(EntityManager em) {
        RefRatingFitch refRatingFitch = new RefRatingFitch()
            .ratingFitchId(DEFAULT_RATING_FITCH_ID)
            .ratingFitchName(DEFAULT_RATING_FITCH_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return refRatingFitch;
    }

    @Before
    public void initTest() {
        refRatingFitch = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefRatingFitch() throws Exception {
        int databaseSizeBeforeCreate = refRatingFitchRepository.findAll().size();

        // Create the RefRatingFitch
        restRefRatingFitchMockMvc.perform(post("/api/ref-rating-fitches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRatingFitch)))
            .andExpect(status().isCreated());

        // Validate the RefRatingFitch in the database
        List<RefRatingFitch> refRatingFitchList = refRatingFitchRepository.findAll();
        assertThat(refRatingFitchList).hasSize(databaseSizeBeforeCreate + 1);
        RefRatingFitch testRefRatingFitch = refRatingFitchList.get(refRatingFitchList.size() - 1);
        assertThat(testRefRatingFitch.getRatingFitchId()).isEqualTo(DEFAULT_RATING_FITCH_ID);
        assertThat(testRefRatingFitch.getRatingFitchName()).isEqualTo(DEFAULT_RATING_FITCH_NAME);
        assertThat(testRefRatingFitch.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRefRatingFitch.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRefRatingFitch.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testRefRatingFitch.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testRefRatingFitch.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createRefRatingFitchWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refRatingFitchRepository.findAll().size();

        // Create the RefRatingFitch with an existing ID
        refRatingFitch.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefRatingFitchMockMvc.perform(post("/api/ref-rating-fitches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRatingFitch)))
            .andExpect(status().isBadRequest());

        // Validate the RefRatingFitch in the database
        List<RefRatingFitch> refRatingFitchList = refRatingFitchRepository.findAll();
        assertThat(refRatingFitchList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRefRatingFitches() throws Exception {
        // Initialize the database
        refRatingFitchRepository.saveAndFlush(refRatingFitch);

        // Get all the refRatingFitchList
        restRefRatingFitchMockMvc.perform(get("/api/ref-rating-fitches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refRatingFitch.getId().intValue())))
            .andExpect(jsonPath("$.[*].ratingFitchId").value(hasItem(DEFAULT_RATING_FITCH_ID)))
            .andExpect(jsonPath("$.[*].ratingFitchName").value(hasItem(DEFAULT_RATING_FITCH_NAME.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getRefRatingFitch() throws Exception {
        // Initialize the database
        refRatingFitchRepository.saveAndFlush(refRatingFitch);

        // Get the refRatingFitch
        restRefRatingFitchMockMvc.perform(get("/api/ref-rating-fitches/{id}", refRatingFitch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refRatingFitch.getId().intValue()))
            .andExpect(jsonPath("$.ratingFitchId").value(DEFAULT_RATING_FITCH_ID))
            .andExpect(jsonPath("$.ratingFitchName").value(DEFAULT_RATING_FITCH_NAME.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefRatingFitch() throws Exception {
        // Get the refRatingFitch
        restRefRatingFitchMockMvc.perform(get("/api/ref-rating-fitches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefRatingFitch() throws Exception {
        // Initialize the database
        refRatingFitchRepository.saveAndFlush(refRatingFitch);

        int databaseSizeBeforeUpdate = refRatingFitchRepository.findAll().size();

        // Update the refRatingFitch
        RefRatingFitch updatedRefRatingFitch = refRatingFitchRepository.findById(refRatingFitch.getId()).get();
        // Disconnect from session so that the updates on updatedRefRatingFitch are not directly saved in db
        em.detach(updatedRefRatingFitch);
        updatedRefRatingFitch
            .ratingFitchId(UPDATED_RATING_FITCH_ID)
            .ratingFitchName(UPDATED_RATING_FITCH_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restRefRatingFitchMockMvc.perform(put("/api/ref-rating-fitches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefRatingFitch)))
            .andExpect(status().isOk());

        // Validate the RefRatingFitch in the database
        List<RefRatingFitch> refRatingFitchList = refRatingFitchRepository.findAll();
        assertThat(refRatingFitchList).hasSize(databaseSizeBeforeUpdate);
        RefRatingFitch testRefRatingFitch = refRatingFitchList.get(refRatingFitchList.size() - 1);
        assertThat(testRefRatingFitch.getRatingFitchId()).isEqualTo(UPDATED_RATING_FITCH_ID);
        assertThat(testRefRatingFitch.getRatingFitchName()).isEqualTo(UPDATED_RATING_FITCH_NAME);
        assertThat(testRefRatingFitch.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRefRatingFitch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRefRatingFitch.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testRefRatingFitch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testRefRatingFitch.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingRefRatingFitch() throws Exception {
        int databaseSizeBeforeUpdate = refRatingFitchRepository.findAll().size();

        // Create the RefRatingFitch

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefRatingFitchMockMvc.perform(put("/api/ref-rating-fitches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRatingFitch)))
            .andExpect(status().isBadRequest());

        // Validate the RefRatingFitch in the database
        List<RefRatingFitch> refRatingFitchList = refRatingFitchRepository.findAll();
        assertThat(refRatingFitchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefRatingFitch() throws Exception {
        // Initialize the database
        refRatingFitchRepository.saveAndFlush(refRatingFitch);

        int databaseSizeBeforeDelete = refRatingFitchRepository.findAll().size();

        // Get the refRatingFitch
        restRefRatingFitchMockMvc.perform(delete("/api/ref-rating-fitches/{id}", refRatingFitch.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefRatingFitch> refRatingFitchList = refRatingFitchRepository.findAll();
        assertThat(refRatingFitchList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefRatingFitch.class);
        RefRatingFitch refRatingFitch1 = new RefRatingFitch();
        refRatingFitch1.setId(1L);
        RefRatingFitch refRatingFitch2 = new RefRatingFitch();
        refRatingFitch2.setId(refRatingFitch1.getId());
        assertThat(refRatingFitch1).isEqualTo(refRatingFitch2);
        refRatingFitch2.setId(2L);
        assertThat(refRatingFitch1).isNotEqualTo(refRatingFitch2);
        refRatingFitch1.setId(null);
        assertThat(refRatingFitch1).isNotEqualTo(refRatingFitch2);
    }
}

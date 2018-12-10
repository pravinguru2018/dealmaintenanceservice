package com.demo.crud.web.rest;

import com.demo.crud.DealmaintenanceserviceApp;

import com.demo.crud.domain.RefRatingMoodys;
import com.demo.crud.repository.RefRatingMoodysRepository;
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
 * Test class for the RefRatingMoodysResource REST controller.
 *
 * @see RefRatingMoodysResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DealmaintenanceserviceApp.class)
public class RefRatingMoodysResourceIntTest {

    private static final Integer DEFAULT_RATING_MOODYS_ID = 1;
    private static final Integer UPDATED_RATING_MOODYS_ID = 2;

    private static final String DEFAULT_RATING_MOODYS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RATING_MOODYS_NAME = "BBBBBBBBBB";

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
    private RefRatingMoodysRepository refRatingMoodysRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefRatingMoodysMockMvc;

    private RefRatingMoodys refRatingMoodys;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefRatingMoodysResource refRatingMoodysResource = new RefRatingMoodysResource(refRatingMoodysRepository);
        this.restRefRatingMoodysMockMvc = MockMvcBuilders.standaloneSetup(refRatingMoodysResource)
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
    public static RefRatingMoodys createEntity(EntityManager em) {
        RefRatingMoodys refRatingMoodys = new RefRatingMoodys()
            .ratingMoodysId(DEFAULT_RATING_MOODYS_ID)
            .ratingMoodysName(DEFAULT_RATING_MOODYS_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return refRatingMoodys;
    }

    @Before
    public void initTest() {
        refRatingMoodys = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefRatingMoodys() throws Exception {
        int databaseSizeBeforeCreate = refRatingMoodysRepository.findAll().size();

        // Create the RefRatingMoodys
        restRefRatingMoodysMockMvc.perform(post("/api/ref-rating-moodys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRatingMoodys)))
            .andExpect(status().isCreated());

        // Validate the RefRatingMoodys in the database
        List<RefRatingMoodys> refRatingMoodysList = refRatingMoodysRepository.findAll();
        assertThat(refRatingMoodysList).hasSize(databaseSizeBeforeCreate + 1);
        RefRatingMoodys testRefRatingMoodys = refRatingMoodysList.get(refRatingMoodysList.size() - 1);
        assertThat(testRefRatingMoodys.getRatingMoodysId()).isEqualTo(DEFAULT_RATING_MOODYS_ID);
        assertThat(testRefRatingMoodys.getRatingMoodysName()).isEqualTo(DEFAULT_RATING_MOODYS_NAME);
        assertThat(testRefRatingMoodys.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRefRatingMoodys.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRefRatingMoodys.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testRefRatingMoodys.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testRefRatingMoodys.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createRefRatingMoodysWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refRatingMoodysRepository.findAll().size();

        // Create the RefRatingMoodys with an existing ID
        refRatingMoodys.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefRatingMoodysMockMvc.perform(post("/api/ref-rating-moodys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRatingMoodys)))
            .andExpect(status().isBadRequest());

        // Validate the RefRatingMoodys in the database
        List<RefRatingMoodys> refRatingMoodysList = refRatingMoodysRepository.findAll();
        assertThat(refRatingMoodysList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRefRatingMoodys() throws Exception {
        // Initialize the database
        refRatingMoodysRepository.saveAndFlush(refRatingMoodys);

        // Get all the refRatingMoodysList
        restRefRatingMoodysMockMvc.perform(get("/api/ref-rating-moodys?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refRatingMoodys.getId().intValue())))
            .andExpect(jsonPath("$.[*].ratingMoodysId").value(hasItem(DEFAULT_RATING_MOODYS_ID)))
            .andExpect(jsonPath("$.[*].ratingMoodysName").value(hasItem(DEFAULT_RATING_MOODYS_NAME.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getRefRatingMoodys() throws Exception {
        // Initialize the database
        refRatingMoodysRepository.saveAndFlush(refRatingMoodys);

        // Get the refRatingMoodys
        restRefRatingMoodysMockMvc.perform(get("/api/ref-rating-moodys/{id}", refRatingMoodys.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refRatingMoodys.getId().intValue()))
            .andExpect(jsonPath("$.ratingMoodysId").value(DEFAULT_RATING_MOODYS_ID))
            .andExpect(jsonPath("$.ratingMoodysName").value(DEFAULT_RATING_MOODYS_NAME.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefRatingMoodys() throws Exception {
        // Get the refRatingMoodys
        restRefRatingMoodysMockMvc.perform(get("/api/ref-rating-moodys/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefRatingMoodys() throws Exception {
        // Initialize the database
        refRatingMoodysRepository.saveAndFlush(refRatingMoodys);

        int databaseSizeBeforeUpdate = refRatingMoodysRepository.findAll().size();

        // Update the refRatingMoodys
        RefRatingMoodys updatedRefRatingMoodys = refRatingMoodysRepository.findById(refRatingMoodys.getId()).get();
        // Disconnect from session so that the updates on updatedRefRatingMoodys are not directly saved in db
        em.detach(updatedRefRatingMoodys);
        updatedRefRatingMoodys
            .ratingMoodysId(UPDATED_RATING_MOODYS_ID)
            .ratingMoodysName(UPDATED_RATING_MOODYS_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restRefRatingMoodysMockMvc.perform(put("/api/ref-rating-moodys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefRatingMoodys)))
            .andExpect(status().isOk());

        // Validate the RefRatingMoodys in the database
        List<RefRatingMoodys> refRatingMoodysList = refRatingMoodysRepository.findAll();
        assertThat(refRatingMoodysList).hasSize(databaseSizeBeforeUpdate);
        RefRatingMoodys testRefRatingMoodys = refRatingMoodysList.get(refRatingMoodysList.size() - 1);
        assertThat(testRefRatingMoodys.getRatingMoodysId()).isEqualTo(UPDATED_RATING_MOODYS_ID);
        assertThat(testRefRatingMoodys.getRatingMoodysName()).isEqualTo(UPDATED_RATING_MOODYS_NAME);
        assertThat(testRefRatingMoodys.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRefRatingMoodys.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRefRatingMoodys.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testRefRatingMoodys.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testRefRatingMoodys.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingRefRatingMoodys() throws Exception {
        int databaseSizeBeforeUpdate = refRatingMoodysRepository.findAll().size();

        // Create the RefRatingMoodys

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefRatingMoodysMockMvc.perform(put("/api/ref-rating-moodys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRatingMoodys)))
            .andExpect(status().isBadRequest());

        // Validate the RefRatingMoodys in the database
        List<RefRatingMoodys> refRatingMoodysList = refRatingMoodysRepository.findAll();
        assertThat(refRatingMoodysList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefRatingMoodys() throws Exception {
        // Initialize the database
        refRatingMoodysRepository.saveAndFlush(refRatingMoodys);

        int databaseSizeBeforeDelete = refRatingMoodysRepository.findAll().size();

        // Get the refRatingMoodys
        restRefRatingMoodysMockMvc.perform(delete("/api/ref-rating-moodys/{id}", refRatingMoodys.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefRatingMoodys> refRatingMoodysList = refRatingMoodysRepository.findAll();
        assertThat(refRatingMoodysList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefRatingMoodys.class);
        RefRatingMoodys refRatingMoodys1 = new RefRatingMoodys();
        refRatingMoodys1.setId(1L);
        RefRatingMoodys refRatingMoodys2 = new RefRatingMoodys();
        refRatingMoodys2.setId(refRatingMoodys1.getId());
        assertThat(refRatingMoodys1).isEqualTo(refRatingMoodys2);
        refRatingMoodys2.setId(2L);
        assertThat(refRatingMoodys1).isNotEqualTo(refRatingMoodys2);
        refRatingMoodys1.setId(null);
        assertThat(refRatingMoodys1).isNotEqualTo(refRatingMoodys2);
    }
}

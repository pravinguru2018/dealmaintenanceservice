package com.demo.crud.web.rest;

import com.demo.crud.DealmaintenanceserviceApp;

import com.demo.crud.domain.RefRatingSAndP;
import com.demo.crud.repository.RefRatingSAndPRepository;
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
 * Test class for the RefRatingSAndPResource REST controller.
 *
 * @see RefRatingSAndPResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DealmaintenanceserviceApp.class)
public class RefRatingSAndPResourceIntTest {

    private static final Integer DEFAULT_RATING_S_AND_P_ID = 1;
    private static final Integer UPDATED_RATING_S_AND_P_ID = 2;

    private static final String DEFAULT_RATING_S_AND_P_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RATING_S_AND_P_NAME = "BBBBBBBBBB";

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
    private RefRatingSAndPRepository refRatingSAndPRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefRatingSAndPMockMvc;

    private RefRatingSAndP refRatingSAndP;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefRatingSAndPResource refRatingSAndPResource = new RefRatingSAndPResource(refRatingSAndPRepository);
        this.restRefRatingSAndPMockMvc = MockMvcBuilders.standaloneSetup(refRatingSAndPResource)
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
    public static RefRatingSAndP createEntity(EntityManager em) {
        RefRatingSAndP refRatingSAndP = new RefRatingSAndP()
            .ratingSAndPId(DEFAULT_RATING_S_AND_P_ID)
            .ratingSAndPName(DEFAULT_RATING_S_AND_P_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return refRatingSAndP;
    }

    @Before
    public void initTest() {
        refRatingSAndP = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefRatingSAndP() throws Exception {
        int databaseSizeBeforeCreate = refRatingSAndPRepository.findAll().size();

        // Create the RefRatingSAndP
        restRefRatingSAndPMockMvc.perform(post("/api/ref-rating-s-and-ps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRatingSAndP)))
            .andExpect(status().isCreated());

        // Validate the RefRatingSAndP in the database
        List<RefRatingSAndP> refRatingSAndPList = refRatingSAndPRepository.findAll();
        assertThat(refRatingSAndPList).hasSize(databaseSizeBeforeCreate + 1);
        RefRatingSAndP testRefRatingSAndP = refRatingSAndPList.get(refRatingSAndPList.size() - 1);
        assertThat(testRefRatingSAndP.getRatingSAndPId()).isEqualTo(DEFAULT_RATING_S_AND_P_ID);
        assertThat(testRefRatingSAndP.getRatingSAndPName()).isEqualTo(DEFAULT_RATING_S_AND_P_NAME);
        assertThat(testRefRatingSAndP.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRefRatingSAndP.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRefRatingSAndP.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testRefRatingSAndP.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testRefRatingSAndP.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createRefRatingSAndPWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refRatingSAndPRepository.findAll().size();

        // Create the RefRatingSAndP with an existing ID
        refRatingSAndP.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefRatingSAndPMockMvc.perform(post("/api/ref-rating-s-and-ps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRatingSAndP)))
            .andExpect(status().isBadRequest());

        // Validate the RefRatingSAndP in the database
        List<RefRatingSAndP> refRatingSAndPList = refRatingSAndPRepository.findAll();
        assertThat(refRatingSAndPList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRefRatingSAndPS() throws Exception {
        // Initialize the database
        refRatingSAndPRepository.saveAndFlush(refRatingSAndP);

        // Get all the refRatingSAndPList
        restRefRatingSAndPMockMvc.perform(get("/api/ref-rating-s-and-ps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refRatingSAndP.getId().intValue())))
            .andExpect(jsonPath("$.[*].ratingSAndPId").value(hasItem(DEFAULT_RATING_S_AND_P_ID)))
            .andExpect(jsonPath("$.[*].ratingSAndPName").value(hasItem(DEFAULT_RATING_S_AND_P_NAME.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getRefRatingSAndP() throws Exception {
        // Initialize the database
        refRatingSAndPRepository.saveAndFlush(refRatingSAndP);

        // Get the refRatingSAndP
        restRefRatingSAndPMockMvc.perform(get("/api/ref-rating-s-and-ps/{id}", refRatingSAndP.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refRatingSAndP.getId().intValue()))
            .andExpect(jsonPath("$.ratingSAndPId").value(DEFAULT_RATING_S_AND_P_ID))
            .andExpect(jsonPath("$.ratingSAndPName").value(DEFAULT_RATING_S_AND_P_NAME.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefRatingSAndP() throws Exception {
        // Get the refRatingSAndP
        restRefRatingSAndPMockMvc.perform(get("/api/ref-rating-s-and-ps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefRatingSAndP() throws Exception {
        // Initialize the database
        refRatingSAndPRepository.saveAndFlush(refRatingSAndP);

        int databaseSizeBeforeUpdate = refRatingSAndPRepository.findAll().size();

        // Update the refRatingSAndP
        RefRatingSAndP updatedRefRatingSAndP = refRatingSAndPRepository.findById(refRatingSAndP.getId()).get();
        // Disconnect from session so that the updates on updatedRefRatingSAndP are not directly saved in db
        em.detach(updatedRefRatingSAndP);
        updatedRefRatingSAndP
            .ratingSAndPId(UPDATED_RATING_S_AND_P_ID)
            .ratingSAndPName(UPDATED_RATING_S_AND_P_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restRefRatingSAndPMockMvc.perform(put("/api/ref-rating-s-and-ps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefRatingSAndP)))
            .andExpect(status().isOk());

        // Validate the RefRatingSAndP in the database
        List<RefRatingSAndP> refRatingSAndPList = refRatingSAndPRepository.findAll();
        assertThat(refRatingSAndPList).hasSize(databaseSizeBeforeUpdate);
        RefRatingSAndP testRefRatingSAndP = refRatingSAndPList.get(refRatingSAndPList.size() - 1);
        assertThat(testRefRatingSAndP.getRatingSAndPId()).isEqualTo(UPDATED_RATING_S_AND_P_ID);
        assertThat(testRefRatingSAndP.getRatingSAndPName()).isEqualTo(UPDATED_RATING_S_AND_P_NAME);
        assertThat(testRefRatingSAndP.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRefRatingSAndP.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRefRatingSAndP.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testRefRatingSAndP.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testRefRatingSAndP.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingRefRatingSAndP() throws Exception {
        int databaseSizeBeforeUpdate = refRatingSAndPRepository.findAll().size();

        // Create the RefRatingSAndP

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefRatingSAndPMockMvc.perform(put("/api/ref-rating-s-and-ps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRatingSAndP)))
            .andExpect(status().isBadRequest());

        // Validate the RefRatingSAndP in the database
        List<RefRatingSAndP> refRatingSAndPList = refRatingSAndPRepository.findAll();
        assertThat(refRatingSAndPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefRatingSAndP() throws Exception {
        // Initialize the database
        refRatingSAndPRepository.saveAndFlush(refRatingSAndP);

        int databaseSizeBeforeDelete = refRatingSAndPRepository.findAll().size();

        // Get the refRatingSAndP
        restRefRatingSAndPMockMvc.perform(delete("/api/ref-rating-s-and-ps/{id}", refRatingSAndP.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefRatingSAndP> refRatingSAndPList = refRatingSAndPRepository.findAll();
        assertThat(refRatingSAndPList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefRatingSAndP.class);
        RefRatingSAndP refRatingSAndP1 = new RefRatingSAndP();
        refRatingSAndP1.setId(1L);
        RefRatingSAndP refRatingSAndP2 = new RefRatingSAndP();
        refRatingSAndP2.setId(refRatingSAndP1.getId());
        assertThat(refRatingSAndP1).isEqualTo(refRatingSAndP2);
        refRatingSAndP2.setId(2L);
        assertThat(refRatingSAndP1).isNotEqualTo(refRatingSAndP2);
        refRatingSAndP1.setId(null);
        assertThat(refRatingSAndP1).isNotEqualTo(refRatingSAndP2);
    }
}

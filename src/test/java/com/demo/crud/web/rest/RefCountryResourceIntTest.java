package com.demo.crud.web.rest;

import com.demo.crud.DealmaintenanceserviceApp;

import com.demo.crud.domain.RefCountry;
import com.demo.crud.repository.RefCountryRepository;
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
 * Test class for the RefCountryResource REST controller.
 *
 * @see RefCountryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DealmaintenanceserviceApp.class)
public class RefCountryResourceIntTest {

    private static final Integer DEFAULT_COUNTRY_ID = 1;
    private static final Integer UPDATED_COUNTRY_ID = 2;

    private static final String DEFAULT_COUNTRY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_PRA = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_PRA = "BBBBBBBBBB";

    private static final String DEFAULT_REGION_PRA = "AAAAAAAAAA";
    private static final String UPDATED_REGION_PRA = "BBBBBBBBBB";

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
    private RefCountryRepository refCountryRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefCountryMockMvc;

    private RefCountry refCountry;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefCountryResource refCountryResource = new RefCountryResource(refCountryRepository);
        this.restRefCountryMockMvc = MockMvcBuilders.standaloneSetup(refCountryResource)
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
    public static RefCountry createEntity(EntityManager em) {
        RefCountry refCountry = new RefCountry()
            .countryId(DEFAULT_COUNTRY_ID)
            .countryName(DEFAULT_COUNTRY_NAME)
            .countryPra(DEFAULT_COUNTRY_PRA)
            .regionPra(DEFAULT_REGION_PRA)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return refCountry;
    }

    @Before
    public void initTest() {
        refCountry = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefCountry() throws Exception {
        int databaseSizeBeforeCreate = refCountryRepository.findAll().size();

        // Create the RefCountry
        restRefCountryMockMvc.perform(post("/api/ref-countries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refCountry)))
            .andExpect(status().isCreated());

        // Validate the RefCountry in the database
        List<RefCountry> refCountryList = refCountryRepository.findAll();
        assertThat(refCountryList).hasSize(databaseSizeBeforeCreate + 1);
        RefCountry testRefCountry = refCountryList.get(refCountryList.size() - 1);
        assertThat(testRefCountry.getCountryId()).isEqualTo(DEFAULT_COUNTRY_ID);
        assertThat(testRefCountry.getCountryName()).isEqualTo(DEFAULT_COUNTRY_NAME);
        assertThat(testRefCountry.getCountryPra()).isEqualTo(DEFAULT_COUNTRY_PRA);
        assertThat(testRefCountry.getRegionPra()).isEqualTo(DEFAULT_REGION_PRA);
        assertThat(testRefCountry.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRefCountry.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRefCountry.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testRefCountry.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testRefCountry.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createRefCountryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refCountryRepository.findAll().size();

        // Create the RefCountry with an existing ID
        refCountry.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefCountryMockMvc.perform(post("/api/ref-countries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refCountry)))
            .andExpect(status().isBadRequest());

        // Validate the RefCountry in the database
        List<RefCountry> refCountryList = refCountryRepository.findAll();
        assertThat(refCountryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRefCountries() throws Exception {
        // Initialize the database
        refCountryRepository.saveAndFlush(refCountry);

        // Get all the refCountryList
        restRefCountryMockMvc.perform(get("/api/ref-countries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refCountry.getId().intValue())))
            .andExpect(jsonPath("$.[*].countryId").value(hasItem(DEFAULT_COUNTRY_ID)))
            .andExpect(jsonPath("$.[*].countryName").value(hasItem(DEFAULT_COUNTRY_NAME.toString())))
            .andExpect(jsonPath("$.[*].countryPra").value(hasItem(DEFAULT_COUNTRY_PRA.toString())))
            .andExpect(jsonPath("$.[*].regionPra").value(hasItem(DEFAULT_REGION_PRA.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getRefCountry() throws Exception {
        // Initialize the database
        refCountryRepository.saveAndFlush(refCountry);

        // Get the refCountry
        restRefCountryMockMvc.perform(get("/api/ref-countries/{id}", refCountry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refCountry.getId().intValue()))
            .andExpect(jsonPath("$.countryId").value(DEFAULT_COUNTRY_ID))
            .andExpect(jsonPath("$.countryName").value(DEFAULT_COUNTRY_NAME.toString()))
            .andExpect(jsonPath("$.countryPra").value(DEFAULT_COUNTRY_PRA.toString()))
            .andExpect(jsonPath("$.regionPra").value(DEFAULT_REGION_PRA.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefCountry() throws Exception {
        // Get the refCountry
        restRefCountryMockMvc.perform(get("/api/ref-countries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefCountry() throws Exception {
        // Initialize the database
        refCountryRepository.saveAndFlush(refCountry);

        int databaseSizeBeforeUpdate = refCountryRepository.findAll().size();

        // Update the refCountry
        RefCountry updatedRefCountry = refCountryRepository.findById(refCountry.getId()).get();
        // Disconnect from session so that the updates on updatedRefCountry are not directly saved in db
        em.detach(updatedRefCountry);
        updatedRefCountry
            .countryId(UPDATED_COUNTRY_ID)
            .countryName(UPDATED_COUNTRY_NAME)
            .countryPra(UPDATED_COUNTRY_PRA)
            .regionPra(UPDATED_REGION_PRA)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restRefCountryMockMvc.perform(put("/api/ref-countries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefCountry)))
            .andExpect(status().isOk());

        // Validate the RefCountry in the database
        List<RefCountry> refCountryList = refCountryRepository.findAll();
        assertThat(refCountryList).hasSize(databaseSizeBeforeUpdate);
        RefCountry testRefCountry = refCountryList.get(refCountryList.size() - 1);
        assertThat(testRefCountry.getCountryId()).isEqualTo(UPDATED_COUNTRY_ID);
        assertThat(testRefCountry.getCountryName()).isEqualTo(UPDATED_COUNTRY_NAME);
        assertThat(testRefCountry.getCountryPra()).isEqualTo(UPDATED_COUNTRY_PRA);
        assertThat(testRefCountry.getRegionPra()).isEqualTo(UPDATED_REGION_PRA);
        assertThat(testRefCountry.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRefCountry.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRefCountry.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testRefCountry.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testRefCountry.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingRefCountry() throws Exception {
        int databaseSizeBeforeUpdate = refCountryRepository.findAll().size();

        // Create the RefCountry

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefCountryMockMvc.perform(put("/api/ref-countries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refCountry)))
            .andExpect(status().isBadRequest());

        // Validate the RefCountry in the database
        List<RefCountry> refCountryList = refCountryRepository.findAll();
        assertThat(refCountryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefCountry() throws Exception {
        // Initialize the database
        refCountryRepository.saveAndFlush(refCountry);

        int databaseSizeBeforeDelete = refCountryRepository.findAll().size();

        // Get the refCountry
        restRefCountryMockMvc.perform(delete("/api/ref-countries/{id}", refCountry.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefCountry> refCountryList = refCountryRepository.findAll();
        assertThat(refCountryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefCountry.class);
        RefCountry refCountry1 = new RefCountry();
        refCountry1.setId(1L);
        RefCountry refCountry2 = new RefCountry();
        refCountry2.setId(refCountry1.getId());
        assertThat(refCountry1).isEqualTo(refCountry2);
        refCountry2.setId(2L);
        assertThat(refCountry1).isNotEqualTo(refCountry2);
        refCountry1.setId(null);
        assertThat(refCountry1).isNotEqualTo(refCountry2);
    }
}

package com.demo.crud.web.rest;

import com.demo.crud.DealmaintenanceserviceApp;

import com.demo.crud.domain.RefFacilityType;
import com.demo.crud.repository.RefFacilityTypeRepository;
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
 * Test class for the RefFacilityTypeResource REST controller.
 *
 * @see RefFacilityTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DealmaintenanceserviceApp.class)
public class RefFacilityTypeResourceIntTest {

    private static final Integer DEFAULT_FACILITY_TYPE_ID = 1;
    private static final Integer UPDATED_FACILITY_TYPE_ID = 2;

    private static final String DEFAULT_FACILITY_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FACILITY_TYPE_NAME = "BBBBBBBBBB";

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
    private RefFacilityTypeRepository refFacilityTypeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefFacilityTypeMockMvc;

    private RefFacilityType refFacilityType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefFacilityTypeResource refFacilityTypeResource = new RefFacilityTypeResource(refFacilityTypeRepository);
        this.restRefFacilityTypeMockMvc = MockMvcBuilders.standaloneSetup(refFacilityTypeResource)
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
    public static RefFacilityType createEntity(EntityManager em) {
        RefFacilityType refFacilityType = new RefFacilityType()
            .facilityTypeId(DEFAULT_FACILITY_TYPE_ID)
            .facilityTypeName(DEFAULT_FACILITY_TYPE_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return refFacilityType;
    }

    @Before
    public void initTest() {
        refFacilityType = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefFacilityType() throws Exception {
        int databaseSizeBeforeCreate = refFacilityTypeRepository.findAll().size();

        // Create the RefFacilityType
        restRefFacilityTypeMockMvc.perform(post("/api/ref-facility-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refFacilityType)))
            .andExpect(status().isCreated());

        // Validate the RefFacilityType in the database
        List<RefFacilityType> refFacilityTypeList = refFacilityTypeRepository.findAll();
        assertThat(refFacilityTypeList).hasSize(databaseSizeBeforeCreate + 1);
        RefFacilityType testRefFacilityType = refFacilityTypeList.get(refFacilityTypeList.size() - 1);
        assertThat(testRefFacilityType.getFacilityTypeId()).isEqualTo(DEFAULT_FACILITY_TYPE_ID);
        assertThat(testRefFacilityType.getFacilityTypeName()).isEqualTo(DEFAULT_FACILITY_TYPE_NAME);
        assertThat(testRefFacilityType.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRefFacilityType.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRefFacilityType.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testRefFacilityType.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testRefFacilityType.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createRefFacilityTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refFacilityTypeRepository.findAll().size();

        // Create the RefFacilityType with an existing ID
        refFacilityType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefFacilityTypeMockMvc.perform(post("/api/ref-facility-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refFacilityType)))
            .andExpect(status().isBadRequest());

        // Validate the RefFacilityType in the database
        List<RefFacilityType> refFacilityTypeList = refFacilityTypeRepository.findAll();
        assertThat(refFacilityTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRefFacilityTypes() throws Exception {
        // Initialize the database
        refFacilityTypeRepository.saveAndFlush(refFacilityType);

        // Get all the refFacilityTypeList
        restRefFacilityTypeMockMvc.perform(get("/api/ref-facility-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refFacilityType.getId().intValue())))
            .andExpect(jsonPath("$.[*].facilityTypeId").value(hasItem(DEFAULT_FACILITY_TYPE_ID)))
            .andExpect(jsonPath("$.[*].facilityTypeName").value(hasItem(DEFAULT_FACILITY_TYPE_NAME.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getRefFacilityType() throws Exception {
        // Initialize the database
        refFacilityTypeRepository.saveAndFlush(refFacilityType);

        // Get the refFacilityType
        restRefFacilityTypeMockMvc.perform(get("/api/ref-facility-types/{id}", refFacilityType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refFacilityType.getId().intValue()))
            .andExpect(jsonPath("$.facilityTypeId").value(DEFAULT_FACILITY_TYPE_ID))
            .andExpect(jsonPath("$.facilityTypeName").value(DEFAULT_FACILITY_TYPE_NAME.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefFacilityType() throws Exception {
        // Get the refFacilityType
        restRefFacilityTypeMockMvc.perform(get("/api/ref-facility-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefFacilityType() throws Exception {
        // Initialize the database
        refFacilityTypeRepository.saveAndFlush(refFacilityType);

        int databaseSizeBeforeUpdate = refFacilityTypeRepository.findAll().size();

        // Update the refFacilityType
        RefFacilityType updatedRefFacilityType = refFacilityTypeRepository.findById(refFacilityType.getId()).get();
        // Disconnect from session so that the updates on updatedRefFacilityType are not directly saved in db
        em.detach(updatedRefFacilityType);
        updatedRefFacilityType
            .facilityTypeId(UPDATED_FACILITY_TYPE_ID)
            .facilityTypeName(UPDATED_FACILITY_TYPE_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restRefFacilityTypeMockMvc.perform(put("/api/ref-facility-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefFacilityType)))
            .andExpect(status().isOk());

        // Validate the RefFacilityType in the database
        List<RefFacilityType> refFacilityTypeList = refFacilityTypeRepository.findAll();
        assertThat(refFacilityTypeList).hasSize(databaseSizeBeforeUpdate);
        RefFacilityType testRefFacilityType = refFacilityTypeList.get(refFacilityTypeList.size() - 1);
        assertThat(testRefFacilityType.getFacilityTypeId()).isEqualTo(UPDATED_FACILITY_TYPE_ID);
        assertThat(testRefFacilityType.getFacilityTypeName()).isEqualTo(UPDATED_FACILITY_TYPE_NAME);
        assertThat(testRefFacilityType.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRefFacilityType.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRefFacilityType.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testRefFacilityType.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testRefFacilityType.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingRefFacilityType() throws Exception {
        int databaseSizeBeforeUpdate = refFacilityTypeRepository.findAll().size();

        // Create the RefFacilityType

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefFacilityTypeMockMvc.perform(put("/api/ref-facility-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refFacilityType)))
            .andExpect(status().isBadRequest());

        // Validate the RefFacilityType in the database
        List<RefFacilityType> refFacilityTypeList = refFacilityTypeRepository.findAll();
        assertThat(refFacilityTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefFacilityType() throws Exception {
        // Initialize the database
        refFacilityTypeRepository.saveAndFlush(refFacilityType);

        int databaseSizeBeforeDelete = refFacilityTypeRepository.findAll().size();

        // Get the refFacilityType
        restRefFacilityTypeMockMvc.perform(delete("/api/ref-facility-types/{id}", refFacilityType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefFacilityType> refFacilityTypeList = refFacilityTypeRepository.findAll();
        assertThat(refFacilityTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefFacilityType.class);
        RefFacilityType refFacilityType1 = new RefFacilityType();
        refFacilityType1.setId(1L);
        RefFacilityType refFacilityType2 = new RefFacilityType();
        refFacilityType2.setId(refFacilityType1.getId());
        assertThat(refFacilityType1).isEqualTo(refFacilityType2);
        refFacilityType2.setId(2L);
        assertThat(refFacilityType1).isNotEqualTo(refFacilityType2);
        refFacilityType1.setId(null);
        assertThat(refFacilityType1).isNotEqualTo(refFacilityType2);
    }
}

package com.demo.crud.web.rest;

import com.demo.crud.DealmaintenanceserviceApp;

import com.demo.crud.domain.RefBookingEntity;
import com.demo.crud.repository.RefBookingEntityRepository;
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
 * Test class for the RefBookingEntityResource REST controller.
 *
 * @see RefBookingEntityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DealmaintenanceserviceApp.class)
public class RefBookingEntityResourceIntTest {

    private static final Integer DEFAULT_BOOKING_ENTITY_ID = 1;
    private static final Integer UPDATED_BOOKING_ENTITY_ID = 2;

    private static final String DEFAULT_BOOKING_ENTITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BOOKING_ENTITY_NAME = "BBBBBBBBBB";

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
    private RefBookingEntityRepository refBookingEntityRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefBookingEntityMockMvc;

    private RefBookingEntity refBookingEntity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefBookingEntityResource refBookingEntityResource = new RefBookingEntityResource(refBookingEntityRepository);
        this.restRefBookingEntityMockMvc = MockMvcBuilders.standaloneSetup(refBookingEntityResource)
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
    public static RefBookingEntity createEntity(EntityManager em) {
        RefBookingEntity refBookingEntity = new RefBookingEntity()
            .bookingEntityId(DEFAULT_BOOKING_ENTITY_ID)
            .bookingEntityName(DEFAULT_BOOKING_ENTITY_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return refBookingEntity;
    }

    @Before
    public void initTest() {
        refBookingEntity = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefBookingEntity() throws Exception {
        int databaseSizeBeforeCreate = refBookingEntityRepository.findAll().size();

        // Create the RefBookingEntity
        restRefBookingEntityMockMvc.perform(post("/api/ref-booking-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refBookingEntity)))
            .andExpect(status().isCreated());

        // Validate the RefBookingEntity in the database
        List<RefBookingEntity> refBookingEntityList = refBookingEntityRepository.findAll();
        assertThat(refBookingEntityList).hasSize(databaseSizeBeforeCreate + 1);
        RefBookingEntity testRefBookingEntity = refBookingEntityList.get(refBookingEntityList.size() - 1);
        assertThat(testRefBookingEntity.getBookingEntityId()).isEqualTo(DEFAULT_BOOKING_ENTITY_ID);
        assertThat(testRefBookingEntity.getBookingEntityName()).isEqualTo(DEFAULT_BOOKING_ENTITY_NAME);
        assertThat(testRefBookingEntity.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRefBookingEntity.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRefBookingEntity.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testRefBookingEntity.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testRefBookingEntity.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createRefBookingEntityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refBookingEntityRepository.findAll().size();

        // Create the RefBookingEntity with an existing ID
        refBookingEntity.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefBookingEntityMockMvc.perform(post("/api/ref-booking-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refBookingEntity)))
            .andExpect(status().isBadRequest());

        // Validate the RefBookingEntity in the database
        List<RefBookingEntity> refBookingEntityList = refBookingEntityRepository.findAll();
        assertThat(refBookingEntityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRefBookingEntities() throws Exception {
        // Initialize the database
        refBookingEntityRepository.saveAndFlush(refBookingEntity);

        // Get all the refBookingEntityList
        restRefBookingEntityMockMvc.perform(get("/api/ref-booking-entities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refBookingEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].bookingEntityId").value(hasItem(DEFAULT_BOOKING_ENTITY_ID)))
            .andExpect(jsonPath("$.[*].bookingEntityName").value(hasItem(DEFAULT_BOOKING_ENTITY_NAME.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getRefBookingEntity() throws Exception {
        // Initialize the database
        refBookingEntityRepository.saveAndFlush(refBookingEntity);

        // Get the refBookingEntity
        restRefBookingEntityMockMvc.perform(get("/api/ref-booking-entities/{id}", refBookingEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refBookingEntity.getId().intValue()))
            .andExpect(jsonPath("$.bookingEntityId").value(DEFAULT_BOOKING_ENTITY_ID))
            .andExpect(jsonPath("$.bookingEntityName").value(DEFAULT_BOOKING_ENTITY_NAME.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefBookingEntity() throws Exception {
        // Get the refBookingEntity
        restRefBookingEntityMockMvc.perform(get("/api/ref-booking-entities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefBookingEntity() throws Exception {
        // Initialize the database
        refBookingEntityRepository.saveAndFlush(refBookingEntity);

        int databaseSizeBeforeUpdate = refBookingEntityRepository.findAll().size();

        // Update the refBookingEntity
        RefBookingEntity updatedRefBookingEntity = refBookingEntityRepository.findById(refBookingEntity.getId()).get();
        // Disconnect from session so that the updates on updatedRefBookingEntity are not directly saved in db
        em.detach(updatedRefBookingEntity);
        updatedRefBookingEntity
            .bookingEntityId(UPDATED_BOOKING_ENTITY_ID)
            .bookingEntityName(UPDATED_BOOKING_ENTITY_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restRefBookingEntityMockMvc.perform(put("/api/ref-booking-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefBookingEntity)))
            .andExpect(status().isOk());

        // Validate the RefBookingEntity in the database
        List<RefBookingEntity> refBookingEntityList = refBookingEntityRepository.findAll();
        assertThat(refBookingEntityList).hasSize(databaseSizeBeforeUpdate);
        RefBookingEntity testRefBookingEntity = refBookingEntityList.get(refBookingEntityList.size() - 1);
        assertThat(testRefBookingEntity.getBookingEntityId()).isEqualTo(UPDATED_BOOKING_ENTITY_ID);
        assertThat(testRefBookingEntity.getBookingEntityName()).isEqualTo(UPDATED_BOOKING_ENTITY_NAME);
        assertThat(testRefBookingEntity.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRefBookingEntity.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRefBookingEntity.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testRefBookingEntity.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testRefBookingEntity.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingRefBookingEntity() throws Exception {
        int databaseSizeBeforeUpdate = refBookingEntityRepository.findAll().size();

        // Create the RefBookingEntity

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefBookingEntityMockMvc.perform(put("/api/ref-booking-entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refBookingEntity)))
            .andExpect(status().isBadRequest());

        // Validate the RefBookingEntity in the database
        List<RefBookingEntity> refBookingEntityList = refBookingEntityRepository.findAll();
        assertThat(refBookingEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefBookingEntity() throws Exception {
        // Initialize the database
        refBookingEntityRepository.saveAndFlush(refBookingEntity);

        int databaseSizeBeforeDelete = refBookingEntityRepository.findAll().size();

        // Get the refBookingEntity
        restRefBookingEntityMockMvc.perform(delete("/api/ref-booking-entities/{id}", refBookingEntity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefBookingEntity> refBookingEntityList = refBookingEntityRepository.findAll();
        assertThat(refBookingEntityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefBookingEntity.class);
        RefBookingEntity refBookingEntity1 = new RefBookingEntity();
        refBookingEntity1.setId(1L);
        RefBookingEntity refBookingEntity2 = new RefBookingEntity();
        refBookingEntity2.setId(refBookingEntity1.getId());
        assertThat(refBookingEntity1).isEqualTo(refBookingEntity2);
        refBookingEntity2.setId(2L);
        assertThat(refBookingEntity1).isNotEqualTo(refBookingEntity2);
        refBookingEntity1.setId(null);
        assertThat(refBookingEntity1).isNotEqualTo(refBookingEntity2);
    }
}

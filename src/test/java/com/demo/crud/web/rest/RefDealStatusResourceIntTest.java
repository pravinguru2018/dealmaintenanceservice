package com.demo.crud.web.rest;

import com.demo.crud.DealmaintenanceserviceApp;

import com.demo.crud.domain.RefDealStatus;
import com.demo.crud.repository.RefDealStatusRepository;
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
 * Test class for the RefDealStatusResource REST controller.
 *
 * @see RefDealStatusResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DealmaintenanceserviceApp.class)
public class RefDealStatusResourceIntTest {

    private static final Integer DEFAULT_DEAL_STATUS_ID = 1;
    private static final Integer UPDATED_DEAL_STATUS_ID = 2;

    private static final String DEFAULT_DEAL_STATUS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEAL_STATUS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CREDIT_APPROVAL_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_CREDIT_APPROVAL_STATUS = "BBBBBBBBBB";

    private static final Integer DEFAULT_DISPLAY_ORDER = 1;
    private static final Integer UPDATED_DISPLAY_ORDER = 2;

    private static final String DEFAULT_DISPLAY_KEY = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY_KEY = "BBBBBBBBBB";

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
    private RefDealStatusRepository refDealStatusRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefDealStatusMockMvc;

    private RefDealStatus refDealStatus;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefDealStatusResource refDealStatusResource = new RefDealStatusResource(refDealStatusRepository);
        this.restRefDealStatusMockMvc = MockMvcBuilders.standaloneSetup(refDealStatusResource)
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
    public static RefDealStatus createEntity(EntityManager em) {
        RefDealStatus refDealStatus = new RefDealStatus()
            .dealStatusId(DEFAULT_DEAL_STATUS_ID)
            .dealStatusName(DEFAULT_DEAL_STATUS_NAME)
            .creditApprovalStatus(DEFAULT_CREDIT_APPROVAL_STATUS)
            .displayOrder(DEFAULT_DISPLAY_ORDER)
            .displayKey(DEFAULT_DISPLAY_KEY)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return refDealStatus;
    }

    @Before
    public void initTest() {
        refDealStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefDealStatus() throws Exception {
        int databaseSizeBeforeCreate = refDealStatusRepository.findAll().size();

        // Create the RefDealStatus
        restRefDealStatusMockMvc.perform(post("/api/ref-deal-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refDealStatus)))
            .andExpect(status().isCreated());

        // Validate the RefDealStatus in the database
        List<RefDealStatus> refDealStatusList = refDealStatusRepository.findAll();
        assertThat(refDealStatusList).hasSize(databaseSizeBeforeCreate + 1);
        RefDealStatus testRefDealStatus = refDealStatusList.get(refDealStatusList.size() - 1);
        assertThat(testRefDealStatus.getDealStatusId()).isEqualTo(DEFAULT_DEAL_STATUS_ID);
        assertThat(testRefDealStatus.getDealStatusName()).isEqualTo(DEFAULT_DEAL_STATUS_NAME);
        assertThat(testRefDealStatus.getCreditApprovalStatus()).isEqualTo(DEFAULT_CREDIT_APPROVAL_STATUS);
        assertThat(testRefDealStatus.getDisplayOrder()).isEqualTo(DEFAULT_DISPLAY_ORDER);
        assertThat(testRefDealStatus.getDisplayKey()).isEqualTo(DEFAULT_DISPLAY_KEY);
        assertThat(testRefDealStatus.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRefDealStatus.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRefDealStatus.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testRefDealStatus.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testRefDealStatus.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createRefDealStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refDealStatusRepository.findAll().size();

        // Create the RefDealStatus with an existing ID
        refDealStatus.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefDealStatusMockMvc.perform(post("/api/ref-deal-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refDealStatus)))
            .andExpect(status().isBadRequest());

        // Validate the RefDealStatus in the database
        List<RefDealStatus> refDealStatusList = refDealStatusRepository.findAll();
        assertThat(refDealStatusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRefDealStatuses() throws Exception {
        // Initialize the database
        refDealStatusRepository.saveAndFlush(refDealStatus);

        // Get all the refDealStatusList
        restRefDealStatusMockMvc.perform(get("/api/ref-deal-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refDealStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].dealStatusId").value(hasItem(DEFAULT_DEAL_STATUS_ID)))
            .andExpect(jsonPath("$.[*].dealStatusName").value(hasItem(DEFAULT_DEAL_STATUS_NAME.toString())))
            .andExpect(jsonPath("$.[*].creditApprovalStatus").value(hasItem(DEFAULT_CREDIT_APPROVAL_STATUS.toString())))
            .andExpect(jsonPath("$.[*].displayOrder").value(hasItem(DEFAULT_DISPLAY_ORDER)))
            .andExpect(jsonPath("$.[*].displayKey").value(hasItem(DEFAULT_DISPLAY_KEY.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getRefDealStatus() throws Exception {
        // Initialize the database
        refDealStatusRepository.saveAndFlush(refDealStatus);

        // Get the refDealStatus
        restRefDealStatusMockMvc.perform(get("/api/ref-deal-statuses/{id}", refDealStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refDealStatus.getId().intValue()))
            .andExpect(jsonPath("$.dealStatusId").value(DEFAULT_DEAL_STATUS_ID))
            .andExpect(jsonPath("$.dealStatusName").value(DEFAULT_DEAL_STATUS_NAME.toString()))
            .andExpect(jsonPath("$.creditApprovalStatus").value(DEFAULT_CREDIT_APPROVAL_STATUS.toString()))
            .andExpect(jsonPath("$.displayOrder").value(DEFAULT_DISPLAY_ORDER))
            .andExpect(jsonPath("$.displayKey").value(DEFAULT_DISPLAY_KEY.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefDealStatus() throws Exception {
        // Get the refDealStatus
        restRefDealStatusMockMvc.perform(get("/api/ref-deal-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefDealStatus() throws Exception {
        // Initialize the database
        refDealStatusRepository.saveAndFlush(refDealStatus);

        int databaseSizeBeforeUpdate = refDealStatusRepository.findAll().size();

        // Update the refDealStatus
        RefDealStatus updatedRefDealStatus = refDealStatusRepository.findById(refDealStatus.getId()).get();
        // Disconnect from session so that the updates on updatedRefDealStatus are not directly saved in db
        em.detach(updatedRefDealStatus);
        updatedRefDealStatus
            .dealStatusId(UPDATED_DEAL_STATUS_ID)
            .dealStatusName(UPDATED_DEAL_STATUS_NAME)
            .creditApprovalStatus(UPDATED_CREDIT_APPROVAL_STATUS)
            .displayOrder(UPDATED_DISPLAY_ORDER)
            .displayKey(UPDATED_DISPLAY_KEY)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restRefDealStatusMockMvc.perform(put("/api/ref-deal-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefDealStatus)))
            .andExpect(status().isOk());

        // Validate the RefDealStatus in the database
        List<RefDealStatus> refDealStatusList = refDealStatusRepository.findAll();
        assertThat(refDealStatusList).hasSize(databaseSizeBeforeUpdate);
        RefDealStatus testRefDealStatus = refDealStatusList.get(refDealStatusList.size() - 1);
        assertThat(testRefDealStatus.getDealStatusId()).isEqualTo(UPDATED_DEAL_STATUS_ID);
        assertThat(testRefDealStatus.getDealStatusName()).isEqualTo(UPDATED_DEAL_STATUS_NAME);
        assertThat(testRefDealStatus.getCreditApprovalStatus()).isEqualTo(UPDATED_CREDIT_APPROVAL_STATUS);
        assertThat(testRefDealStatus.getDisplayOrder()).isEqualTo(UPDATED_DISPLAY_ORDER);
        assertThat(testRefDealStatus.getDisplayKey()).isEqualTo(UPDATED_DISPLAY_KEY);
        assertThat(testRefDealStatus.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRefDealStatus.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRefDealStatus.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testRefDealStatus.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testRefDealStatus.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingRefDealStatus() throws Exception {
        int databaseSizeBeforeUpdate = refDealStatusRepository.findAll().size();

        // Create the RefDealStatus

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefDealStatusMockMvc.perform(put("/api/ref-deal-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refDealStatus)))
            .andExpect(status().isBadRequest());

        // Validate the RefDealStatus in the database
        List<RefDealStatus> refDealStatusList = refDealStatusRepository.findAll();
        assertThat(refDealStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefDealStatus() throws Exception {
        // Initialize the database
        refDealStatusRepository.saveAndFlush(refDealStatus);

        int databaseSizeBeforeDelete = refDealStatusRepository.findAll().size();

        // Get the refDealStatus
        restRefDealStatusMockMvc.perform(delete("/api/ref-deal-statuses/{id}", refDealStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefDealStatus> refDealStatusList = refDealStatusRepository.findAll();
        assertThat(refDealStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefDealStatus.class);
        RefDealStatus refDealStatus1 = new RefDealStatus();
        refDealStatus1.setId(1L);
        RefDealStatus refDealStatus2 = new RefDealStatus();
        refDealStatus2.setId(refDealStatus1.getId());
        assertThat(refDealStatus1).isEqualTo(refDealStatus2);
        refDealStatus2.setId(2L);
        assertThat(refDealStatus1).isNotEqualTo(refDealStatus2);
        refDealStatus1.setId(null);
        assertThat(refDealStatus1).isNotEqualTo(refDealStatus2);
    }
}

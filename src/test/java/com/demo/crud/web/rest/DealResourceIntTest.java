package com.demo.crud.web.rest;

import com.demo.crud.DealmaintenanceserviceApp;

import com.demo.crud.domain.Deal;
import com.demo.crud.repository.DealRepository;
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
 * Test class for the DealResource REST controller.
 *
 * @see DealResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DealmaintenanceserviceApp.class)
public class DealResourceIntTest {

    private static final Integer DEFAULT_DEAL_ID = 1;
    private static final Integer UPDATED_DEAL_ID = 2;

    private static final String DEFAULT_PROJECT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT_SPONSOR = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_SPONSOR = "BBBBBBBBBB";

    private static final String DEFAULT_TARGET_ASSET = "AAAAAAAAAA";
    private static final String UPDATED_TARGET_ASSET = "BBBBBBBBBB";

    private static final String DEFAULT_SYNDICATE_OWNER = "AAAAAAAAAA";
    private static final String UPDATED_SYNDICATE_OWNER = "BBBBBBBBBB";

    private static final String DEFAULT_DEAL_PIPELINE_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEAL_PIPELINE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGINATION_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_ORIGINATION_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_RELATIONSHIP_MANAGER = "AAAAAAAAAA";
    private static final String UPDATED_RELATIONSHIP_MANAGER = "BBBBBBBBBB";

    private static final String DEFAULT_LLF = "AAAAAAAAAA";
    private static final String UPDATED_LLF = "BBBBBBBBBB";

    private static final String DEFAULT_REGULATORY_APPROVAL_REQUIRED = "AAAAAAAAAA";
    private static final String UPDATED_REGULATORY_APPROVAL_REQUIRED = "BBBBBBBBBB";

    private static final String DEFAULT_REGULATORY_APPROVAL_OBTAINED = "AAAAAAAAAA";
    private static final String UPDATED_REGULATORY_APPROVAL_OBTAINED = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_OF_INCORPORATION = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_OF_INCORPORATION = "BBBBBBBBBB";

    private static final Instant DEFAULT_REGULATORY_APPROVAL_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REGULATORY_APPROVAL_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DEAL_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DEAL_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_NOTE = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREDIT_APPROVAL_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREDIT_APPROVAL_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ALLOCATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ALLOCATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SYNDICATION_LAUNCH_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SYNDICATION_LAUNCH_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_COMMITMENT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_COMMITMENT_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CREDIT_APPROVAL_OFF_RISK_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREDIT_APPROVAL_OFF_RISK_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREDIT_OFFICER = "AAAAAAAAAA";
    private static final String UPDATED_CREDIT_OFFICER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_BY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_BY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDealMockMvc;

    private Deal deal;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DealResource dealResource = new DealResource(dealRepository);
        this.restDealMockMvc = MockMvcBuilders.standaloneSetup(dealResource)
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
    public static Deal createEntity(EntityManager em) {
        Deal deal = new Deal()
            .dealId(DEFAULT_DEAL_ID)
            .projectName(DEFAULT_PROJECT_NAME)
            .clientSponsor(DEFAULT_CLIENT_SPONSOR)
            .targetAsset(DEFAULT_TARGET_ASSET)
            .syndicateOwner(DEFAULT_SYNDICATE_OWNER)
            .dealPipelineId(DEFAULT_DEAL_PIPELINE_ID)
            .originationContact(DEFAULT_ORIGINATION_CONTACT)
            .relationshipManager(DEFAULT_RELATIONSHIP_MANAGER)
            .llf(DEFAULT_LLF)
            .regulatoryApprovalRequired(DEFAULT_REGULATORY_APPROVAL_REQUIRED)
            .regulatoryApprovalObtained(DEFAULT_REGULATORY_APPROVAL_OBTAINED)
            .countryOfIncorporation(DEFAULT_COUNTRY_OF_INCORPORATION)
            .regulatoryApprovalDate(DEFAULT_REGULATORY_APPROVAL_DATE)
            .dealDescription(DEFAULT_DEAL_DESCRIPTION)
            .updateNote(DEFAULT_UPDATE_NOTE)
            .creditApprovalDate(DEFAULT_CREDIT_APPROVAL_DATE)
            .allocationDate(DEFAULT_ALLOCATION_DATE)
            .syndicationLaunchDate(DEFAULT_SYNDICATION_LAUNCH_DATE)
            .commitmentDate(DEFAULT_COMMITMENT_DATE)
            .creditApprovalOffRiskDate(DEFAULT_CREDIT_APPROVAL_OFF_RISK_DATE)
            .creditOfficer(DEFAULT_CREDIT_OFFICER)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY);
        return deal;
    }

    @Before
    public void initTest() {
        deal = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeal() throws Exception {
        int databaseSizeBeforeCreate = dealRepository.findAll().size();

        // Create the Deal
        restDealMockMvc.perform(post("/api/deals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deal)))
            .andExpect(status().isCreated());

        // Validate the Deal in the database
        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeCreate + 1);
        Deal testDeal = dealList.get(dealList.size() - 1);
        assertThat(testDeal.getDealId()).isEqualTo(DEFAULT_DEAL_ID);
        assertThat(testDeal.getProjectName()).isEqualTo(DEFAULT_PROJECT_NAME);
        assertThat(testDeal.getClientSponsor()).isEqualTo(DEFAULT_CLIENT_SPONSOR);
        assertThat(testDeal.getTargetAsset()).isEqualTo(DEFAULT_TARGET_ASSET);
        assertThat(testDeal.getSyndicateOwner()).isEqualTo(DEFAULT_SYNDICATE_OWNER);
        assertThat(testDeal.getDealPipelineId()).isEqualTo(DEFAULT_DEAL_PIPELINE_ID);
        assertThat(testDeal.getOriginationContact()).isEqualTo(DEFAULT_ORIGINATION_CONTACT);
        assertThat(testDeal.getRelationshipManager()).isEqualTo(DEFAULT_RELATIONSHIP_MANAGER);
        assertThat(testDeal.getLlf()).isEqualTo(DEFAULT_LLF);
        assertThat(testDeal.getRegulatoryApprovalRequired()).isEqualTo(DEFAULT_REGULATORY_APPROVAL_REQUIRED);
        assertThat(testDeal.getRegulatoryApprovalObtained()).isEqualTo(DEFAULT_REGULATORY_APPROVAL_OBTAINED);
        assertThat(testDeal.getCountryOfIncorporation()).isEqualTo(DEFAULT_COUNTRY_OF_INCORPORATION);
        assertThat(testDeal.getRegulatoryApprovalDate()).isEqualTo(DEFAULT_REGULATORY_APPROVAL_DATE);
        assertThat(testDeal.getDealDescription()).isEqualTo(DEFAULT_DEAL_DESCRIPTION);
        assertThat(testDeal.getUpdateNote()).isEqualTo(DEFAULT_UPDATE_NOTE);
        assertThat(testDeal.getCreditApprovalDate()).isEqualTo(DEFAULT_CREDIT_APPROVAL_DATE);
        assertThat(testDeal.getAllocationDate()).isEqualTo(DEFAULT_ALLOCATION_DATE);
        assertThat(testDeal.getSyndicationLaunchDate()).isEqualTo(DEFAULT_SYNDICATION_LAUNCH_DATE);
        assertThat(testDeal.getCommitmentDate()).isEqualTo(DEFAULT_COMMITMENT_DATE);
        assertThat(testDeal.getCreditApprovalOffRiskDate()).isEqualTo(DEFAULT_CREDIT_APPROVAL_OFF_RISK_DATE);
        assertThat(testDeal.getCreditOfficer()).isEqualTo(DEFAULT_CREDIT_OFFICER);
        assertThat(testDeal.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testDeal.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDeal.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testDeal.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createDealWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dealRepository.findAll().size();

        // Create the Deal with an existing ID
        deal.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDealMockMvc.perform(post("/api/deals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deal)))
            .andExpect(status().isBadRequest());

        // Validate the Deal in the database
        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDeals() throws Exception {
        // Initialize the database
        dealRepository.saveAndFlush(deal);

        // Get all the dealList
        restDealMockMvc.perform(get("/api/deals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deal.getId().intValue())))
            .andExpect(jsonPath("$.[*].dealId").value(hasItem(DEFAULT_DEAL_ID)))
            .andExpect(jsonPath("$.[*].projectName").value(hasItem(DEFAULT_PROJECT_NAME.toString())))
            .andExpect(jsonPath("$.[*].clientSponsor").value(hasItem(DEFAULT_CLIENT_SPONSOR.toString())))
            .andExpect(jsonPath("$.[*].targetAsset").value(hasItem(DEFAULT_TARGET_ASSET.toString())))
            .andExpect(jsonPath("$.[*].syndicateOwner").value(hasItem(DEFAULT_SYNDICATE_OWNER.toString())))
            .andExpect(jsonPath("$.[*].dealPipelineId").value(hasItem(DEFAULT_DEAL_PIPELINE_ID.toString())))
            .andExpect(jsonPath("$.[*].originationContact").value(hasItem(DEFAULT_ORIGINATION_CONTACT.toString())))
            .andExpect(jsonPath("$.[*].relationshipManager").value(hasItem(DEFAULT_RELATIONSHIP_MANAGER.toString())))
            .andExpect(jsonPath("$.[*].llf").value(hasItem(DEFAULT_LLF.toString())))
            .andExpect(jsonPath("$.[*].regulatoryApprovalRequired").value(hasItem(DEFAULT_REGULATORY_APPROVAL_REQUIRED.toString())))
            .andExpect(jsonPath("$.[*].regulatoryApprovalObtained").value(hasItem(DEFAULT_REGULATORY_APPROVAL_OBTAINED.toString())))
            .andExpect(jsonPath("$.[*].countryOfIncorporation").value(hasItem(DEFAULT_COUNTRY_OF_INCORPORATION.toString())))
            .andExpect(jsonPath("$.[*].regulatoryApprovalDate").value(hasItem(DEFAULT_REGULATORY_APPROVAL_DATE.toString())))
            .andExpect(jsonPath("$.[*].dealDescription").value(hasItem(DEFAULT_DEAL_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].updateNote").value(hasItem(DEFAULT_UPDATE_NOTE.toString())))
            .andExpect(jsonPath("$.[*].creditApprovalDate").value(hasItem(DEFAULT_CREDIT_APPROVAL_DATE.toString())))
            .andExpect(jsonPath("$.[*].allocationDate").value(hasItem(DEFAULT_ALLOCATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].syndicationLaunchDate").value(hasItem(DEFAULT_SYNDICATION_LAUNCH_DATE.toString())))
            .andExpect(jsonPath("$.[*].commitmentDate").value(hasItem(DEFAULT_COMMITMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].creditApprovalOffRiskDate").value(hasItem(DEFAULT_CREDIT_APPROVAL_OFF_RISK_DATE.toString())))
            .andExpect(jsonPath("$.[*].creditOfficer").value(hasItem(DEFAULT_CREDIT_OFFICER.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())));
    }
    
    @Test
    @Transactional
    public void getDeal() throws Exception {
        // Initialize the database
        dealRepository.saveAndFlush(deal);

        // Get the deal
        restDealMockMvc.perform(get("/api/deals/{id}", deal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(deal.getId().intValue()))
            .andExpect(jsonPath("$.dealId").value(DEFAULT_DEAL_ID))
            .andExpect(jsonPath("$.projectName").value(DEFAULT_PROJECT_NAME.toString()))
            .andExpect(jsonPath("$.clientSponsor").value(DEFAULT_CLIENT_SPONSOR.toString()))
            .andExpect(jsonPath("$.targetAsset").value(DEFAULT_TARGET_ASSET.toString()))
            .andExpect(jsonPath("$.syndicateOwner").value(DEFAULT_SYNDICATE_OWNER.toString()))
            .andExpect(jsonPath("$.dealPipelineId").value(DEFAULT_DEAL_PIPELINE_ID.toString()))
            .andExpect(jsonPath("$.originationContact").value(DEFAULT_ORIGINATION_CONTACT.toString()))
            .andExpect(jsonPath("$.relationshipManager").value(DEFAULT_RELATIONSHIP_MANAGER.toString()))
            .andExpect(jsonPath("$.llf").value(DEFAULT_LLF.toString()))
            .andExpect(jsonPath("$.regulatoryApprovalRequired").value(DEFAULT_REGULATORY_APPROVAL_REQUIRED.toString()))
            .andExpect(jsonPath("$.regulatoryApprovalObtained").value(DEFAULT_REGULATORY_APPROVAL_OBTAINED.toString()))
            .andExpect(jsonPath("$.countryOfIncorporation").value(DEFAULT_COUNTRY_OF_INCORPORATION.toString()))
            .andExpect(jsonPath("$.regulatoryApprovalDate").value(DEFAULT_REGULATORY_APPROVAL_DATE.toString()))
            .andExpect(jsonPath("$.dealDescription").value(DEFAULT_DEAL_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.updateNote").value(DEFAULT_UPDATE_NOTE.toString()))
            .andExpect(jsonPath("$.creditApprovalDate").value(DEFAULT_CREDIT_APPROVAL_DATE.toString()))
            .andExpect(jsonPath("$.allocationDate").value(DEFAULT_ALLOCATION_DATE.toString()))
            .andExpect(jsonPath("$.syndicationLaunchDate").value(DEFAULT_SYNDICATION_LAUNCH_DATE.toString()))
            .andExpect(jsonPath("$.commitmentDate").value(DEFAULT_COMMITMENT_DATE.toString()))
            .andExpect(jsonPath("$.creditApprovalOffRiskDate").value(DEFAULT_CREDIT_APPROVAL_OFF_RISK_DATE.toString()))
            .andExpect(jsonPath("$.creditOfficer").value(DEFAULT_CREDIT_OFFICER.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDeal() throws Exception {
        // Get the deal
        restDealMockMvc.perform(get("/api/deals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeal() throws Exception {
        // Initialize the database
        dealRepository.saveAndFlush(deal);

        int databaseSizeBeforeUpdate = dealRepository.findAll().size();

        // Update the deal
        Deal updatedDeal = dealRepository.findById(deal.getId()).get();
        // Disconnect from session so that the updates on updatedDeal are not directly saved in db
        em.detach(updatedDeal);
        updatedDeal
            .dealId(UPDATED_DEAL_ID)
            .projectName(UPDATED_PROJECT_NAME)
            .clientSponsor(UPDATED_CLIENT_SPONSOR)
            .targetAsset(UPDATED_TARGET_ASSET)
            .syndicateOwner(UPDATED_SYNDICATE_OWNER)
            .dealPipelineId(UPDATED_DEAL_PIPELINE_ID)
            .originationContact(UPDATED_ORIGINATION_CONTACT)
            .relationshipManager(UPDATED_RELATIONSHIP_MANAGER)
            .llf(UPDATED_LLF)
            .regulatoryApprovalRequired(UPDATED_REGULATORY_APPROVAL_REQUIRED)
            .regulatoryApprovalObtained(UPDATED_REGULATORY_APPROVAL_OBTAINED)
            .countryOfIncorporation(UPDATED_COUNTRY_OF_INCORPORATION)
            .regulatoryApprovalDate(UPDATED_REGULATORY_APPROVAL_DATE)
            .dealDescription(UPDATED_DEAL_DESCRIPTION)
            .updateNote(UPDATED_UPDATE_NOTE)
            .creditApprovalDate(UPDATED_CREDIT_APPROVAL_DATE)
            .allocationDate(UPDATED_ALLOCATION_DATE)
            .syndicationLaunchDate(UPDATED_SYNDICATION_LAUNCH_DATE)
            .commitmentDate(UPDATED_COMMITMENT_DATE)
            .creditApprovalOffRiskDate(UPDATED_CREDIT_APPROVAL_OFF_RISK_DATE)
            .creditOfficer(UPDATED_CREDIT_OFFICER)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY);

        restDealMockMvc.perform(put("/api/deals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDeal)))
            .andExpect(status().isOk());

        // Validate the Deal in the database
        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeUpdate);
        Deal testDeal = dealList.get(dealList.size() - 1);
        assertThat(testDeal.getDealId()).isEqualTo(UPDATED_DEAL_ID);
        assertThat(testDeal.getProjectName()).isEqualTo(UPDATED_PROJECT_NAME);
        assertThat(testDeal.getClientSponsor()).isEqualTo(UPDATED_CLIENT_SPONSOR);
        assertThat(testDeal.getTargetAsset()).isEqualTo(UPDATED_TARGET_ASSET);
        assertThat(testDeal.getSyndicateOwner()).isEqualTo(UPDATED_SYNDICATE_OWNER);
        assertThat(testDeal.getDealPipelineId()).isEqualTo(UPDATED_DEAL_PIPELINE_ID);
        assertThat(testDeal.getOriginationContact()).isEqualTo(UPDATED_ORIGINATION_CONTACT);
        assertThat(testDeal.getRelationshipManager()).isEqualTo(UPDATED_RELATIONSHIP_MANAGER);
        assertThat(testDeal.getLlf()).isEqualTo(UPDATED_LLF);
        assertThat(testDeal.getRegulatoryApprovalRequired()).isEqualTo(UPDATED_REGULATORY_APPROVAL_REQUIRED);
        assertThat(testDeal.getRegulatoryApprovalObtained()).isEqualTo(UPDATED_REGULATORY_APPROVAL_OBTAINED);
        assertThat(testDeal.getCountryOfIncorporation()).isEqualTo(UPDATED_COUNTRY_OF_INCORPORATION);
        assertThat(testDeal.getRegulatoryApprovalDate()).isEqualTo(UPDATED_REGULATORY_APPROVAL_DATE);
        assertThat(testDeal.getDealDescription()).isEqualTo(UPDATED_DEAL_DESCRIPTION);
        assertThat(testDeal.getUpdateNote()).isEqualTo(UPDATED_UPDATE_NOTE);
        assertThat(testDeal.getCreditApprovalDate()).isEqualTo(UPDATED_CREDIT_APPROVAL_DATE);
        assertThat(testDeal.getAllocationDate()).isEqualTo(UPDATED_ALLOCATION_DATE);
        assertThat(testDeal.getSyndicationLaunchDate()).isEqualTo(UPDATED_SYNDICATION_LAUNCH_DATE);
        assertThat(testDeal.getCommitmentDate()).isEqualTo(UPDATED_COMMITMENT_DATE);
        assertThat(testDeal.getCreditApprovalOffRiskDate()).isEqualTo(UPDATED_CREDIT_APPROVAL_OFF_RISK_DATE);
        assertThat(testDeal.getCreditOfficer()).isEqualTo(UPDATED_CREDIT_OFFICER);
        assertThat(testDeal.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testDeal.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDeal.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testDeal.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingDeal() throws Exception {
        int databaseSizeBeforeUpdate = dealRepository.findAll().size();

        // Create the Deal

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDealMockMvc.perform(put("/api/deals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deal)))
            .andExpect(status().isBadRequest());

        // Validate the Deal in the database
        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDeal() throws Exception {
        // Initialize the database
        dealRepository.saveAndFlush(deal);

        int databaseSizeBeforeDelete = dealRepository.findAll().size();

        // Get the deal
        restDealMockMvc.perform(delete("/api/deals/{id}", deal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Deal.class);
        Deal deal1 = new Deal();
        deal1.setId(1L);
        Deal deal2 = new Deal();
        deal2.setId(deal1.getId());
        assertThat(deal1).isEqualTo(deal2);
        deal2.setId(2L);
        assertThat(deal1).isNotEqualTo(deal2);
        deal1.setId(null);
        assertThat(deal1).isNotEqualTo(deal2);
    }
}

package com.demo.crud.web.rest;

import com.demo.crud.DealmaintenanceserviceApp;

import com.demo.crud.domain.TrancheMargin;
import com.demo.crud.repository.TrancheMarginRepository;
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
 * Test class for the TrancheMarginResource REST controller.
 *
 * @see TrancheMarginResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DealmaintenanceserviceApp.class)
public class TrancheMarginResourceIntTest {

    private static final Integer DEFAULT_TRANCHE_MARGIN_ID = 1;
    private static final Integer UPDATED_TRANCHE_MARGIN_ID = 2;

    private static final String DEFAULT_MARGIN_SPREAD_BPS = "AAAAAAAAAA";
    private static final String UPDATED_MARGIN_SPREAD_BPS = "BBBBBBBBBB";

    private static final Integer DEFAULT_FLOOR_PERCENTAGE = 1;
    private static final Integer UPDATED_FLOOR_PERCENTAGE = 2;

    private static final String DEFAULT_INTEREST_PAYMENT_FREQUENCY = "AAAAAAAAAA";
    private static final String UPDATED_INTEREST_PAYMENT_FREQUENCY = "BBBBBBBBBB";

    private static final String DEFAULT_TOTAL_PRICING_FLEX_BPS_PA = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL_PRICING_FLEX_BPS_PA = "BBBBBBBBBB";

    private static final Integer DEFAULT_UNDERWRITE_FEES_BPS = 1;
    private static final Integer UPDATED_UNDERWRITE_FEES_BPS = 2;

    private static final Integer DEFAULT_PARTICIPATION_FEES = 1;
    private static final Integer UPDATED_PARTICIPATION_FEES = 2;

    private static final Integer DEFAULT_EXTENSION_FEES_6_MONTHS = 1;
    private static final Integer UPDATED_EXTENSION_FEES_6_MONTHS = 2;

    private static final Integer DEFAULT_EXTENSION_FEES_6_TO_12_MONTHS = 1;
    private static final Integer UPDATED_EXTENSION_FEES_6_TO_12_MONTHS = 2;

    private static final String DEFAULT_OTHER_FLEX = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_FLEX = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATED_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private TrancheMarginRepository trancheMarginRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTrancheMarginMockMvc;

    private TrancheMargin trancheMargin;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TrancheMarginResource trancheMarginResource = new TrancheMarginResource(trancheMarginRepository);
        this.restTrancheMarginMockMvc = MockMvcBuilders.standaloneSetup(trancheMarginResource)
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
    public static TrancheMargin createEntity(EntityManager em) {
        TrancheMargin trancheMargin = new TrancheMargin()
            .trancheMarginId(DEFAULT_TRANCHE_MARGIN_ID)
            .marginSpreadBps(DEFAULT_MARGIN_SPREAD_BPS)
            .floorPercentage(DEFAULT_FLOOR_PERCENTAGE)
            .interestPaymentFrequency(DEFAULT_INTEREST_PAYMENT_FREQUENCY)
            .totalPricingFlexBpsPa(DEFAULT_TOTAL_PRICING_FLEX_BPS_PA)
            .underwriteFeesBps(DEFAULT_UNDERWRITE_FEES_BPS)
            .participationFees(DEFAULT_PARTICIPATION_FEES)
            .extensionFees6Months(DEFAULT_EXTENSION_FEES_6_MONTHS)
            .extensionFees6To12Months(DEFAULT_EXTENSION_FEES_6_TO_12_MONTHS)
            .otherFlex(DEFAULT_OTHER_FLEX)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return trancheMargin;
    }

    @Before
    public void initTest() {
        trancheMargin = createEntity(em);
    }

    @Test
    @Transactional
    public void createTrancheMargin() throws Exception {
        int databaseSizeBeforeCreate = trancheMarginRepository.findAll().size();

        // Create the TrancheMargin
        restTrancheMarginMockMvc.perform(post("/api/tranche-margins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trancheMargin)))
            .andExpect(status().isCreated());

        // Validate the TrancheMargin in the database
        List<TrancheMargin> trancheMarginList = trancheMarginRepository.findAll();
        assertThat(trancheMarginList).hasSize(databaseSizeBeforeCreate + 1);
        TrancheMargin testTrancheMargin = trancheMarginList.get(trancheMarginList.size() - 1);
        assertThat(testTrancheMargin.getTrancheMarginId()).isEqualTo(DEFAULT_TRANCHE_MARGIN_ID);
        assertThat(testTrancheMargin.getMarginSpreadBps()).isEqualTo(DEFAULT_MARGIN_SPREAD_BPS);
        assertThat(testTrancheMargin.getFloorPercentage()).isEqualTo(DEFAULT_FLOOR_PERCENTAGE);
        assertThat(testTrancheMargin.getInterestPaymentFrequency()).isEqualTo(DEFAULT_INTEREST_PAYMENT_FREQUENCY);
        assertThat(testTrancheMargin.getTotalPricingFlexBpsPa()).isEqualTo(DEFAULT_TOTAL_PRICING_FLEX_BPS_PA);
        assertThat(testTrancheMargin.getUnderwriteFeesBps()).isEqualTo(DEFAULT_UNDERWRITE_FEES_BPS);
        assertThat(testTrancheMargin.getParticipationFees()).isEqualTo(DEFAULT_PARTICIPATION_FEES);
        assertThat(testTrancheMargin.getExtensionFees6Months()).isEqualTo(DEFAULT_EXTENSION_FEES_6_MONTHS);
        assertThat(testTrancheMargin.getExtensionFees6To12Months()).isEqualTo(DEFAULT_EXTENSION_FEES_6_TO_12_MONTHS);
        assertThat(testTrancheMargin.getOtherFlex()).isEqualTo(DEFAULT_OTHER_FLEX);
        assertThat(testTrancheMargin.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testTrancheMargin.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testTrancheMargin.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testTrancheMargin.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createTrancheMarginWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = trancheMarginRepository.findAll().size();

        // Create the TrancheMargin with an existing ID
        trancheMargin.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrancheMarginMockMvc.perform(post("/api/tranche-margins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trancheMargin)))
            .andExpect(status().isBadRequest());

        // Validate the TrancheMargin in the database
        List<TrancheMargin> trancheMarginList = trancheMarginRepository.findAll();
        assertThat(trancheMarginList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTrancheMargins() throws Exception {
        // Initialize the database
        trancheMarginRepository.saveAndFlush(trancheMargin);

        // Get all the trancheMarginList
        restTrancheMarginMockMvc.perform(get("/api/tranche-margins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trancheMargin.getId().intValue())))
            .andExpect(jsonPath("$.[*].trancheMarginId").value(hasItem(DEFAULT_TRANCHE_MARGIN_ID)))
            .andExpect(jsonPath("$.[*].marginSpreadBps").value(hasItem(DEFAULT_MARGIN_SPREAD_BPS.toString())))
            .andExpect(jsonPath("$.[*].floorPercentage").value(hasItem(DEFAULT_FLOOR_PERCENTAGE)))
            .andExpect(jsonPath("$.[*].interestPaymentFrequency").value(hasItem(DEFAULT_INTEREST_PAYMENT_FREQUENCY.toString())))
            .andExpect(jsonPath("$.[*].totalPricingFlexBpsPa").value(hasItem(DEFAULT_TOTAL_PRICING_FLEX_BPS_PA.toString())))
            .andExpect(jsonPath("$.[*].underwriteFeesBps").value(hasItem(DEFAULT_UNDERWRITE_FEES_BPS)))
            .andExpect(jsonPath("$.[*].participationFees").value(hasItem(DEFAULT_PARTICIPATION_FEES)))
            .andExpect(jsonPath("$.[*].extensionFees6Months").value(hasItem(DEFAULT_EXTENSION_FEES_6_MONTHS)))
            .andExpect(jsonPath("$.[*].extensionFees6To12Months").value(hasItem(DEFAULT_EXTENSION_FEES_6_TO_12_MONTHS)))
            .andExpect(jsonPath("$.[*].otherFlex").value(hasItem(DEFAULT_OTHER_FLEX.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getTrancheMargin() throws Exception {
        // Initialize the database
        trancheMarginRepository.saveAndFlush(trancheMargin);

        // Get the trancheMargin
        restTrancheMarginMockMvc.perform(get("/api/tranche-margins/{id}", trancheMargin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(trancheMargin.getId().intValue()))
            .andExpect(jsonPath("$.trancheMarginId").value(DEFAULT_TRANCHE_MARGIN_ID))
            .andExpect(jsonPath("$.marginSpreadBps").value(DEFAULT_MARGIN_SPREAD_BPS.toString()))
            .andExpect(jsonPath("$.floorPercentage").value(DEFAULT_FLOOR_PERCENTAGE))
            .andExpect(jsonPath("$.interestPaymentFrequency").value(DEFAULT_INTEREST_PAYMENT_FREQUENCY.toString()))
            .andExpect(jsonPath("$.totalPricingFlexBpsPa").value(DEFAULT_TOTAL_PRICING_FLEX_BPS_PA.toString()))
            .andExpect(jsonPath("$.underwriteFeesBps").value(DEFAULT_UNDERWRITE_FEES_BPS))
            .andExpect(jsonPath("$.participationFees").value(DEFAULT_PARTICIPATION_FEES))
            .andExpect(jsonPath("$.extensionFees6Months").value(DEFAULT_EXTENSION_FEES_6_MONTHS))
            .andExpect(jsonPath("$.extensionFees6To12Months").value(DEFAULT_EXTENSION_FEES_6_TO_12_MONTHS))
            .andExpect(jsonPath("$.otherFlex").value(DEFAULT_OTHER_FLEX.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTrancheMargin() throws Exception {
        // Get the trancheMargin
        restTrancheMarginMockMvc.perform(get("/api/tranche-margins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTrancheMargin() throws Exception {
        // Initialize the database
        trancheMarginRepository.saveAndFlush(trancheMargin);

        int databaseSizeBeforeUpdate = trancheMarginRepository.findAll().size();

        // Update the trancheMargin
        TrancheMargin updatedTrancheMargin = trancheMarginRepository.findById(trancheMargin.getId()).get();
        // Disconnect from session so that the updates on updatedTrancheMargin are not directly saved in db
        em.detach(updatedTrancheMargin);
        updatedTrancheMargin
            .trancheMarginId(UPDATED_TRANCHE_MARGIN_ID)
            .marginSpreadBps(UPDATED_MARGIN_SPREAD_BPS)
            .floorPercentage(UPDATED_FLOOR_PERCENTAGE)
            .interestPaymentFrequency(UPDATED_INTEREST_PAYMENT_FREQUENCY)
            .totalPricingFlexBpsPa(UPDATED_TOTAL_PRICING_FLEX_BPS_PA)
            .underwriteFeesBps(UPDATED_UNDERWRITE_FEES_BPS)
            .participationFees(UPDATED_PARTICIPATION_FEES)
            .extensionFees6Months(UPDATED_EXTENSION_FEES_6_MONTHS)
            .extensionFees6To12Months(UPDATED_EXTENSION_FEES_6_TO_12_MONTHS)
            .otherFlex(UPDATED_OTHER_FLEX)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restTrancheMarginMockMvc.perform(put("/api/tranche-margins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTrancheMargin)))
            .andExpect(status().isOk());

        // Validate the TrancheMargin in the database
        List<TrancheMargin> trancheMarginList = trancheMarginRepository.findAll();
        assertThat(trancheMarginList).hasSize(databaseSizeBeforeUpdate);
        TrancheMargin testTrancheMargin = trancheMarginList.get(trancheMarginList.size() - 1);
        assertThat(testTrancheMargin.getTrancheMarginId()).isEqualTo(UPDATED_TRANCHE_MARGIN_ID);
        assertThat(testTrancheMargin.getMarginSpreadBps()).isEqualTo(UPDATED_MARGIN_SPREAD_BPS);
        assertThat(testTrancheMargin.getFloorPercentage()).isEqualTo(UPDATED_FLOOR_PERCENTAGE);
        assertThat(testTrancheMargin.getInterestPaymentFrequency()).isEqualTo(UPDATED_INTEREST_PAYMENT_FREQUENCY);
        assertThat(testTrancheMargin.getTotalPricingFlexBpsPa()).isEqualTo(UPDATED_TOTAL_PRICING_FLEX_BPS_PA);
        assertThat(testTrancheMargin.getUnderwriteFeesBps()).isEqualTo(UPDATED_UNDERWRITE_FEES_BPS);
        assertThat(testTrancheMargin.getParticipationFees()).isEqualTo(UPDATED_PARTICIPATION_FEES);
        assertThat(testTrancheMargin.getExtensionFees6Months()).isEqualTo(UPDATED_EXTENSION_FEES_6_MONTHS);
        assertThat(testTrancheMargin.getExtensionFees6To12Months()).isEqualTo(UPDATED_EXTENSION_FEES_6_TO_12_MONTHS);
        assertThat(testTrancheMargin.getOtherFlex()).isEqualTo(UPDATED_OTHER_FLEX);
        assertThat(testTrancheMargin.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTrancheMargin.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testTrancheMargin.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testTrancheMargin.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingTrancheMargin() throws Exception {
        int databaseSizeBeforeUpdate = trancheMarginRepository.findAll().size();

        // Create the TrancheMargin

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTrancheMarginMockMvc.perform(put("/api/tranche-margins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trancheMargin)))
            .andExpect(status().isBadRequest());

        // Validate the TrancheMargin in the database
        List<TrancheMargin> trancheMarginList = trancheMarginRepository.findAll();
        assertThat(trancheMarginList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTrancheMargin() throws Exception {
        // Initialize the database
        trancheMarginRepository.saveAndFlush(trancheMargin);

        int databaseSizeBeforeDelete = trancheMarginRepository.findAll().size();

        // Get the trancheMargin
        restTrancheMarginMockMvc.perform(delete("/api/tranche-margins/{id}", trancheMargin.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TrancheMargin> trancheMarginList = trancheMarginRepository.findAll();
        assertThat(trancheMarginList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrancheMargin.class);
        TrancheMargin trancheMargin1 = new TrancheMargin();
        trancheMargin1.setId(1L);
        TrancheMargin trancheMargin2 = new TrancheMargin();
        trancheMargin2.setId(trancheMargin1.getId());
        assertThat(trancheMargin1).isEqualTo(trancheMargin2);
        trancheMargin2.setId(2L);
        assertThat(trancheMargin1).isNotEqualTo(trancheMargin2);
        trancheMargin1.setId(null);
        assertThat(trancheMargin1).isNotEqualTo(trancheMargin2);
    }
}

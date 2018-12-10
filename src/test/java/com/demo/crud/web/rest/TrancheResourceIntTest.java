package com.demo.crud.web.rest;

import com.demo.crud.DealmaintenanceserviceApp;

import com.demo.crud.domain.Tranche;
import com.demo.crud.repository.TrancheRepository;
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
 * Test class for the TrancheResource REST controller.
 *
 * @see TrancheResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DealmaintenanceserviceApp.class)
public class TrancheResourceIntTest {

    private static final Integer DEFAULT_TRANCHE_ID = 1;
    private static final Integer UPDATED_TRANCHE_ID = 2;

    private static final String DEFAULT_TRANCHE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TRANCHE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FUNDED_DURING_SYNDICATION = "AAAAAAAAAA";
    private static final String UPDATED_FUNDED_DURING_SYNDICATION = "BBBBBBBBBB";

    private static final String DEFAULT_DEBT_TAKEOUT = "AAAAAAAAAA";
    private static final String UPDATED_DEBT_TAKEOUT = "BBBBBBBBBB";

    private static final Integer DEFAULT_TENOR_YEARS = 1;
    private static final Integer UPDATED_TENOR_YEARS = 2;

    private static final Integer DEFAULT_TENOR_MONTHS = 1;
    private static final Integer UPDATED_TENOR_MONTHS = 2;

    private static final Instant DEFAULT_BRIDGE_TAKEOUT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BRIDGE_TAKEOUT_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_CREDIT_APPROVED_LCYM_STRUCTURE = 1;
    private static final Integer UPDATED_CREDIT_APPROVED_LCYM_STRUCTURE = 2;

    private static final Integer DEFAULT_CREDIT_APPROVED_LCYM_COMMIT = 1;
    private static final Integer UPDATED_CREDIT_APPROVED_LCYM_COMMIT = 2;

    private static final Integer DEFAULT_CREDIT_APPROVED_LCYM_HOLD = 1;
    private static final Integer UPDATED_CREDIT_APPROVED_LCYM_HOLD = 2;

    private static final Integer DEFAULT_FINAL_APPROVED_LCYM_STRUCTURE = 1;
    private static final Integer UPDATED_FINAL_APPROVED_LCYM_STRUCTURE = 2;

    private static final Integer DEFAULT_FINAL_APPROVED_LCYM_COMMIT = 1;
    private static final Integer UPDATED_FINAL_APPROVED_LCYM_COMMIT = 2;

    private static final Integer DEFAULT_FINAL_APPROVED_LCYM_HOLD = 1;
    private static final Integer UPDATED_FINAL_APPROVED_LCYM_HOLD = 2;

    private static final Integer DEFAULT_MARKET_RISK_LCYM_ECONOMIC = 1;
    private static final Integer UPDATED_MARKET_RISK_LCYM_ECONOMIC = 2;

    private static final Integer DEFAULT_MARKET_RISK_LCYM_LEGAL = 1;
    private static final Integer UPDATED_MARKET_RISK_LCYM_LEGAL = 2;

    private static final Integer DEFAULT_MARKET_RISK_LCYM_SETTLEMENT = 1;
    private static final Integer UPDATED_MARKET_RISK_LCYM_SETTLEMENT = 2;

    private static final Integer DEFAULT_CURRENT_BRIDGE_HOLD_LCYM = 1;
    private static final Integer UPDATED_CURRENT_BRIDGE_HOLD_LCYM = 2;

    private static final Integer DEFAULT_TENOR_HIGH_YIELD_BOND_YEARS = 1;
    private static final Integer UPDATED_TENOR_HIGH_YIELD_BOND_YEARS = 2;

    private static final Integer DEFAULT_TENOR_HIGH_YIELD_BOND_MONTHS = 1;
    private static final Integer UPDATED_TENOR_HIGH_YIELD_BOND_MONTHS = 2;

    private static final Integer DEFAULT_BOND_CAP_RATE = 1;
    private static final Integer UPDATED_BOND_CAP_RATE = 2;

    private static final String DEFAULT_REF_MARGIN_RATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REF_MARGIN_RATE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final Integer DEFAULT_SORT_ORDER = 1;
    private static final Integer UPDATED_SORT_ORDER = 2;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATED_ON = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_ON = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private TrancheRepository trancheRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTrancheMockMvc;

    private Tranche tranche;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TrancheResource trancheResource = new TrancheResource(trancheRepository);
        this.restTrancheMockMvc = MockMvcBuilders.standaloneSetup(trancheResource)
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
    public static Tranche createEntity(EntityManager em) {
        Tranche tranche = new Tranche()
            .trancheId(DEFAULT_TRANCHE_ID)
            .trancheName(DEFAULT_TRANCHE_NAME)
            .fundedDuringSyndication(DEFAULT_FUNDED_DURING_SYNDICATION)
            .debtTakeout(DEFAULT_DEBT_TAKEOUT)
            .tenorYears(DEFAULT_TENOR_YEARS)
            .tenorMonths(DEFAULT_TENOR_MONTHS)
            .bridgeTakeoutDate(DEFAULT_BRIDGE_TAKEOUT_DATE)
            .creditApprovedLcymStructure(DEFAULT_CREDIT_APPROVED_LCYM_STRUCTURE)
            .creditApprovedLcymCommit(DEFAULT_CREDIT_APPROVED_LCYM_COMMIT)
            .creditApprovedLcymHold(DEFAULT_CREDIT_APPROVED_LCYM_HOLD)
            .finalApprovedLcymStructure(DEFAULT_FINAL_APPROVED_LCYM_STRUCTURE)
            .finalApprovedLcymCommit(DEFAULT_FINAL_APPROVED_LCYM_COMMIT)
            .finalApprovedLcymHold(DEFAULT_FINAL_APPROVED_LCYM_HOLD)
            .marketRiskLcymEconomic(DEFAULT_MARKET_RISK_LCYM_ECONOMIC)
            .marketRiskLcymLegal(DEFAULT_MARKET_RISK_LCYM_LEGAL)
            .marketRiskLcymSettlement(DEFAULT_MARKET_RISK_LCYM_SETTLEMENT)
            .currentBridgeHoldLcym(DEFAULT_CURRENT_BRIDGE_HOLD_LCYM)
            .tenorHighYieldBondYears(DEFAULT_TENOR_HIGH_YIELD_BOND_YEARS)
            .tenorHighYieldBondMonths(DEFAULT_TENOR_HIGH_YIELD_BOND_MONTHS)
            .bondCapRate(DEFAULT_BOND_CAP_RATE)
            .refMarginRateName(DEFAULT_REF_MARGIN_RATE_NAME)
            .currency(DEFAULT_CURRENCY)
            .sortOrder(DEFAULT_SORT_ORDER)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return tranche;
    }

    @Before
    public void initTest() {
        tranche = createEntity(em);
    }

    @Test
    @Transactional
    public void createTranche() throws Exception {
        int databaseSizeBeforeCreate = trancheRepository.findAll().size();

        // Create the Tranche
        restTrancheMockMvc.perform(post("/api/tranches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tranche)))
            .andExpect(status().isCreated());

        // Validate the Tranche in the database
        List<Tranche> trancheList = trancheRepository.findAll();
        assertThat(trancheList).hasSize(databaseSizeBeforeCreate + 1);
        Tranche testTranche = trancheList.get(trancheList.size() - 1);
        assertThat(testTranche.getTrancheId()).isEqualTo(DEFAULT_TRANCHE_ID);
        assertThat(testTranche.getTrancheName()).isEqualTo(DEFAULT_TRANCHE_NAME);
        assertThat(testTranche.getFundedDuringSyndication()).isEqualTo(DEFAULT_FUNDED_DURING_SYNDICATION);
        assertThat(testTranche.getDebtTakeout()).isEqualTo(DEFAULT_DEBT_TAKEOUT);
        assertThat(testTranche.getTenorYears()).isEqualTo(DEFAULT_TENOR_YEARS);
        assertThat(testTranche.getTenorMonths()).isEqualTo(DEFAULT_TENOR_MONTHS);
        assertThat(testTranche.getBridgeTakeoutDate()).isEqualTo(DEFAULT_BRIDGE_TAKEOUT_DATE);
        assertThat(testTranche.getCreditApprovedLcymStructure()).isEqualTo(DEFAULT_CREDIT_APPROVED_LCYM_STRUCTURE);
        assertThat(testTranche.getCreditApprovedLcymCommit()).isEqualTo(DEFAULT_CREDIT_APPROVED_LCYM_COMMIT);
        assertThat(testTranche.getCreditApprovedLcymHold()).isEqualTo(DEFAULT_CREDIT_APPROVED_LCYM_HOLD);
        assertThat(testTranche.getFinalApprovedLcymStructure()).isEqualTo(DEFAULT_FINAL_APPROVED_LCYM_STRUCTURE);
        assertThat(testTranche.getFinalApprovedLcymCommit()).isEqualTo(DEFAULT_FINAL_APPROVED_LCYM_COMMIT);
        assertThat(testTranche.getFinalApprovedLcymHold()).isEqualTo(DEFAULT_FINAL_APPROVED_LCYM_HOLD);
        assertThat(testTranche.getMarketRiskLcymEconomic()).isEqualTo(DEFAULT_MARKET_RISK_LCYM_ECONOMIC);
        assertThat(testTranche.getMarketRiskLcymLegal()).isEqualTo(DEFAULT_MARKET_RISK_LCYM_LEGAL);
        assertThat(testTranche.getMarketRiskLcymSettlement()).isEqualTo(DEFAULT_MARKET_RISK_LCYM_SETTLEMENT);
        assertThat(testTranche.getCurrentBridgeHoldLcym()).isEqualTo(DEFAULT_CURRENT_BRIDGE_HOLD_LCYM);
        assertThat(testTranche.getTenorHighYieldBondYears()).isEqualTo(DEFAULT_TENOR_HIGH_YIELD_BOND_YEARS);
        assertThat(testTranche.getTenorHighYieldBondMonths()).isEqualTo(DEFAULT_TENOR_HIGH_YIELD_BOND_MONTHS);
        assertThat(testTranche.getBondCapRate()).isEqualTo(DEFAULT_BOND_CAP_RATE);
        assertThat(testTranche.getRefMarginRateName()).isEqualTo(DEFAULT_REF_MARGIN_RATE_NAME);
        assertThat(testTranche.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testTranche.getSortOrder()).isEqualTo(DEFAULT_SORT_ORDER);
        assertThat(testTranche.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testTranche.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testTranche.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testTranche.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createTrancheWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = trancheRepository.findAll().size();

        // Create the Tranche with an existing ID
        tranche.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrancheMockMvc.perform(post("/api/tranches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tranche)))
            .andExpect(status().isBadRequest());

        // Validate the Tranche in the database
        List<Tranche> trancheList = trancheRepository.findAll();
        assertThat(trancheList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTranches() throws Exception {
        // Initialize the database
        trancheRepository.saveAndFlush(tranche);

        // Get all the trancheList
        restTrancheMockMvc.perform(get("/api/tranches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tranche.getId().intValue())))
            .andExpect(jsonPath("$.[*].trancheId").value(hasItem(DEFAULT_TRANCHE_ID)))
            .andExpect(jsonPath("$.[*].trancheName").value(hasItem(DEFAULT_TRANCHE_NAME.toString())))
            .andExpect(jsonPath("$.[*].fundedDuringSyndication").value(hasItem(DEFAULT_FUNDED_DURING_SYNDICATION.toString())))
            .andExpect(jsonPath("$.[*].debtTakeout").value(hasItem(DEFAULT_DEBT_TAKEOUT.toString())))
            .andExpect(jsonPath("$.[*].tenorYears").value(hasItem(DEFAULT_TENOR_YEARS)))
            .andExpect(jsonPath("$.[*].tenorMonths").value(hasItem(DEFAULT_TENOR_MONTHS)))
            .andExpect(jsonPath("$.[*].bridgeTakeoutDate").value(hasItem(DEFAULT_BRIDGE_TAKEOUT_DATE.toString())))
            .andExpect(jsonPath("$.[*].creditApprovedLcymStructure").value(hasItem(DEFAULT_CREDIT_APPROVED_LCYM_STRUCTURE)))
            .andExpect(jsonPath("$.[*].creditApprovedLcymCommit").value(hasItem(DEFAULT_CREDIT_APPROVED_LCYM_COMMIT)))
            .andExpect(jsonPath("$.[*].creditApprovedLcymHold").value(hasItem(DEFAULT_CREDIT_APPROVED_LCYM_HOLD)))
            .andExpect(jsonPath("$.[*].finalApprovedLcymStructure").value(hasItem(DEFAULT_FINAL_APPROVED_LCYM_STRUCTURE)))
            .andExpect(jsonPath("$.[*].finalApprovedLcymCommit").value(hasItem(DEFAULT_FINAL_APPROVED_LCYM_COMMIT)))
            .andExpect(jsonPath("$.[*].finalApprovedLcymHold").value(hasItem(DEFAULT_FINAL_APPROVED_LCYM_HOLD)))
            .andExpect(jsonPath("$.[*].marketRiskLcymEconomic").value(hasItem(DEFAULT_MARKET_RISK_LCYM_ECONOMIC)))
            .andExpect(jsonPath("$.[*].marketRiskLcymLegal").value(hasItem(DEFAULT_MARKET_RISK_LCYM_LEGAL)))
            .andExpect(jsonPath("$.[*].marketRiskLcymSettlement").value(hasItem(DEFAULT_MARKET_RISK_LCYM_SETTLEMENT)))
            .andExpect(jsonPath("$.[*].currentBridgeHoldLcym").value(hasItem(DEFAULT_CURRENT_BRIDGE_HOLD_LCYM)))
            .andExpect(jsonPath("$.[*].tenorHighYieldBondYears").value(hasItem(DEFAULT_TENOR_HIGH_YIELD_BOND_YEARS)))
            .andExpect(jsonPath("$.[*].tenorHighYieldBondMonths").value(hasItem(DEFAULT_TENOR_HIGH_YIELD_BOND_MONTHS)))
            .andExpect(jsonPath("$.[*].bondCapRate").value(hasItem(DEFAULT_BOND_CAP_RATE)))
            .andExpect(jsonPath("$.[*].refMarginRateName").value(hasItem(DEFAULT_REF_MARGIN_RATE_NAME.toString())))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.toString())))
            .andExpect(jsonPath("$.[*].sortOrder").value(hasItem(DEFAULT_SORT_ORDER)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getTranche() throws Exception {
        // Initialize the database
        trancheRepository.saveAndFlush(tranche);

        // Get the tranche
        restTrancheMockMvc.perform(get("/api/tranches/{id}", tranche.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tranche.getId().intValue()))
            .andExpect(jsonPath("$.trancheId").value(DEFAULT_TRANCHE_ID))
            .andExpect(jsonPath("$.trancheName").value(DEFAULT_TRANCHE_NAME.toString()))
            .andExpect(jsonPath("$.fundedDuringSyndication").value(DEFAULT_FUNDED_DURING_SYNDICATION.toString()))
            .andExpect(jsonPath("$.debtTakeout").value(DEFAULT_DEBT_TAKEOUT.toString()))
            .andExpect(jsonPath("$.tenorYears").value(DEFAULT_TENOR_YEARS))
            .andExpect(jsonPath("$.tenorMonths").value(DEFAULT_TENOR_MONTHS))
            .andExpect(jsonPath("$.bridgeTakeoutDate").value(DEFAULT_BRIDGE_TAKEOUT_DATE.toString()))
            .andExpect(jsonPath("$.creditApprovedLcymStructure").value(DEFAULT_CREDIT_APPROVED_LCYM_STRUCTURE))
            .andExpect(jsonPath("$.creditApprovedLcymCommit").value(DEFAULT_CREDIT_APPROVED_LCYM_COMMIT))
            .andExpect(jsonPath("$.creditApprovedLcymHold").value(DEFAULT_CREDIT_APPROVED_LCYM_HOLD))
            .andExpect(jsonPath("$.finalApprovedLcymStructure").value(DEFAULT_FINAL_APPROVED_LCYM_STRUCTURE))
            .andExpect(jsonPath("$.finalApprovedLcymCommit").value(DEFAULT_FINAL_APPROVED_LCYM_COMMIT))
            .andExpect(jsonPath("$.finalApprovedLcymHold").value(DEFAULT_FINAL_APPROVED_LCYM_HOLD))
            .andExpect(jsonPath("$.marketRiskLcymEconomic").value(DEFAULT_MARKET_RISK_LCYM_ECONOMIC))
            .andExpect(jsonPath("$.marketRiskLcymLegal").value(DEFAULT_MARKET_RISK_LCYM_LEGAL))
            .andExpect(jsonPath("$.marketRiskLcymSettlement").value(DEFAULT_MARKET_RISK_LCYM_SETTLEMENT))
            .andExpect(jsonPath("$.currentBridgeHoldLcym").value(DEFAULT_CURRENT_BRIDGE_HOLD_LCYM))
            .andExpect(jsonPath("$.tenorHighYieldBondYears").value(DEFAULT_TENOR_HIGH_YIELD_BOND_YEARS))
            .andExpect(jsonPath("$.tenorHighYieldBondMonths").value(DEFAULT_TENOR_HIGH_YIELD_BOND_MONTHS))
            .andExpect(jsonPath("$.bondCapRate").value(DEFAULT_BOND_CAP_RATE))
            .andExpect(jsonPath("$.refMarginRateName").value(DEFAULT_REF_MARGIN_RATE_NAME.toString()))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.toString()))
            .andExpect(jsonPath("$.sortOrder").value(DEFAULT_SORT_ORDER))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTranche() throws Exception {
        // Get the tranche
        restTrancheMockMvc.perform(get("/api/tranches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTranche() throws Exception {
        // Initialize the database
        trancheRepository.saveAndFlush(tranche);

        int databaseSizeBeforeUpdate = trancheRepository.findAll().size();

        // Update the tranche
        Tranche updatedTranche = trancheRepository.findById(tranche.getId()).get();
        // Disconnect from session so that the updates on updatedTranche are not directly saved in db
        em.detach(updatedTranche);
        updatedTranche
            .trancheId(UPDATED_TRANCHE_ID)
            .trancheName(UPDATED_TRANCHE_NAME)
            .fundedDuringSyndication(UPDATED_FUNDED_DURING_SYNDICATION)
            .debtTakeout(UPDATED_DEBT_TAKEOUT)
            .tenorYears(UPDATED_TENOR_YEARS)
            .tenorMonths(UPDATED_TENOR_MONTHS)
            .bridgeTakeoutDate(UPDATED_BRIDGE_TAKEOUT_DATE)
            .creditApprovedLcymStructure(UPDATED_CREDIT_APPROVED_LCYM_STRUCTURE)
            .creditApprovedLcymCommit(UPDATED_CREDIT_APPROVED_LCYM_COMMIT)
            .creditApprovedLcymHold(UPDATED_CREDIT_APPROVED_LCYM_HOLD)
            .finalApprovedLcymStructure(UPDATED_FINAL_APPROVED_LCYM_STRUCTURE)
            .finalApprovedLcymCommit(UPDATED_FINAL_APPROVED_LCYM_COMMIT)
            .finalApprovedLcymHold(UPDATED_FINAL_APPROVED_LCYM_HOLD)
            .marketRiskLcymEconomic(UPDATED_MARKET_RISK_LCYM_ECONOMIC)
            .marketRiskLcymLegal(UPDATED_MARKET_RISK_LCYM_LEGAL)
            .marketRiskLcymSettlement(UPDATED_MARKET_RISK_LCYM_SETTLEMENT)
            .currentBridgeHoldLcym(UPDATED_CURRENT_BRIDGE_HOLD_LCYM)
            .tenorHighYieldBondYears(UPDATED_TENOR_HIGH_YIELD_BOND_YEARS)
            .tenorHighYieldBondMonths(UPDATED_TENOR_HIGH_YIELD_BOND_MONTHS)
            .bondCapRate(UPDATED_BOND_CAP_RATE)
            .refMarginRateName(UPDATED_REF_MARGIN_RATE_NAME)
            .currency(UPDATED_CURRENCY)
            .sortOrder(UPDATED_SORT_ORDER)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restTrancheMockMvc.perform(put("/api/tranches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTranche)))
            .andExpect(status().isOk());

        // Validate the Tranche in the database
        List<Tranche> trancheList = trancheRepository.findAll();
        assertThat(trancheList).hasSize(databaseSizeBeforeUpdate);
        Tranche testTranche = trancheList.get(trancheList.size() - 1);
        assertThat(testTranche.getTrancheId()).isEqualTo(UPDATED_TRANCHE_ID);
        assertThat(testTranche.getTrancheName()).isEqualTo(UPDATED_TRANCHE_NAME);
        assertThat(testTranche.getFundedDuringSyndication()).isEqualTo(UPDATED_FUNDED_DURING_SYNDICATION);
        assertThat(testTranche.getDebtTakeout()).isEqualTo(UPDATED_DEBT_TAKEOUT);
        assertThat(testTranche.getTenorYears()).isEqualTo(UPDATED_TENOR_YEARS);
        assertThat(testTranche.getTenorMonths()).isEqualTo(UPDATED_TENOR_MONTHS);
        assertThat(testTranche.getBridgeTakeoutDate()).isEqualTo(UPDATED_BRIDGE_TAKEOUT_DATE);
        assertThat(testTranche.getCreditApprovedLcymStructure()).isEqualTo(UPDATED_CREDIT_APPROVED_LCYM_STRUCTURE);
        assertThat(testTranche.getCreditApprovedLcymCommit()).isEqualTo(UPDATED_CREDIT_APPROVED_LCYM_COMMIT);
        assertThat(testTranche.getCreditApprovedLcymHold()).isEqualTo(UPDATED_CREDIT_APPROVED_LCYM_HOLD);
        assertThat(testTranche.getFinalApprovedLcymStructure()).isEqualTo(UPDATED_FINAL_APPROVED_LCYM_STRUCTURE);
        assertThat(testTranche.getFinalApprovedLcymCommit()).isEqualTo(UPDATED_FINAL_APPROVED_LCYM_COMMIT);
        assertThat(testTranche.getFinalApprovedLcymHold()).isEqualTo(UPDATED_FINAL_APPROVED_LCYM_HOLD);
        assertThat(testTranche.getMarketRiskLcymEconomic()).isEqualTo(UPDATED_MARKET_RISK_LCYM_ECONOMIC);
        assertThat(testTranche.getMarketRiskLcymLegal()).isEqualTo(UPDATED_MARKET_RISK_LCYM_LEGAL);
        assertThat(testTranche.getMarketRiskLcymSettlement()).isEqualTo(UPDATED_MARKET_RISK_LCYM_SETTLEMENT);
        assertThat(testTranche.getCurrentBridgeHoldLcym()).isEqualTo(UPDATED_CURRENT_BRIDGE_HOLD_LCYM);
        assertThat(testTranche.getTenorHighYieldBondYears()).isEqualTo(UPDATED_TENOR_HIGH_YIELD_BOND_YEARS);
        assertThat(testTranche.getTenorHighYieldBondMonths()).isEqualTo(UPDATED_TENOR_HIGH_YIELD_BOND_MONTHS);
        assertThat(testTranche.getBondCapRate()).isEqualTo(UPDATED_BOND_CAP_RATE);
        assertThat(testTranche.getRefMarginRateName()).isEqualTo(UPDATED_REF_MARGIN_RATE_NAME);
        assertThat(testTranche.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testTranche.getSortOrder()).isEqualTo(UPDATED_SORT_ORDER);
        assertThat(testTranche.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTranche.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testTranche.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testTranche.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingTranche() throws Exception {
        int databaseSizeBeforeUpdate = trancheRepository.findAll().size();

        // Create the Tranche

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTrancheMockMvc.perform(put("/api/tranches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tranche)))
            .andExpect(status().isBadRequest());

        // Validate the Tranche in the database
        List<Tranche> trancheList = trancheRepository.findAll();
        assertThat(trancheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTranche() throws Exception {
        // Initialize the database
        trancheRepository.saveAndFlush(tranche);

        int databaseSizeBeforeDelete = trancheRepository.findAll().size();

        // Get the tranche
        restTrancheMockMvc.perform(delete("/api/tranches/{id}", tranche.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tranche> trancheList = trancheRepository.findAll();
        assertThat(trancheList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tranche.class);
        Tranche tranche1 = new Tranche();
        tranche1.setId(1L);
        Tranche tranche2 = new Tranche();
        tranche2.setId(tranche1.getId());
        assertThat(tranche1).isEqualTo(tranche2);
        tranche2.setId(2L);
        assertThat(tranche1).isNotEqualTo(tranche2);
        tranche1.setId(null);
        assertThat(tranche1).isNotEqualTo(tranche2);
    }
}

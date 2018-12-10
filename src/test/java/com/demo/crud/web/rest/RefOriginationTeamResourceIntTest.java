package com.demo.crud.web.rest;

import com.demo.crud.DealmaintenanceserviceApp;

import com.demo.crud.domain.RefOriginationTeam;
import com.demo.crud.repository.RefOriginationTeamRepository;
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
 * Test class for the RefOriginationTeamResource REST controller.
 *
 * @see RefOriginationTeamResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DealmaintenanceserviceApp.class)
public class RefOriginationTeamResourceIntTest {

    private static final Integer DEFAULT_ORIGINATION_TEAM_ID = 1;
    private static final Integer UPDATED_ORIGINATION_TEAM_ID = 2;

    private static final String DEFAULT_ORIGINATION_TEAM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORIGINATION_TEAM_NAME = "BBBBBBBBBB";

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
    private RefOriginationTeamRepository refOriginationTeamRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefOriginationTeamMockMvc;

    private RefOriginationTeam refOriginationTeam;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefOriginationTeamResource refOriginationTeamResource = new RefOriginationTeamResource(refOriginationTeamRepository);
        this.restRefOriginationTeamMockMvc = MockMvcBuilders.standaloneSetup(refOriginationTeamResource)
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
    public static RefOriginationTeam createEntity(EntityManager em) {
        RefOriginationTeam refOriginationTeam = new RefOriginationTeam()
            .originationTeamId(DEFAULT_ORIGINATION_TEAM_ID)
            .originationTeamName(DEFAULT_ORIGINATION_TEAM_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return refOriginationTeam;
    }

    @Before
    public void initTest() {
        refOriginationTeam = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefOriginationTeam() throws Exception {
        int databaseSizeBeforeCreate = refOriginationTeamRepository.findAll().size();

        // Create the RefOriginationTeam
        restRefOriginationTeamMockMvc.perform(post("/api/ref-origination-teams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refOriginationTeam)))
            .andExpect(status().isCreated());

        // Validate the RefOriginationTeam in the database
        List<RefOriginationTeam> refOriginationTeamList = refOriginationTeamRepository.findAll();
        assertThat(refOriginationTeamList).hasSize(databaseSizeBeforeCreate + 1);
        RefOriginationTeam testRefOriginationTeam = refOriginationTeamList.get(refOriginationTeamList.size() - 1);
        assertThat(testRefOriginationTeam.getOriginationTeamId()).isEqualTo(DEFAULT_ORIGINATION_TEAM_ID);
        assertThat(testRefOriginationTeam.getOriginationTeamName()).isEqualTo(DEFAULT_ORIGINATION_TEAM_NAME);
        assertThat(testRefOriginationTeam.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRefOriginationTeam.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRefOriginationTeam.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testRefOriginationTeam.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testRefOriginationTeam.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createRefOriginationTeamWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refOriginationTeamRepository.findAll().size();

        // Create the RefOriginationTeam with an existing ID
        refOriginationTeam.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefOriginationTeamMockMvc.perform(post("/api/ref-origination-teams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refOriginationTeam)))
            .andExpect(status().isBadRequest());

        // Validate the RefOriginationTeam in the database
        List<RefOriginationTeam> refOriginationTeamList = refOriginationTeamRepository.findAll();
        assertThat(refOriginationTeamList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRefOriginationTeams() throws Exception {
        // Initialize the database
        refOriginationTeamRepository.saveAndFlush(refOriginationTeam);

        // Get all the refOriginationTeamList
        restRefOriginationTeamMockMvc.perform(get("/api/ref-origination-teams?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refOriginationTeam.getId().intValue())))
            .andExpect(jsonPath("$.[*].originationTeamId").value(hasItem(DEFAULT_ORIGINATION_TEAM_ID)))
            .andExpect(jsonPath("$.[*].originationTeamName").value(hasItem(DEFAULT_ORIGINATION_TEAM_NAME.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getRefOriginationTeam() throws Exception {
        // Initialize the database
        refOriginationTeamRepository.saveAndFlush(refOriginationTeam);

        // Get the refOriginationTeam
        restRefOriginationTeamMockMvc.perform(get("/api/ref-origination-teams/{id}", refOriginationTeam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refOriginationTeam.getId().intValue()))
            .andExpect(jsonPath("$.originationTeamId").value(DEFAULT_ORIGINATION_TEAM_ID))
            .andExpect(jsonPath("$.originationTeamName").value(DEFAULT_ORIGINATION_TEAM_NAME.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefOriginationTeam() throws Exception {
        // Get the refOriginationTeam
        restRefOriginationTeamMockMvc.perform(get("/api/ref-origination-teams/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefOriginationTeam() throws Exception {
        // Initialize the database
        refOriginationTeamRepository.saveAndFlush(refOriginationTeam);

        int databaseSizeBeforeUpdate = refOriginationTeamRepository.findAll().size();

        // Update the refOriginationTeam
        RefOriginationTeam updatedRefOriginationTeam = refOriginationTeamRepository.findById(refOriginationTeam.getId()).get();
        // Disconnect from session so that the updates on updatedRefOriginationTeam are not directly saved in db
        em.detach(updatedRefOriginationTeam);
        updatedRefOriginationTeam
            .originationTeamId(UPDATED_ORIGINATION_TEAM_ID)
            .originationTeamName(UPDATED_ORIGINATION_TEAM_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restRefOriginationTeamMockMvc.perform(put("/api/ref-origination-teams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefOriginationTeam)))
            .andExpect(status().isOk());

        // Validate the RefOriginationTeam in the database
        List<RefOriginationTeam> refOriginationTeamList = refOriginationTeamRepository.findAll();
        assertThat(refOriginationTeamList).hasSize(databaseSizeBeforeUpdate);
        RefOriginationTeam testRefOriginationTeam = refOriginationTeamList.get(refOriginationTeamList.size() - 1);
        assertThat(testRefOriginationTeam.getOriginationTeamId()).isEqualTo(UPDATED_ORIGINATION_TEAM_ID);
        assertThat(testRefOriginationTeam.getOriginationTeamName()).isEqualTo(UPDATED_ORIGINATION_TEAM_NAME);
        assertThat(testRefOriginationTeam.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRefOriginationTeam.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRefOriginationTeam.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testRefOriginationTeam.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testRefOriginationTeam.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingRefOriginationTeam() throws Exception {
        int databaseSizeBeforeUpdate = refOriginationTeamRepository.findAll().size();

        // Create the RefOriginationTeam

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefOriginationTeamMockMvc.perform(put("/api/ref-origination-teams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refOriginationTeam)))
            .andExpect(status().isBadRequest());

        // Validate the RefOriginationTeam in the database
        List<RefOriginationTeam> refOriginationTeamList = refOriginationTeamRepository.findAll();
        assertThat(refOriginationTeamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefOriginationTeam() throws Exception {
        // Initialize the database
        refOriginationTeamRepository.saveAndFlush(refOriginationTeam);

        int databaseSizeBeforeDelete = refOriginationTeamRepository.findAll().size();

        // Get the refOriginationTeam
        restRefOriginationTeamMockMvc.perform(delete("/api/ref-origination-teams/{id}", refOriginationTeam.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefOriginationTeam> refOriginationTeamList = refOriginationTeamRepository.findAll();
        assertThat(refOriginationTeamList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefOriginationTeam.class);
        RefOriginationTeam refOriginationTeam1 = new RefOriginationTeam();
        refOriginationTeam1.setId(1L);
        RefOriginationTeam refOriginationTeam2 = new RefOriginationTeam();
        refOriginationTeam2.setId(refOriginationTeam1.getId());
        assertThat(refOriginationTeam1).isEqualTo(refOriginationTeam2);
        refOriginationTeam2.setId(2L);
        assertThat(refOriginationTeam1).isNotEqualTo(refOriginationTeam2);
        refOriginationTeam1.setId(null);
        assertThat(refOriginationTeam1).isNotEqualTo(refOriginationTeam2);
    }
}

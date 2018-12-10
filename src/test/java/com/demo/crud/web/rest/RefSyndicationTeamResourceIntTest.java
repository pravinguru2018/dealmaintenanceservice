package com.demo.crud.web.rest;

import com.demo.crud.DealmaintenanceserviceApp;

import com.demo.crud.domain.RefSyndicationTeam;
import com.demo.crud.repository.RefSyndicationTeamRepository;
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
 * Test class for the RefSyndicationTeamResource REST controller.
 *
 * @see RefSyndicationTeamResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DealmaintenanceserviceApp.class)
public class RefSyndicationTeamResourceIntTest {

    private static final Integer DEFAULT_REF_SYNDICATION_TEAM_ID = 1;
    private static final Integer UPDATED_REF_SYNDICATION_TEAM_ID = 2;

    private static final String DEFAULT_REF_SYNDICATION_TEAM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REF_SYNDICATION_TEAM_NAME = "BBBBBBBBBB";

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
    private RefSyndicationTeamRepository refSyndicationTeamRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefSyndicationTeamMockMvc;

    private RefSyndicationTeam refSyndicationTeam;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefSyndicationTeamResource refSyndicationTeamResource = new RefSyndicationTeamResource(refSyndicationTeamRepository);
        this.restRefSyndicationTeamMockMvc = MockMvcBuilders.standaloneSetup(refSyndicationTeamResource)
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
    public static RefSyndicationTeam createEntity(EntityManager em) {
        RefSyndicationTeam refSyndicationTeam = new RefSyndicationTeam()
            .refSyndicationTeamId(DEFAULT_REF_SYNDICATION_TEAM_ID)
            .refSyndicationTeamName(DEFAULT_REF_SYNDICATION_TEAM_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return refSyndicationTeam;
    }

    @Before
    public void initTest() {
        refSyndicationTeam = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefSyndicationTeam() throws Exception {
        int databaseSizeBeforeCreate = refSyndicationTeamRepository.findAll().size();

        // Create the RefSyndicationTeam
        restRefSyndicationTeamMockMvc.perform(post("/api/ref-syndication-teams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refSyndicationTeam)))
            .andExpect(status().isCreated());

        // Validate the RefSyndicationTeam in the database
        List<RefSyndicationTeam> refSyndicationTeamList = refSyndicationTeamRepository.findAll();
        assertThat(refSyndicationTeamList).hasSize(databaseSizeBeforeCreate + 1);
        RefSyndicationTeam testRefSyndicationTeam = refSyndicationTeamList.get(refSyndicationTeamList.size() - 1);
        assertThat(testRefSyndicationTeam.getRefSyndicationTeamId()).isEqualTo(DEFAULT_REF_SYNDICATION_TEAM_ID);
        assertThat(testRefSyndicationTeam.getRefSyndicationTeamName()).isEqualTo(DEFAULT_REF_SYNDICATION_TEAM_NAME);
        assertThat(testRefSyndicationTeam.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRefSyndicationTeam.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRefSyndicationTeam.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testRefSyndicationTeam.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testRefSyndicationTeam.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createRefSyndicationTeamWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refSyndicationTeamRepository.findAll().size();

        // Create the RefSyndicationTeam with an existing ID
        refSyndicationTeam.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefSyndicationTeamMockMvc.perform(post("/api/ref-syndication-teams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refSyndicationTeam)))
            .andExpect(status().isBadRequest());

        // Validate the RefSyndicationTeam in the database
        List<RefSyndicationTeam> refSyndicationTeamList = refSyndicationTeamRepository.findAll();
        assertThat(refSyndicationTeamList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRefSyndicationTeams() throws Exception {
        // Initialize the database
        refSyndicationTeamRepository.saveAndFlush(refSyndicationTeam);

        // Get all the refSyndicationTeamList
        restRefSyndicationTeamMockMvc.perform(get("/api/ref-syndication-teams?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refSyndicationTeam.getId().intValue())))
            .andExpect(jsonPath("$.[*].refSyndicationTeamId").value(hasItem(DEFAULT_REF_SYNDICATION_TEAM_ID)))
            .andExpect(jsonPath("$.[*].refSyndicationTeamName").value(hasItem(DEFAULT_REF_SYNDICATION_TEAM_NAME.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getRefSyndicationTeam() throws Exception {
        // Initialize the database
        refSyndicationTeamRepository.saveAndFlush(refSyndicationTeam);

        // Get the refSyndicationTeam
        restRefSyndicationTeamMockMvc.perform(get("/api/ref-syndication-teams/{id}", refSyndicationTeam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refSyndicationTeam.getId().intValue()))
            .andExpect(jsonPath("$.refSyndicationTeamId").value(DEFAULT_REF_SYNDICATION_TEAM_ID))
            .andExpect(jsonPath("$.refSyndicationTeamName").value(DEFAULT_REF_SYNDICATION_TEAM_NAME.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefSyndicationTeam() throws Exception {
        // Get the refSyndicationTeam
        restRefSyndicationTeamMockMvc.perform(get("/api/ref-syndication-teams/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefSyndicationTeam() throws Exception {
        // Initialize the database
        refSyndicationTeamRepository.saveAndFlush(refSyndicationTeam);

        int databaseSizeBeforeUpdate = refSyndicationTeamRepository.findAll().size();

        // Update the refSyndicationTeam
        RefSyndicationTeam updatedRefSyndicationTeam = refSyndicationTeamRepository.findById(refSyndicationTeam.getId()).get();
        // Disconnect from session so that the updates on updatedRefSyndicationTeam are not directly saved in db
        em.detach(updatedRefSyndicationTeam);
        updatedRefSyndicationTeam
            .refSyndicationTeamId(UPDATED_REF_SYNDICATION_TEAM_ID)
            .refSyndicationTeamName(UPDATED_REF_SYNDICATION_TEAM_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restRefSyndicationTeamMockMvc.perform(put("/api/ref-syndication-teams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefSyndicationTeam)))
            .andExpect(status().isOk());

        // Validate the RefSyndicationTeam in the database
        List<RefSyndicationTeam> refSyndicationTeamList = refSyndicationTeamRepository.findAll();
        assertThat(refSyndicationTeamList).hasSize(databaseSizeBeforeUpdate);
        RefSyndicationTeam testRefSyndicationTeam = refSyndicationTeamList.get(refSyndicationTeamList.size() - 1);
        assertThat(testRefSyndicationTeam.getRefSyndicationTeamId()).isEqualTo(UPDATED_REF_SYNDICATION_TEAM_ID);
        assertThat(testRefSyndicationTeam.getRefSyndicationTeamName()).isEqualTo(UPDATED_REF_SYNDICATION_TEAM_NAME);
        assertThat(testRefSyndicationTeam.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRefSyndicationTeam.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRefSyndicationTeam.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testRefSyndicationTeam.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testRefSyndicationTeam.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingRefSyndicationTeam() throws Exception {
        int databaseSizeBeforeUpdate = refSyndicationTeamRepository.findAll().size();

        // Create the RefSyndicationTeam

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefSyndicationTeamMockMvc.perform(put("/api/ref-syndication-teams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refSyndicationTeam)))
            .andExpect(status().isBadRequest());

        // Validate the RefSyndicationTeam in the database
        List<RefSyndicationTeam> refSyndicationTeamList = refSyndicationTeamRepository.findAll();
        assertThat(refSyndicationTeamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefSyndicationTeam() throws Exception {
        // Initialize the database
        refSyndicationTeamRepository.saveAndFlush(refSyndicationTeam);

        int databaseSizeBeforeDelete = refSyndicationTeamRepository.findAll().size();

        // Get the refSyndicationTeam
        restRefSyndicationTeamMockMvc.perform(delete("/api/ref-syndication-teams/{id}", refSyndicationTeam.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefSyndicationTeam> refSyndicationTeamList = refSyndicationTeamRepository.findAll();
        assertThat(refSyndicationTeamList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefSyndicationTeam.class);
        RefSyndicationTeam refSyndicationTeam1 = new RefSyndicationTeam();
        refSyndicationTeam1.setId(1L);
        RefSyndicationTeam refSyndicationTeam2 = new RefSyndicationTeam();
        refSyndicationTeam2.setId(refSyndicationTeam1.getId());
        assertThat(refSyndicationTeam1).isEqualTo(refSyndicationTeam2);
        refSyndicationTeam2.setId(2L);
        assertThat(refSyndicationTeam1).isNotEqualTo(refSyndicationTeam2);
        refSyndicationTeam1.setId(null);
        assertThat(refSyndicationTeam1).isNotEqualTo(refSyndicationTeam2);
    }
}

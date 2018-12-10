package com.demo.crud.web.rest;

import com.demo.crud.DealmaintenanceserviceApp;

import com.demo.crud.domain.RefRecourseToClient;
import com.demo.crud.repository.RefRecourseToClientRepository;
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
 * Test class for the RefRecourseToClientResource REST controller.
 *
 * @see RefRecourseToClientResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DealmaintenanceserviceApp.class)
public class RefRecourseToClientResourceIntTest {

    private static final Integer DEFAULT_RECOURSE_TO_CLIENT_ID = 1;
    private static final Integer UPDATED_RECOURSE_TO_CLIENT_ID = 2;

    private static final String DEFAULT_RECOURSE_TO_CLIENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RECOURSE_TO_CLIENT_NAME = "BBBBBBBBBB";

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
    private RefRecourseToClientRepository refRecourseToClientRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefRecourseToClientMockMvc;

    private RefRecourseToClient refRecourseToClient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefRecourseToClientResource refRecourseToClientResource = new RefRecourseToClientResource(refRecourseToClientRepository);
        this.restRefRecourseToClientMockMvc = MockMvcBuilders.standaloneSetup(refRecourseToClientResource)
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
    public static RefRecourseToClient createEntity(EntityManager em) {
        RefRecourseToClient refRecourseToClient = new RefRecourseToClient()
            .recourseToClientId(DEFAULT_RECOURSE_TO_CLIENT_ID)
            .recourseToClientName(DEFAULT_RECOURSE_TO_CLIENT_NAME)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return refRecourseToClient;
    }

    @Before
    public void initTest() {
        refRecourseToClient = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefRecourseToClient() throws Exception {
        int databaseSizeBeforeCreate = refRecourseToClientRepository.findAll().size();

        // Create the RefRecourseToClient
        restRefRecourseToClientMockMvc.perform(post("/api/ref-recourse-to-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRecourseToClient)))
            .andExpect(status().isCreated());

        // Validate the RefRecourseToClient in the database
        List<RefRecourseToClient> refRecourseToClientList = refRecourseToClientRepository.findAll();
        assertThat(refRecourseToClientList).hasSize(databaseSizeBeforeCreate + 1);
        RefRecourseToClient testRefRecourseToClient = refRecourseToClientList.get(refRecourseToClientList.size() - 1);
        assertThat(testRefRecourseToClient.getRecourseToClientId()).isEqualTo(DEFAULT_RECOURSE_TO_CLIENT_ID);
        assertThat(testRefRecourseToClient.getRecourseToClientName()).isEqualTo(DEFAULT_RECOURSE_TO_CLIENT_NAME);
        assertThat(testRefRecourseToClient.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRefRecourseToClient.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRefRecourseToClient.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testRefRecourseToClient.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testRefRecourseToClient.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createRefRecourseToClientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refRecourseToClientRepository.findAll().size();

        // Create the RefRecourseToClient with an existing ID
        refRecourseToClient.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefRecourseToClientMockMvc.perform(post("/api/ref-recourse-to-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRecourseToClient)))
            .andExpect(status().isBadRequest());

        // Validate the RefRecourseToClient in the database
        List<RefRecourseToClient> refRecourseToClientList = refRecourseToClientRepository.findAll();
        assertThat(refRecourseToClientList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRefRecourseToClients() throws Exception {
        // Initialize the database
        refRecourseToClientRepository.saveAndFlush(refRecourseToClient);

        // Get all the refRecourseToClientList
        restRefRecourseToClientMockMvc.perform(get("/api/ref-recourse-to-clients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refRecourseToClient.getId().intValue())))
            .andExpect(jsonPath("$.[*].recourseToClientId").value(hasItem(DEFAULT_RECOURSE_TO_CLIENT_ID)))
            .andExpect(jsonPath("$.[*].recourseToClientName").value(hasItem(DEFAULT_RECOURSE_TO_CLIENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getRefRecourseToClient() throws Exception {
        // Initialize the database
        refRecourseToClientRepository.saveAndFlush(refRecourseToClient);

        // Get the refRecourseToClient
        restRefRecourseToClientMockMvc.perform(get("/api/ref-recourse-to-clients/{id}", refRecourseToClient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refRecourseToClient.getId().intValue()))
            .andExpect(jsonPath("$.recourseToClientId").value(DEFAULT_RECOURSE_TO_CLIENT_ID))
            .andExpect(jsonPath("$.recourseToClientName").value(DEFAULT_RECOURSE_TO_CLIENT_NAME.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefRecourseToClient() throws Exception {
        // Get the refRecourseToClient
        restRefRecourseToClientMockMvc.perform(get("/api/ref-recourse-to-clients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefRecourseToClient() throws Exception {
        // Initialize the database
        refRecourseToClientRepository.saveAndFlush(refRecourseToClient);

        int databaseSizeBeforeUpdate = refRecourseToClientRepository.findAll().size();

        // Update the refRecourseToClient
        RefRecourseToClient updatedRefRecourseToClient = refRecourseToClientRepository.findById(refRecourseToClient.getId()).get();
        // Disconnect from session so that the updates on updatedRefRecourseToClient are not directly saved in db
        em.detach(updatedRefRecourseToClient);
        updatedRefRecourseToClient
            .recourseToClientId(UPDATED_RECOURSE_TO_CLIENT_ID)
            .recourseToClientName(UPDATED_RECOURSE_TO_CLIENT_NAME)
            .isActive(UPDATED_IS_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);

        restRefRecourseToClientMockMvc.perform(put("/api/ref-recourse-to-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefRecourseToClient)))
            .andExpect(status().isOk());

        // Validate the RefRecourseToClient in the database
        List<RefRecourseToClient> refRecourseToClientList = refRecourseToClientRepository.findAll();
        assertThat(refRecourseToClientList).hasSize(databaseSizeBeforeUpdate);
        RefRecourseToClient testRefRecourseToClient = refRecourseToClientList.get(refRecourseToClientList.size() - 1);
        assertThat(testRefRecourseToClient.getRecourseToClientId()).isEqualTo(UPDATED_RECOURSE_TO_CLIENT_ID);
        assertThat(testRefRecourseToClient.getRecourseToClientName()).isEqualTo(UPDATED_RECOURSE_TO_CLIENT_NAME);
        assertThat(testRefRecourseToClient.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRefRecourseToClient.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRefRecourseToClient.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testRefRecourseToClient.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testRefRecourseToClient.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingRefRecourseToClient() throws Exception {
        int databaseSizeBeforeUpdate = refRecourseToClientRepository.findAll().size();

        // Create the RefRecourseToClient

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefRecourseToClientMockMvc.perform(put("/api/ref-recourse-to-clients")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRecourseToClient)))
            .andExpect(status().isBadRequest());

        // Validate the RefRecourseToClient in the database
        List<RefRecourseToClient> refRecourseToClientList = refRecourseToClientRepository.findAll();
        assertThat(refRecourseToClientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefRecourseToClient() throws Exception {
        // Initialize the database
        refRecourseToClientRepository.saveAndFlush(refRecourseToClient);

        int databaseSizeBeforeDelete = refRecourseToClientRepository.findAll().size();

        // Get the refRecourseToClient
        restRefRecourseToClientMockMvc.perform(delete("/api/ref-recourse-to-clients/{id}", refRecourseToClient.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RefRecourseToClient> refRecourseToClientList = refRecourseToClientRepository.findAll();
        assertThat(refRecourseToClientList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefRecourseToClient.class);
        RefRecourseToClient refRecourseToClient1 = new RefRecourseToClient();
        refRecourseToClient1.setId(1L);
        RefRecourseToClient refRecourseToClient2 = new RefRecourseToClient();
        refRecourseToClient2.setId(refRecourseToClient1.getId());
        assertThat(refRecourseToClient1).isEqualTo(refRecourseToClient2);
        refRecourseToClient2.setId(2L);
        assertThat(refRecourseToClient1).isNotEqualTo(refRecourseToClient2);
        refRecourseToClient1.setId(null);
        assertThat(refRecourseToClient1).isNotEqualTo(refRecourseToClient2);
    }
}

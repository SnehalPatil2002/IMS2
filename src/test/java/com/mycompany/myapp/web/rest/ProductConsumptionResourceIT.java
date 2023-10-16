package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ProductConsumption;
import com.mycompany.myapp.repository.ProductConsumptionRepository;
import com.mycompany.myapp.service.criteria.ProductConsumptionCriteria;
import com.mycompany.myapp.service.dto.ProductConsumptionDTO;
import com.mycompany.myapp.service.mapper.ProductConsumptionMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ProductConsumptionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProductConsumptionResourceIT {

    private static final Double DEFAULT_QUANTITY_CONSUMED = 1D;
    private static final Double UPDATED_QUANTITY_CONSUMED = 2D;
    private static final Double SMALLER_QUANTITY_CONSUMED = 1D - 1D;

    private static final Double DEFAULT_TOTAL_PRODUCTS_COST = 1D;
    private static final Double UPDATED_TOTAL_PRODUCTS_COST = 2D;
    private static final Double SMALLER_TOTAL_PRODUCTS_COST = 1D - 1D;

    private static final String ENTITY_API_URL = "/api/product-consumptions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProductConsumptionRepository productConsumptionRepository;

    @Autowired
    private ProductConsumptionMapper productConsumptionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductConsumptionMockMvc;

    private ProductConsumption productConsumption;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductConsumption createEntity(EntityManager em) {
        ProductConsumption productConsumption = new ProductConsumption()
            .quantityConsumed(DEFAULT_QUANTITY_CONSUMED)
            .totalProductsCost(DEFAULT_TOTAL_PRODUCTS_COST);
        return productConsumption;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductConsumption createUpdatedEntity(EntityManager em) {
        ProductConsumption productConsumption = new ProductConsumption()
            .quantityConsumed(UPDATED_QUANTITY_CONSUMED)
            .totalProductsCost(UPDATED_TOTAL_PRODUCTS_COST);
        return productConsumption;
    }

    @BeforeEach
    public void initTest() {
        productConsumption = createEntity(em);
    }

    @Test
    @Transactional
    void createProductConsumption() throws Exception {
        int databaseSizeBeforeCreate = productConsumptionRepository.findAll().size();
        // Create the ProductConsumption
        ProductConsumptionDTO productConsumptionDTO = productConsumptionMapper.toDto(productConsumption);
        restProductConsumptionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productConsumptionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ProductConsumption in the database
        List<ProductConsumption> productConsumptionList = productConsumptionRepository.findAll();
        assertThat(productConsumptionList).hasSize(databaseSizeBeforeCreate + 1);
        ProductConsumption testProductConsumption = productConsumptionList.get(productConsumptionList.size() - 1);
        assertThat(testProductConsumption.getQuantityConsumed()).isEqualTo(DEFAULT_QUANTITY_CONSUMED);
        assertThat(testProductConsumption.getTotalProductsCost()).isEqualTo(DEFAULT_TOTAL_PRODUCTS_COST);
    }

    @Test
    @Transactional
    void createProductConsumptionWithExistingId() throws Exception {
        // Create the ProductConsumption with an existing ID
        productConsumption.setId(1L);
        ProductConsumptionDTO productConsumptionDTO = productConsumptionMapper.toDto(productConsumption);

        int databaseSizeBeforeCreate = productConsumptionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductConsumptionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productConsumptionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductConsumption in the database
        List<ProductConsumption> productConsumptionList = productConsumptionRepository.findAll();
        assertThat(productConsumptionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProductConsumptions() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        // Get all the productConsumptionList
        restProductConsumptionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productConsumption.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantityConsumed").value(hasItem(DEFAULT_QUANTITY_CONSUMED.doubleValue())))
            .andExpect(jsonPath("$.[*].totalProductsCost").value(hasItem(DEFAULT_TOTAL_PRODUCTS_COST.doubleValue())));
    }

    @Test
    @Transactional
    void getProductConsumption() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        // Get the productConsumption
        restProductConsumptionMockMvc
            .perform(get(ENTITY_API_URL_ID, productConsumption.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productConsumption.getId().intValue()))
            .andExpect(jsonPath("$.quantityConsumed").value(DEFAULT_QUANTITY_CONSUMED.doubleValue()))
            .andExpect(jsonPath("$.totalProductsCost").value(DEFAULT_TOTAL_PRODUCTS_COST.doubleValue()));
    }

    @Test
    @Transactional
    void getProductConsumptionsByIdFiltering() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        Long id = productConsumption.getId();

        defaultProductConsumptionShouldBeFound("id.equals=" + id);
        defaultProductConsumptionShouldNotBeFound("id.notEquals=" + id);

        defaultProductConsumptionShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultProductConsumptionShouldNotBeFound("id.greaterThan=" + id);

        defaultProductConsumptionShouldBeFound("id.lessThanOrEqual=" + id);
        defaultProductConsumptionShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllProductConsumptionsByQuantityConsumedIsEqualToSomething() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        // Get all the productConsumptionList where quantityConsumed equals to DEFAULT_QUANTITY_CONSUMED
        defaultProductConsumptionShouldBeFound("quantityConsumed.equals=" + DEFAULT_QUANTITY_CONSUMED);

        // Get all the productConsumptionList where quantityConsumed equals to UPDATED_QUANTITY_CONSUMED
        defaultProductConsumptionShouldNotBeFound("quantityConsumed.equals=" + UPDATED_QUANTITY_CONSUMED);
    }

    @Test
    @Transactional
    void getAllProductConsumptionsByQuantityConsumedIsInShouldWork() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        // Get all the productConsumptionList where quantityConsumed in DEFAULT_QUANTITY_CONSUMED or UPDATED_QUANTITY_CONSUMED
        defaultProductConsumptionShouldBeFound("quantityConsumed.in=" + DEFAULT_QUANTITY_CONSUMED + "," + UPDATED_QUANTITY_CONSUMED);

        // Get all the productConsumptionList where quantityConsumed equals to UPDATED_QUANTITY_CONSUMED
        defaultProductConsumptionShouldNotBeFound("quantityConsumed.in=" + UPDATED_QUANTITY_CONSUMED);
    }

    @Test
    @Transactional
    void getAllProductConsumptionsByQuantityConsumedIsNullOrNotNull() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        // Get all the productConsumptionList where quantityConsumed is not null
        defaultProductConsumptionShouldBeFound("quantityConsumed.specified=true");

        // Get all the productConsumptionList where quantityConsumed is null
        defaultProductConsumptionShouldNotBeFound("quantityConsumed.specified=false");
    }

    @Test
    @Transactional
    void getAllProductConsumptionsByQuantityConsumedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        // Get all the productConsumptionList where quantityConsumed is greater than or equal to DEFAULT_QUANTITY_CONSUMED
        defaultProductConsumptionShouldBeFound("quantityConsumed.greaterThanOrEqual=" + DEFAULT_QUANTITY_CONSUMED);

        // Get all the productConsumptionList where quantityConsumed is greater than or equal to UPDATED_QUANTITY_CONSUMED
        defaultProductConsumptionShouldNotBeFound("quantityConsumed.greaterThanOrEqual=" + UPDATED_QUANTITY_CONSUMED);
    }

    @Test
    @Transactional
    void getAllProductConsumptionsByQuantityConsumedIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        // Get all the productConsumptionList where quantityConsumed is less than or equal to DEFAULT_QUANTITY_CONSUMED
        defaultProductConsumptionShouldBeFound("quantityConsumed.lessThanOrEqual=" + DEFAULT_QUANTITY_CONSUMED);

        // Get all the productConsumptionList where quantityConsumed is less than or equal to SMALLER_QUANTITY_CONSUMED
        defaultProductConsumptionShouldNotBeFound("quantityConsumed.lessThanOrEqual=" + SMALLER_QUANTITY_CONSUMED);
    }

    @Test
    @Transactional
    void getAllProductConsumptionsByQuantityConsumedIsLessThanSomething() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        // Get all the productConsumptionList where quantityConsumed is less than DEFAULT_QUANTITY_CONSUMED
        defaultProductConsumptionShouldNotBeFound("quantityConsumed.lessThan=" + DEFAULT_QUANTITY_CONSUMED);

        // Get all the productConsumptionList where quantityConsumed is less than UPDATED_QUANTITY_CONSUMED
        defaultProductConsumptionShouldBeFound("quantityConsumed.lessThan=" + UPDATED_QUANTITY_CONSUMED);
    }

    @Test
    @Transactional
    void getAllProductConsumptionsByQuantityConsumedIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        // Get all the productConsumptionList where quantityConsumed is greater than DEFAULT_QUANTITY_CONSUMED
        defaultProductConsumptionShouldNotBeFound("quantityConsumed.greaterThan=" + DEFAULT_QUANTITY_CONSUMED);

        // Get all the productConsumptionList where quantityConsumed is greater than SMALLER_QUANTITY_CONSUMED
        defaultProductConsumptionShouldBeFound("quantityConsumed.greaterThan=" + SMALLER_QUANTITY_CONSUMED);
    }

    @Test
    @Transactional
    void getAllProductConsumptionsByTotalProductsCostIsEqualToSomething() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        // Get all the productConsumptionList where totalProductsCost equals to DEFAULT_TOTAL_PRODUCTS_COST
        defaultProductConsumptionShouldBeFound("totalProductsCost.equals=" + DEFAULT_TOTAL_PRODUCTS_COST);

        // Get all the productConsumptionList where totalProductsCost equals to UPDATED_TOTAL_PRODUCTS_COST
        defaultProductConsumptionShouldNotBeFound("totalProductsCost.equals=" + UPDATED_TOTAL_PRODUCTS_COST);
    }

    @Test
    @Transactional
    void getAllProductConsumptionsByTotalProductsCostIsInShouldWork() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        // Get all the productConsumptionList where totalProductsCost in DEFAULT_TOTAL_PRODUCTS_COST or UPDATED_TOTAL_PRODUCTS_COST
        defaultProductConsumptionShouldBeFound("totalProductsCost.in=" + DEFAULT_TOTAL_PRODUCTS_COST + "," + UPDATED_TOTAL_PRODUCTS_COST);

        // Get all the productConsumptionList where totalProductsCost equals to UPDATED_TOTAL_PRODUCTS_COST
        defaultProductConsumptionShouldNotBeFound("totalProductsCost.in=" + UPDATED_TOTAL_PRODUCTS_COST);
    }

    @Test
    @Transactional
    void getAllProductConsumptionsByTotalProductsCostIsNullOrNotNull() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        // Get all the productConsumptionList where totalProductsCost is not null
        defaultProductConsumptionShouldBeFound("totalProductsCost.specified=true");

        // Get all the productConsumptionList where totalProductsCost is null
        defaultProductConsumptionShouldNotBeFound("totalProductsCost.specified=false");
    }

    @Test
    @Transactional
    void getAllProductConsumptionsByTotalProductsCostIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        // Get all the productConsumptionList where totalProductsCost is greater than or equal to DEFAULT_TOTAL_PRODUCTS_COST
        defaultProductConsumptionShouldBeFound("totalProductsCost.greaterThanOrEqual=" + DEFAULT_TOTAL_PRODUCTS_COST);

        // Get all the productConsumptionList where totalProductsCost is greater than or equal to UPDATED_TOTAL_PRODUCTS_COST
        defaultProductConsumptionShouldNotBeFound("totalProductsCost.greaterThanOrEqual=" + UPDATED_TOTAL_PRODUCTS_COST);
    }

    @Test
    @Transactional
    void getAllProductConsumptionsByTotalProductsCostIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        // Get all the productConsumptionList where totalProductsCost is less than or equal to DEFAULT_TOTAL_PRODUCTS_COST
        defaultProductConsumptionShouldBeFound("totalProductsCost.lessThanOrEqual=" + DEFAULT_TOTAL_PRODUCTS_COST);

        // Get all the productConsumptionList where totalProductsCost is less than or equal to SMALLER_TOTAL_PRODUCTS_COST
        defaultProductConsumptionShouldNotBeFound("totalProductsCost.lessThanOrEqual=" + SMALLER_TOTAL_PRODUCTS_COST);
    }

    @Test
    @Transactional
    void getAllProductConsumptionsByTotalProductsCostIsLessThanSomething() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        // Get all the productConsumptionList where totalProductsCost is less than DEFAULT_TOTAL_PRODUCTS_COST
        defaultProductConsumptionShouldNotBeFound("totalProductsCost.lessThan=" + DEFAULT_TOTAL_PRODUCTS_COST);

        // Get all the productConsumptionList where totalProductsCost is less than UPDATED_TOTAL_PRODUCTS_COST
        defaultProductConsumptionShouldBeFound("totalProductsCost.lessThan=" + UPDATED_TOTAL_PRODUCTS_COST);
    }

    @Test
    @Transactional
    void getAllProductConsumptionsByTotalProductsCostIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        // Get all the productConsumptionList where totalProductsCost is greater than DEFAULT_TOTAL_PRODUCTS_COST
        defaultProductConsumptionShouldNotBeFound("totalProductsCost.greaterThan=" + DEFAULT_TOTAL_PRODUCTS_COST);

        // Get all the productConsumptionList where totalProductsCost is greater than SMALLER_TOTAL_PRODUCTS_COST
        defaultProductConsumptionShouldBeFound("totalProductsCost.greaterThan=" + SMALLER_TOTAL_PRODUCTS_COST);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultProductConsumptionShouldBeFound(String filter) throws Exception {
        restProductConsumptionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productConsumption.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantityConsumed").value(hasItem(DEFAULT_QUANTITY_CONSUMED.doubleValue())))
            .andExpect(jsonPath("$.[*].totalProductsCost").value(hasItem(DEFAULT_TOTAL_PRODUCTS_COST.doubleValue())));

        // Check, that the count call also returns 1
        restProductConsumptionMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultProductConsumptionShouldNotBeFound(String filter) throws Exception {
        restProductConsumptionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restProductConsumptionMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingProductConsumption() throws Exception {
        // Get the productConsumption
        restProductConsumptionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProductConsumption() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        int databaseSizeBeforeUpdate = productConsumptionRepository.findAll().size();

        // Update the productConsumption
        ProductConsumption updatedProductConsumption = productConsumptionRepository.findById(productConsumption.getId()).get();
        // Disconnect from session so that the updates on updatedProductConsumption are not directly saved in db
        em.detach(updatedProductConsumption);
        updatedProductConsumption.quantityConsumed(UPDATED_QUANTITY_CONSUMED).totalProductsCost(UPDATED_TOTAL_PRODUCTS_COST);
        ProductConsumptionDTO productConsumptionDTO = productConsumptionMapper.toDto(updatedProductConsumption);

        restProductConsumptionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, productConsumptionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productConsumptionDTO))
            )
            .andExpect(status().isOk());

        // Validate the ProductConsumption in the database
        List<ProductConsumption> productConsumptionList = productConsumptionRepository.findAll();
        assertThat(productConsumptionList).hasSize(databaseSizeBeforeUpdate);
        ProductConsumption testProductConsumption = productConsumptionList.get(productConsumptionList.size() - 1);
        assertThat(testProductConsumption.getQuantityConsumed()).isEqualTo(UPDATED_QUANTITY_CONSUMED);
        assertThat(testProductConsumption.getTotalProductsCost()).isEqualTo(UPDATED_TOTAL_PRODUCTS_COST);
    }

    @Test
    @Transactional
    void putNonExistingProductConsumption() throws Exception {
        int databaseSizeBeforeUpdate = productConsumptionRepository.findAll().size();
        productConsumption.setId(count.incrementAndGet());

        // Create the ProductConsumption
        ProductConsumptionDTO productConsumptionDTO = productConsumptionMapper.toDto(productConsumption);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductConsumptionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, productConsumptionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productConsumptionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductConsumption in the database
        List<ProductConsumption> productConsumptionList = productConsumptionRepository.findAll();
        assertThat(productConsumptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProductConsumption() throws Exception {
        int databaseSizeBeforeUpdate = productConsumptionRepository.findAll().size();
        productConsumption.setId(count.incrementAndGet());

        // Create the ProductConsumption
        ProductConsumptionDTO productConsumptionDTO = productConsumptionMapper.toDto(productConsumption);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductConsumptionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productConsumptionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductConsumption in the database
        List<ProductConsumption> productConsumptionList = productConsumptionRepository.findAll();
        assertThat(productConsumptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProductConsumption() throws Exception {
        int databaseSizeBeforeUpdate = productConsumptionRepository.findAll().size();
        productConsumption.setId(count.incrementAndGet());

        // Create the ProductConsumption
        ProductConsumptionDTO productConsumptionDTO = productConsumptionMapper.toDto(productConsumption);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductConsumptionMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productConsumptionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProductConsumption in the database
        List<ProductConsumption> productConsumptionList = productConsumptionRepository.findAll();
        assertThat(productConsumptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProductConsumptionWithPatch() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        int databaseSizeBeforeUpdate = productConsumptionRepository.findAll().size();

        // Update the productConsumption using partial update
        ProductConsumption partialUpdatedProductConsumption = new ProductConsumption();
        partialUpdatedProductConsumption.setId(productConsumption.getId());

        partialUpdatedProductConsumption.quantityConsumed(UPDATED_QUANTITY_CONSUMED).totalProductsCost(UPDATED_TOTAL_PRODUCTS_COST);

        restProductConsumptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProductConsumption.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProductConsumption))
            )
            .andExpect(status().isOk());

        // Validate the ProductConsumption in the database
        List<ProductConsumption> productConsumptionList = productConsumptionRepository.findAll();
        assertThat(productConsumptionList).hasSize(databaseSizeBeforeUpdate);
        ProductConsumption testProductConsumption = productConsumptionList.get(productConsumptionList.size() - 1);
        assertThat(testProductConsumption.getQuantityConsumed()).isEqualTo(UPDATED_QUANTITY_CONSUMED);
        assertThat(testProductConsumption.getTotalProductsCost()).isEqualTo(UPDATED_TOTAL_PRODUCTS_COST);
    }

    @Test
    @Transactional
    void fullUpdateProductConsumptionWithPatch() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        int databaseSizeBeforeUpdate = productConsumptionRepository.findAll().size();

        // Update the productConsumption using partial update
        ProductConsumption partialUpdatedProductConsumption = new ProductConsumption();
        partialUpdatedProductConsumption.setId(productConsumption.getId());

        partialUpdatedProductConsumption.quantityConsumed(UPDATED_QUANTITY_CONSUMED).totalProductsCost(UPDATED_TOTAL_PRODUCTS_COST);

        restProductConsumptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProductConsumption.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProductConsumption))
            )
            .andExpect(status().isOk());

        // Validate the ProductConsumption in the database
        List<ProductConsumption> productConsumptionList = productConsumptionRepository.findAll();
        assertThat(productConsumptionList).hasSize(databaseSizeBeforeUpdate);
        ProductConsumption testProductConsumption = productConsumptionList.get(productConsumptionList.size() - 1);
        assertThat(testProductConsumption.getQuantityConsumed()).isEqualTo(UPDATED_QUANTITY_CONSUMED);
        assertThat(testProductConsumption.getTotalProductsCost()).isEqualTo(UPDATED_TOTAL_PRODUCTS_COST);
    }

    @Test
    @Transactional
    void patchNonExistingProductConsumption() throws Exception {
        int databaseSizeBeforeUpdate = productConsumptionRepository.findAll().size();
        productConsumption.setId(count.incrementAndGet());

        // Create the ProductConsumption
        ProductConsumptionDTO productConsumptionDTO = productConsumptionMapper.toDto(productConsumption);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductConsumptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, productConsumptionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productConsumptionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductConsumption in the database
        List<ProductConsumption> productConsumptionList = productConsumptionRepository.findAll();
        assertThat(productConsumptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProductConsumption() throws Exception {
        int databaseSizeBeforeUpdate = productConsumptionRepository.findAll().size();
        productConsumption.setId(count.incrementAndGet());

        // Create the ProductConsumption
        ProductConsumptionDTO productConsumptionDTO = productConsumptionMapper.toDto(productConsumption);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductConsumptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productConsumptionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductConsumption in the database
        List<ProductConsumption> productConsumptionList = productConsumptionRepository.findAll();
        assertThat(productConsumptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProductConsumption() throws Exception {
        int databaseSizeBeforeUpdate = productConsumptionRepository.findAll().size();
        productConsumption.setId(count.incrementAndGet());

        // Create the ProductConsumption
        ProductConsumptionDTO productConsumptionDTO = productConsumptionMapper.toDto(productConsumption);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductConsumptionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productConsumptionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProductConsumption in the database
        List<ProductConsumption> productConsumptionList = productConsumptionRepository.findAll();
        assertThat(productConsumptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProductConsumption() throws Exception {
        // Initialize the database
        productConsumptionRepository.saveAndFlush(productConsumption);

        int databaseSizeBeforeDelete = productConsumptionRepository.findAll().size();

        // Delete the productConsumption
        restProductConsumptionMockMvc
            .perform(delete(ENTITY_API_URL_ID, productConsumption.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductConsumption> productConsumptionList = productConsumptionRepository.findAll();
        assertThat(productConsumptionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

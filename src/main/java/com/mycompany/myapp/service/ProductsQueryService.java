package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.Products;
import com.mycompany.myapp.repository.ProductsRepository;
import com.mycompany.myapp.service.criteria.ProductsCriteria;
import com.mycompany.myapp.service.dto.ProductsDTO;
import com.mycompany.myapp.service.mapper.ProductsMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Products} entities in the database.
 * The main input is a {@link ProductsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProductsDTO} or a {@link Page} of {@link ProductsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProductsQueryService extends QueryService<Products> {

    private final Logger log = LoggerFactory.getLogger(ProductsQueryService.class);

    private final ProductsRepository productsRepository;

    private final ProductsMapper productsMapper;

    public ProductsQueryService(ProductsRepository productsRepository, ProductsMapper productsMapper) {
        this.productsRepository = productsRepository;
        this.productsMapper = productsMapper;
    }

    /**
     * Return a {@link List} of {@link ProductsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProductsDTO> findByCriteria(ProductsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Products> specification = createSpecification(criteria);
        return productsMapper.toDto(productsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProductsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProductsDTO> findByCriteria(ProductsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Products> specification = createSpecification(criteria);
        return productsRepository.findAll(specification, page).map(productsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProductsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Products> specification = createSpecification(criteria);
        return productsRepository.count(specification);
    }

    /**
     * Function to convert {@link ProductsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Products> createSpecification(ProductsCriteria criteria) {
        Specification<Products> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Products_.id));
            }
            if (criteria.getProductName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProductName(), Products_.productName));
            }
            if (criteria.getAvailableQty() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAvailableQty(), Products_.availableQty));
            }
            if (criteria.getManufacturingCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getManufacturingCost(), Products_.manufacturingCost));
            }
            if (criteria.getLabourCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLabourCost(), Products_.labourCost));
            }
            if (criteria.getRawMaterialId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRawMaterialId(),
                            root -> root.join(Products_.rawMaterials, JoinType.LEFT).get(RawMaterial_.id)
                        )
                    );
            }
        }
        return specification;
    }
}

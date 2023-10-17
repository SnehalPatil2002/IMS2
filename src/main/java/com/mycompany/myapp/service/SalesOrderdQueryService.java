package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.SalesOrderdRepository;
import com.mycompany.myapp.service.criteria.SalesOrderdCriteria;
import com.mycompany.myapp.service.dto.SalesOrderdDTO;
import com.mycompany.myapp.service.mapper.SalesOrderdMapper;
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
 * Service for executing complex queries for {@link SalesOrderd} entities in the database.
 * The main input is a {@link SalesOrderdCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SalesOrderdDTO} or a {@link Page} of {@link SalesOrderdDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SalesOrderdQueryService extends QueryService<SalesOrderd> {

    private final Logger log = LoggerFactory.getLogger(SalesOrderdQueryService.class);

    private final SalesOrderdRepository salesOrderdRepository;

    private final SalesOrderdMapper salesOrderdMapper;

    public SalesOrderdQueryService(SalesOrderdRepository salesOrderdRepository, SalesOrderdMapper salesOrderdMapper) {
        this.salesOrderdRepository = salesOrderdRepository;
        this.salesOrderdMapper = salesOrderdMapper;
    }

    /**
     * Return a {@link List} of {@link SalesOrderdDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SalesOrderdDTO> findByCriteria(SalesOrderdCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SalesOrderd> specification = createSpecification(criteria);
        return salesOrderdMapper.toDto(salesOrderdRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SalesOrderdDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SalesOrderdDTO> findByCriteria(SalesOrderdCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SalesOrderd> specification = createSpecification(criteria);
        return salesOrderdRepository.findAll(specification, page).map(salesOrderdMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SalesOrderdCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SalesOrderd> specification = createSpecification(criteria);
        return salesOrderdRepository.count(specification);
    }

    /**
     * Function to convert {@link SalesOrderdCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<SalesOrderd> createSpecification(SalesOrderdCriteria criteria) {
        Specification<SalesOrderd> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), SalesOrderd_.id));
            }
            if (criteria.getOrderDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderDate(), SalesOrderd_.orderDate));
            }
            if (criteria.getQuantitySold() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantitySold(), SalesOrderd_.quantitySold));
            }
            if (criteria.getUnitPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUnitPrice(), SalesOrderd_.unitPrice));
            }
            if (criteria.getGstPercentage() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGstPercentage(), SalesOrderd_.gstPercentage));
            }
            if (criteria.getTotalRevenue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalRevenue(), SalesOrderd_.totalRevenue));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), SalesOrderd_.status));
            }
            
            if (criteria.getClientsId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getClientsId(), root -> root.join(SalesOrderd_.clients, JoinType.LEFT).get(Clients_.id))
                    );
            }
           
        }
        return specification;
    }
}

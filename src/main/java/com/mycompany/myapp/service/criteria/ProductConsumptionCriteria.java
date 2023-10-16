package com.mycompany.myapp.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.ProductConsumption} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.ProductConsumptionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /product-consumptions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductConsumptionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private DoubleFilter quantityConsumed;

    private DoubleFilter totalProductsCost;

    private Boolean distinct;

    public ProductConsumptionCriteria() {}

    public ProductConsumptionCriteria(ProductConsumptionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.quantityConsumed = other.quantityConsumed == null ? null : other.quantityConsumed.copy();
        this.totalProductsCost = other.totalProductsCost == null ? null : other.totalProductsCost.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ProductConsumptionCriteria copy() {
        return new ProductConsumptionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public DoubleFilter getQuantityConsumed() {
        return quantityConsumed;
    }

    public DoubleFilter quantityConsumed() {
        if (quantityConsumed == null) {
            quantityConsumed = new DoubleFilter();
        }
        return quantityConsumed;
    }

    public void setQuantityConsumed(DoubleFilter quantityConsumed) {
        this.quantityConsumed = quantityConsumed;
    }

    public DoubleFilter getTotalProductsCost() {
        return totalProductsCost;
    }

    public DoubleFilter totalProductsCost() {
        if (totalProductsCost == null) {
            totalProductsCost = new DoubleFilter();
        }
        return totalProductsCost;
    }

    public void setTotalProductsCost(DoubleFilter totalProductsCost) {
        this.totalProductsCost = totalProductsCost;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProductConsumptionCriteria that = (ProductConsumptionCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(quantityConsumed, that.quantityConsumed) &&
            Objects.equals(totalProductsCost, that.totalProductsCost) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantityConsumed, totalProductsCost, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductConsumptionCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (quantityConsumed != null ? "quantityConsumed=" + quantityConsumed + ", " : "") +
            (totalProductsCost != null ? "totalProductsCost=" + totalProductsCost + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}

package com.mycompany.myapp.service.criteria;

import com.mycompany.myapp.domain.enumeration.Status;
import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.StockRequest} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.StockRequestResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /stock-requests?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StockRequestCriteria implements Serializable, Criteria {

    /**
     * Class for filtering Status
     */
    public static class StatusFilter extends Filter<Status> {

        public StatusFilter() {}

        public StatusFilter(StatusFilter filter) {
            super(filter);
        }

        @Override
        public StatusFilter copy() {
            return new StatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private DoubleFilter qtyRequired;

    private InstantFilter reqDate;

    private BooleanFilter isProd;

    private StatusFilter status;

    private Boolean distinct;

    public StockRequestCriteria() {}

    public StockRequestCriteria(StockRequestCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.qtyRequired = other.qtyRequired == null ? null : other.qtyRequired.copy();
        this.reqDate = other.reqDate == null ? null : other.reqDate.copy();
        this.isProd = other.isProd == null ? null : other.isProd.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.distinct = other.distinct;
    }

    @Override
    public StockRequestCriteria copy() {
        return new StockRequestCriteria(this);
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

    public DoubleFilter getQtyRequired() {
        return qtyRequired;
    }

    public DoubleFilter qtyRequired() {
        if (qtyRequired == null) {
            qtyRequired = new DoubleFilter();
        }
        return qtyRequired;
    }

    public void setQtyRequired(DoubleFilter qtyRequired) {
        this.qtyRequired = qtyRequired;
    }

    public InstantFilter getReqDate() {
        return reqDate;
    }

    public InstantFilter reqDate() {
        if (reqDate == null) {
            reqDate = new InstantFilter();
        }
        return reqDate;
    }

    public void setReqDate(InstantFilter reqDate) {
        this.reqDate = reqDate;
    }

    public BooleanFilter getIsProd() {
        return isProd;
    }

    public BooleanFilter isProd() {
        if (isProd == null) {
            isProd = new BooleanFilter();
        }
        return isProd;
    }

    public void setIsProd(BooleanFilter isProd) {
        this.isProd = isProd;
    }

    public StatusFilter getStatus() {
        return status;
    }

    public StatusFilter status() {
        if (status == null) {
            status = new StatusFilter();
        }
        return status;
    }

    public void setStatus(StatusFilter status) {
        this.status = status;
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
        final StockRequestCriteria that = (StockRequestCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(qtyRequired, that.qtyRequired) &&
            Objects.equals(reqDate, that.reqDate) &&
            Objects.equals(isProd, that.isProd) &&
            Objects.equals(status, that.status) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qtyRequired, reqDate, isProd, status, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StockRequestCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (qtyRequired != null ? "qtyRequired=" + qtyRequired + ", " : "") +
            (reqDate != null ? "reqDate=" + reqDate + ", " : "") +
            (isProd != null ? "isProd=" + isProd + ", " : "") +
            (status != null ? "status=" + status + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}

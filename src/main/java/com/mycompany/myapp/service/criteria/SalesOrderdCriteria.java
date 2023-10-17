package com.mycompany.myapp.service.criteria;

import com.mycompany.myapp.domain.enumeration.DeliveryStatus;
import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.SalesOrderd} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.SalesOrderdResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sales-orderds?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SalesOrderdCriteria implements Serializable, Criteria {

    /**
     * Class for filtering DeliveryStatus
     */
    public static class DeliveryStatusFilter extends Filter<DeliveryStatus> {

        public DeliveryStatusFilter() {}

        public DeliveryStatusFilter(DeliveryStatusFilter filter) {
            super(filter);
        }

        @Override
        public DeliveryStatusFilter copy() {
            return new DeliveryStatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter orderDate;

    private DoubleFilter quantitySold;

    private DoubleFilter unitPrice;

    private DoubleFilter gstPercentage;

    private DoubleFilter totalRevenue;

    private DeliveryStatusFilter status;
    
    private LongFilter clientsId;

    private Boolean distinct;

    public SalesOrderdCriteria() {}

    public SalesOrderdCriteria(SalesOrderdCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.orderDate = other.orderDate == null ? null : other.orderDate.copy();
        this.quantitySold = other.quantitySold == null ? null : other.quantitySold.copy();
        this.unitPrice = other.unitPrice == null ? null : other.unitPrice.copy();
        this.gstPercentage = other.gstPercentage == null ? null : other.gstPercentage.copy();
        this.totalRevenue = other.totalRevenue == null ? null : other.totalRevenue.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.clientsId = other.clientsId == null ? null : other.clientsId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public SalesOrderdCriteria copy() {
        return new SalesOrderdCriteria(this);
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

    public InstantFilter getOrderDate() {
        return orderDate;
    }

    public InstantFilter orderDate() {
        if (orderDate == null) {
            orderDate = new InstantFilter();
        }
        return orderDate;
    }

    public void setOrderDate(InstantFilter orderDate) {
        this.orderDate = orderDate;
    }

    public DoubleFilter getQuantitySold() {
        return quantitySold;
    }

    public DoubleFilter quantitySold() {
        if (quantitySold == null) {
            quantitySold = new DoubleFilter();
        }
        return quantitySold;
    }

    public void setQuantitySold(DoubleFilter quantitySold) {
        this.quantitySold = quantitySold;
    }

    public DoubleFilter getUnitPrice() {
        return unitPrice;
    }

    public DoubleFilter unitPrice() {
        if (unitPrice == null) {
            unitPrice = new DoubleFilter();
        }
        return unitPrice;
    }

    public void setUnitPrice(DoubleFilter unitPrice) {
        this.unitPrice = unitPrice;
    }

    public DoubleFilter getGstPercentage() {
        return gstPercentage;
    }

    public DoubleFilter gstPercentage() {
        if (gstPercentage == null) {
            gstPercentage = new DoubleFilter();
        }
        return gstPercentage;
    }

    public void setGstPercentage(DoubleFilter gstPercentage) {
        this.gstPercentage = gstPercentage;
    }

    public DoubleFilter getTotalRevenue() {
        return totalRevenue;
    }

    public DoubleFilter totalRevenue() {
        if (totalRevenue == null) {
            totalRevenue = new DoubleFilter();
        }
        return totalRevenue;
    }

    public void setTotalRevenue(DoubleFilter totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public DeliveryStatusFilter getStatus() {
        return status;
    }

    public DeliveryStatusFilter status() {
        if (status == null) {
            status = new DeliveryStatusFilter();
        }
        return status;
    }

    public void setStatus(DeliveryStatusFilter status) {
        this.status = status;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }
    
    public LongFilter getClientsId() {
        return clientsId;
    }

    public LongFilter clientsId() {
        if (clientsId == null) {
            clientsId = new LongFilter();
        }
        return clientsId;
    }

    public void setClientsId(LongFilter clientsId) {
        this.clientsId = clientsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SalesOrderdCriteria that = (SalesOrderdCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(orderDate, that.orderDate) &&
            Objects.equals(quantitySold, that.quantitySold) &&
            Objects.equals(unitPrice, that.unitPrice) &&
            Objects.equals(gstPercentage, that.gstPercentage) &&
            Objects.equals(totalRevenue, that.totalRevenue) &&
            Objects.equals(status, that.status) &&
            Objects.equals(clientsId, that.clientsId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, quantitySold, unitPrice, gstPercentage, totalRevenue,clientsId, status, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderdCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (orderDate != null ? "orderDate=" + orderDate + ", " : "") +
            (quantitySold != null ? "quantitySold=" + quantitySold + ", " : "") +
            (unitPrice != null ? "unitPrice=" + unitPrice + ", " : "") +
            (gstPercentage != null ? "gstPercentage=" + gstPercentage + ", " : "") +
            (totalRevenue != null ? "totalRevenue=" + totalRevenue + ", " : "") +
            (status != null ? "status=" + status + ", " : "") +
            (clientsId != null ? "clientsId=" + clientsId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}

package com.mycompany.myapp.service.criteria;

import com.mycompany.myapp.domain.enumeration.Status;
import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.PurchaseQuotation} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.PurchaseQuotationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /purchase-quotations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PurchaseQuotationCriteria implements Serializable, Criteria {

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

    private StringFilter referenceNumber;

    private DoubleFilter totalPOAmount;

    private DoubleFilter totalGSTAmount;

    private InstantFilter poDate;

    private InstantFilter expectedDeliveryDate;

    private StatusFilter orderStatus;
    
    private LongFilter clientsId;
    
  	private LongFilter purchaseQuotationDetailsId;

    private Boolean distinct;

    public PurchaseQuotationCriteria() {}

    public PurchaseQuotationCriteria(PurchaseQuotationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.referenceNumber = other.referenceNumber == null ? null : other.referenceNumber.copy();
        this.totalPOAmount = other.totalPOAmount == null ? null : other.totalPOAmount.copy();
        this.totalGSTAmount = other.totalGSTAmount == null ? null : other.totalGSTAmount.copy();
        this.poDate = other.poDate == null ? null : other.poDate.copy();
        this.expectedDeliveryDate = other.expectedDeliveryDate == null ? null : other.expectedDeliveryDate.copy();
        this.orderStatus = other.orderStatus == null ? null : other.orderStatus.copy();
        this.clientsId = other.clientsId == null ? null : other.clientsId.copy();
        this.purchaseQuotationDetailsId = other.purchaseQuotationDetailsId == null ? null : other.purchaseQuotationDetailsId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public PurchaseQuotationCriteria copy() {
        return new PurchaseQuotationCriteria(this);
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

    public StringFilter getReferenceNumber() {
        return referenceNumber;
    }

    public StringFilter referenceNumber() {
        if (referenceNumber == null) {
            referenceNumber = new StringFilter();
        }
        return referenceNumber;
    }

    public void setReferenceNumber(StringFilter referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public DoubleFilter getTotalPOAmount() {
        return totalPOAmount;
    }

    public DoubleFilter totalPOAmount() {
        if (totalPOAmount == null) {
            totalPOAmount = new DoubleFilter();
        }
        return totalPOAmount;
    }

    public void setTotalPOAmount(DoubleFilter totalPOAmount) {
        this.totalPOAmount = totalPOAmount;
    }

    public DoubleFilter getTotalGSTAmount() {
        return totalGSTAmount;
    }

    public DoubleFilter totalGSTAmount() {
        if (totalGSTAmount == null) {
            totalGSTAmount = new DoubleFilter();
        }
        return totalGSTAmount;
    }

    public void setTotalGSTAmount(DoubleFilter totalGSTAmount) {
        this.totalGSTAmount = totalGSTAmount;
    }

    public InstantFilter getPoDate() {
        return poDate;
    }

    public InstantFilter poDate() {
        if (poDate == null) {
            poDate = new InstantFilter();
        }
        return poDate;
    }

    public void setPoDate(InstantFilter poDate) {
        this.poDate = poDate;
    }

    public InstantFilter getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public InstantFilter expectedDeliveryDate() {
        if (expectedDeliveryDate == null) {
            expectedDeliveryDate = new InstantFilter();
        }
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(InstantFilter expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public StatusFilter getOrderStatus() {
        return orderStatus;
    }

    public StatusFilter orderStatus() {
        if (orderStatus == null) {
            orderStatus = new StatusFilter();
        }
        return orderStatus;
    }

    public void setOrderStatus(StatusFilter orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    //added new fields
    
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
    
    public LongFilter getPurchaseQuotationDetailsId() {
  		return purchaseQuotationDetailsId;
  	}

  	public void setPurchaseQuotationDetailsId(LongFilter purchaseQuotationDetailsId) {
  		this.purchaseQuotationDetailsId = purchaseQuotationDetailsId;
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
        final PurchaseQuotationCriteria that = (PurchaseQuotationCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(referenceNumber, that.referenceNumber) &&
            Objects.equals(totalPOAmount, that.totalPOAmount) &&
            Objects.equals(totalGSTAmount, that.totalGSTAmount) &&
            Objects.equals(poDate, that.poDate) &&
            Objects.equals(expectedDeliveryDate, that.expectedDeliveryDate) &&
            Objects.equals(orderStatus, that.orderStatus) &&
            Objects.equals(clientsId, that.clientsId) &&
            Objects.equals(purchaseQuotationDetailsId, that.purchaseQuotationDetailsId) &&            
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, referenceNumber, totalPOAmount, totalGSTAmount, poDate, expectedDeliveryDate, orderStatus,clientsId,purchaseQuotationDetailsId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseQuotationCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (referenceNumber != null ? "referenceNumber=" + referenceNumber + ", " : "") +
            (totalPOAmount != null ? "totalPOAmount=" + totalPOAmount + ", " : "") +
            (totalGSTAmount != null ? "totalGSTAmount=" + totalGSTAmount + ", " : "") +
            (poDate != null ? "poDate=" + poDate + ", " : "") +
            (expectedDeliveryDate != null ? "expectedDeliveryDate=" + expectedDeliveryDate + ", " : "") +
            (orderStatus != null ? "orderStatus=" + orderStatus + ", " : "") +
            (clientsId != null ? "clientsId=" + clientsId + ", " : "") +
            (purchaseQuotationDetailsId != null ? "purchaseQuotationDetailsId=" + purchaseQuotationDetailsId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}

package com.mycompany.myapp.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.PurchaseQuotationDetails} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.PurchaseQuotationDetailsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /purchase-quotation-details?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PurchaseQuotationDetailsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private DoubleFilter qtyOrdered;

    private IntegerFilter gstTaxPercentage;

    private DoubleFilter pricePerUnit;

    private DoubleFilter totalPrice;

    private DoubleFilter discount;
    
    private LongFilter rawMaterialId;

	private Boolean distinct;

    public PurchaseQuotationDetailsCriteria() {}

    public PurchaseQuotationDetailsCriteria(PurchaseQuotationDetailsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.qtyOrdered = other.qtyOrdered == null ? null : other.qtyOrdered.copy();
        this.gstTaxPercentage = other.gstTaxPercentage == null ? null : other.gstTaxPercentage.copy();
        this.pricePerUnit = other.pricePerUnit == null ? null : other.pricePerUnit.copy();
        this.totalPrice = other.totalPrice == null ? null : other.totalPrice.copy();
        this.discount = other.discount == null ? null : other.discount.copy();
        this.distinct = other.distinct;
    }

    @Override
    public PurchaseQuotationDetailsCriteria copy() {
        return new PurchaseQuotationDetailsCriteria(this);
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

    public DoubleFilter getQtyOrdered() {
        return qtyOrdered;
    }

    public DoubleFilter qtyOrdered() {
        if (qtyOrdered == null) {
            qtyOrdered = new DoubleFilter();
        }
        return qtyOrdered;
    }

    public void setQtyOrdered(DoubleFilter qtyOrdered) {
        this.qtyOrdered = qtyOrdered;
    }

    public IntegerFilter getGstTaxPercentage() {
        return gstTaxPercentage;
    }

    public IntegerFilter gstTaxPercentage() {
        if (gstTaxPercentage == null) {
            gstTaxPercentage = new IntegerFilter();
        }
        return gstTaxPercentage;
    }

    public void setGstTaxPercentage(IntegerFilter gstTaxPercentage) {
        this.gstTaxPercentage = gstTaxPercentage;
    }

    public DoubleFilter getPricePerUnit() {
        return pricePerUnit;
    }

    public DoubleFilter pricePerUnit() {
        if (pricePerUnit == null) {
            pricePerUnit = new DoubleFilter();
        }
        return pricePerUnit;
    }

    public void setPricePerUnit(DoubleFilter pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public DoubleFilter getTotalPrice() {
        return totalPrice;
    }

    public DoubleFilter totalPrice() {
        if (totalPrice == null) {
            totalPrice = new DoubleFilter();
        }
        return totalPrice;
    }

    public void setTotalPrice(DoubleFilter totalPrice) {
        this.totalPrice = totalPrice;
    }

    public DoubleFilter getDiscount() {
        return discount;
    }

    public DoubleFilter discount() {
        if (discount == null) {
            discount = new DoubleFilter();
        }
        return discount;
    }

    public void setDiscount(DoubleFilter discount) {
        this.discount = discount;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    public LongFilter getRawMaterialId() {
		return rawMaterialId;
	}

	public void setRawMaterialId(LongFilter rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}

	 public LongFilter rawMaterialId() {
	        if (rawMaterialId == null) {
	        	rawMaterialId = new LongFilter();
	        }
	        return rawMaterialId;
	   }	
	
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PurchaseQuotationDetailsCriteria that = (PurchaseQuotationDetailsCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(qtyOrdered, that.qtyOrdered) &&
            Objects.equals(gstTaxPercentage, that.gstTaxPercentage) &&
            Objects.equals(pricePerUnit, that.pricePerUnit) &&
            Objects.equals(totalPrice, that.totalPrice) &&
            Objects.equals(discount, that.discount) &&
            Objects.equals(rawMaterialId, that.rawMaterialId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qtyOrdered, gstTaxPercentage, pricePerUnit, totalPrice, discount,rawMaterialId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseQuotationDetailsCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (qtyOrdered != null ? "qtyOrdered=" + qtyOrdered + ", " : "") +
            (gstTaxPercentage != null ? "gstTaxPercentage=" + gstTaxPercentage + ", " : "") +
            (pricePerUnit != null ? "pricePerUnit=" + pricePerUnit + ", " : "") +
            (totalPrice != null ? "totalPrice=" + totalPrice + ", " : "") +
            (discount != null ? "discount=" + discount + ", " : "") +
            (rawMaterialId != null ? "rawMaterialId" + rawMaterialId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}

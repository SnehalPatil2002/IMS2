package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A PurchaseQuotationDetails.
 */
@Entity
@Table(name = "purchase_quotation_details")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PurchaseQuotationDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "qty_ordered")
    private Double qtyOrdered;

    @Column(name = "gst_tax_percentage")
    private Integer gstTaxPercentage;

    @Column(name = "price_per_unit")
    private Double pricePerUnit;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "discount")
    private Double discount;

    @ManyToOne(fetch=FetchType.LAZY)
    private RawMaterial rawMaterial;

    public RawMaterial getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(RawMaterial rawmaterial) {
		this.rawMaterial = rawmaterial;
	}
	
	public PurchaseQuotationDetails rawMaterial(RawMaterial rawMaterial) {
        this.setRawMaterial(rawMaterial);
        return this;
}

	public Long getId() {
        return this.id;
    }

    public PurchaseQuotationDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQtyOrdered() {
        return this.qtyOrdered;
    }

    public PurchaseQuotationDetails qtyOrdered(Double qtyOrdered) {
        this.setQtyOrdered(qtyOrdered);
        return this;
    }

    public void setQtyOrdered(Double qtyOrdered) {
        this.qtyOrdered = qtyOrdered;
    }

    public Integer getGstTaxPercentage() {
        return this.gstTaxPercentage;
    }

    public PurchaseQuotationDetails gstTaxPercentage(Integer gstTaxPercentage) {
        this.setGstTaxPercentage(gstTaxPercentage);
        return this;
    }

    public void setGstTaxPercentage(Integer gstTaxPercentage) {
        this.gstTaxPercentage = gstTaxPercentage;
    }

    public Double getPricePerUnit() {
        return this.pricePerUnit;
    }

    public PurchaseQuotationDetails pricePerUnit(Double pricePerUnit) {
        this.setPricePerUnit(pricePerUnit);
        return this;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }

    public PurchaseQuotationDetails totalPrice(Double totalPrice) {
        this.setTotalPrice(totalPrice);
        return this;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public PurchaseQuotationDetails discount(Double discount) {
        this.setDiscount(discount);
        return this;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseQuotationDetails)) {
            return false;
        }
        return id != null && id.equals(((PurchaseQuotationDetails) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseQuotationDetails{" +
            "id=" + getId() +
            ", qtyOrdered=" + getQtyOrdered() +
            ", gstTaxPercentage=" + getGstTaxPercentage() +
            ", pricePerUnit=" + getPricePerUnit() +
            ", totalPrice=" + getTotalPrice() +
            ", discount=" + getDiscount() +
            "}";
    }
}

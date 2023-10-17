package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.Clients;
import com.mycompany.myapp.domain.enumeration.Status;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.PurchaseQuotation} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PurchaseQuotationDTO implements Serializable {

    private Long id;

    private String referenceNumber;

    private Double totalPOAmount;

    private Double totalGSTAmount;

    private Instant poDate;

    private Instant expectedDeliveryDate;

    private Status orderStatus;
    
    private ClientsDTO clients;
    
    private PurchaseQuotationDetailsDTO purchaseQuotationDetails;

	public ClientsDTO getClients() {
		return clients;
	}

	public void setClients(ClientsDTO clients) {
		this.clients = clients;
	}

	public PurchaseQuotationDetailsDTO getPurchaseQuotationDetails() {
		return purchaseQuotationDetails;
	}

	public void setPurchaseQuotationDetails(PurchaseQuotationDetailsDTO purchaseQuotationDetails) {
		this.purchaseQuotationDetails = purchaseQuotationDetails;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Double getTotalPOAmount() {
        return totalPOAmount;
    }

    public void setTotalPOAmount(Double totalPOAmount) {
        this.totalPOAmount = totalPOAmount;
    }

    public Double getTotalGSTAmount() {
        return totalGSTAmount;
    }

    public void setTotalGSTAmount(Double totalGSTAmount) {
        this.totalGSTAmount = totalGSTAmount;
    }

    public Instant getPoDate() {
        return poDate;
    }

    public void setPoDate(Instant poDate) {
        this.poDate = poDate;
    }

    public Instant getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Instant expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public Status getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseQuotationDTO)) {
            return false;
        }

        PurchaseQuotationDTO purchaseQuotationDTO = (PurchaseQuotationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, purchaseQuotationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseQuotationDTO{" +
            "id=" + getId() +
            ", referenceNumber='" + getReferenceNumber() + "'" +
            ", totalPOAmount=" + getTotalPOAmount() +
            ", totalGSTAmount=" + getTotalGSTAmount() +
            ", poDate='" + getPoDate() + "'" +
            ", expectedDeliveryDate='" + getExpectedDeliveryDate() + "'" +
            ", orderStatus='" + getOrderStatus() + "'" +
            ", clients=" + getClients() +
            ", purchaseQuotationDetails="+getPurchaseQuotationDetails()+
            "}";
    }

	
}

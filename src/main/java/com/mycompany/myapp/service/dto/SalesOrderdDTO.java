package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.DeliveryStatus;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.SalesOrderd} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SalesOrderdDTO implements Serializable {

    private Long id;

    private Instant orderDate;

    private Double quantitySold;

    private Double unitPrice;

    private Double gstPercentage;

    private Double totalRevenue;

    private DeliveryStatus status;
    
    private ClientsDTO clients;

    public ClientsDTO getClients() {
		return clients;
	}

	public void setClients(ClientsDTO clients) {
		this.clients = clients;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    public Double getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(Double quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getGstPercentage() {
        return gstPercentage;
    }

    public void setGstPercentage(Double gstPercentage) {
        this.gstPercentage = gstPercentage;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesOrderdDTO)) {
            return false;
        }

        SalesOrderdDTO salesOrderdDTO = (SalesOrderdDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, salesOrderdDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderdDTO{" +
            "id=" + getId() +
            ", orderDate='" + getOrderDate() + "'" +
            ", quantitySold=" + getQuantitySold() +
            ", unitPrice=" + getUnitPrice() +
            ", gstPercentage=" + getGstPercentage() +
            ", totalRevenue=" + getTotalRevenue() +
            ", status='" + getStatus() + "'" +
            ", clients=" + getClients() +
            "}";
    }
}

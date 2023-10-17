package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Clients;
import com.mycompany.myapp.domain.PurchaseQuotation;
import com.mycompany.myapp.domain.PurchaseQuotationDetails;
import com.mycompany.myapp.service.dto.ClientsDTO;
import com.mycompany.myapp.service.dto.PurchaseQuotationDTO;
import com.mycompany.myapp.service.dto.PurchaseQuotationDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PurchaseQuotation} and its DTO {@link PurchaseQuotationDTO}.
 */
@Mapper(componentModel = "spring")
public interface PurchaseQuotationMapper extends EntityMapper<PurchaseQuotationDTO, PurchaseQuotation> {
	
	@Mapping(target = "clients", qualifiedByName = "clientsId")
	@Mapping(target = "purchaseQuotationDetails", qualifiedByName = "purchaseQuotationDetailsId")
	PurchaseQuotationDTO toDto(PurchaseQuotation p);
	
	@Named("clientsId")
//    @BeanMapping(ignoreByDefault = true)
//    @Mapping(target = "id", source = "id")
//	@Mapping(target = "sname", source = "sname")
//	@Mapping(target = "semail", source = "semail")
	ClientsDTO toDtoClientsDTO(Clients clients);
	
	@Named("purchaseQuotationDetailsId")
	PurchaseQuotationDetailsDTO toDtoPurchaseQuotationDetailsDTO(PurchaseQuotationDetails purchaseQuotationDetails);
	
	
}

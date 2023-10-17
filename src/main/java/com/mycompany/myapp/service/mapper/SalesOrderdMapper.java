package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Clients;
import com.mycompany.myapp.domain.SalesOrderd;
import com.mycompany.myapp.service.dto.ClientsDTO;
import com.mycompany.myapp.service.dto.SalesOrderdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesOrderd} and its DTO {@link SalesOrderdDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesOrderdMapper extends EntityMapper<SalesOrderdDTO, SalesOrderd> {
	
	@Mapping(target = "clients", qualifiedByName = "clientsId")
	SalesOrderdDTO toDto(SalesOrderd p);
	
	@Named("clientsId")
	ClientsDTO toDtoClientsDTO(Clients clients);
}

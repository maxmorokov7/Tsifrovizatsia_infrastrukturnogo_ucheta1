package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.mapper;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ConnectivityEntity;
import org.springframework.stereotype.Component;
import ru.example.infrastructure.web.model.ConnectivityResponse;

@Component
public class ConnectivityMapper {

    public ConnectivityResponse toResponse(ConnectivityEntity e) {
        ConnectivityResponse dto = new ConnectivityResponse();

        dto.setId(e.getId());
        dto.setType(e.getType() != null ? ConnectivityResponse.TypeEnum.valueOf(e.getType().name()) : null);
        dto.setService(e.getService());
        dto.setDirection(e.getDirection() != null ? ConnectivityResponse.DirectionEnum.valueOf(e.getDirection().name()) : null);
        dto.setSubnetName(e.getSubnetName());
        dto.setSourceAddress(e.getSourceAddress());
        dto.setDestinationAddress(e.getDestinationAddress());
        dto.setProtocolsAndPorts(e.getProtocolsAndPorts());
        dto.setAccessJustification(e.getAccessJustification());
        dto.setExpirationType(e.getExpirationType() != null ? ConnectivityResponse.ExpirationTypeEnum.valueOf(e.getExpirationType().name()) : null);
        dto.setExpirationDate(e.getExpirationDate());
        dto.setRequestNumber(e.getRequestNumber());
        dto.setStatus(e.getStatus() != null ? ConnectivityResponse.StatusEnum.valueOf(e.getStatus().name()) : null);
        dto.setNote(e.getNote());

        dto.setSourceServerId(e.getSourceServer() != null ? e.getSourceServer().getId() : null);
        dto.setDestinationServerId(e.getDestinationServer() != null ? e.getDestinationServer().getId() : null);

        return dto;
    }
}
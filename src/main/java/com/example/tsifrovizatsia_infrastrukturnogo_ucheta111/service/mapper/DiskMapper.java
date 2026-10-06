package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.mapper;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.DTO.DiskSummaryDTO;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.DiskEntity;
import org.springframework.stereotype.Component;
import ru.example.infrastructure.web.model.DiskResponse;
import ru.example.infrastructure.web.model.DiskSummaryDto;

@Component
public class DiskMapper {

    public DiskResponse toResponse(DiskEntity e) {
        DiskResponse dto = new DiskResponse();
        dto.setId(e.getId());
        dto.setType(DiskResponse.TypeEnum.fromValue(e.getType().name()));
        dto.setCapacityGb(e.getCapacityGb());
        dto.setServerId(e.getServer() != null ? e.getServer().getId() : null);
        return dto;
    }

    /**
     * Ваш record → сгенерированный OpenAPI DTO.
     */
    public ru.example.infrastructure.web.model.DiskSummaryDto toGenerated(DiskSummaryDTO src) {
        var dto = new DiskSummaryDto();
        dto.setTotalCount(src.totalCount());
        dto.setTotalCapacityGb(src.totalCapacityGb());
        dto.setSsdCount(src.ssdCount());
        dto.setSsdCapacityGb(src.ssdCapacityGb());
        dto.setHddCount(src.hddCount());
        dto.setHddCapacityGb(src.hddCapacityGb());
        return dto;
    }
}
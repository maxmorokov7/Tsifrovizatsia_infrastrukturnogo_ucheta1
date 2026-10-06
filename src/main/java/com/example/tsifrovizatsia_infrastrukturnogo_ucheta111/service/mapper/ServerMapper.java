package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.mapper;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ServerEntity;
import org.springframework.stereotype.Component;
import ru.example.infrastructure.web.model.ServerResponse;
import ru.example.infrastructure.web.model.ServerSpecificationDto;

@Component
public class ServerMapper {

    public ServerResponse toResponse(ServerEntity e) {
        ServerResponse dto = new ServerResponse();
        dto.setId(e.getId());
        dto.setHostname(e.getHostname());
        dto.setIpAddress(e.getIpAddress());
        dto.setRole(e.getRole());
        dto.setEnvironment(e.getEnvironment());
        dto.setOperatingSystem(e.getOperatingSystem());
        dto.setCpuCores(e.getCpuCores());
        dto.setRamGb(e.getRamGb());
        dto.setProjectId(e.getProject() != null ? e.getProject().getId() : null);
        return dto;
    }

    /**
     * Ваш внутренний ServerSpecificationDto → сгенерированный.
     */
    public ServerSpecificationDto toGenerated(
            com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.DTO.ServerSpecificationDto src) {

        ServerSpecificationDto dto = new ServerSpecificationDto();
        dto.setServerId(src.serverId());
        dto.setHostname(src.hostname());
        dto.setCpuCores(src.cpuCores());
        dto.setRamGb(src.ramGb());

        var diskSrc = src.disks();
        if (diskSrc != null) {
            var diskDto = new ru.example.infrastructure.web.model.DiskSummaryDto();
            diskDto.setTotalCount(diskSrc.totalCount());
            diskDto.setTotalCapacityGb(diskSrc.totalCapacityGb());
            diskDto.setSsdCount(diskSrc.ssdCount());
            diskDto.setSsdCapacityGb(diskSrc.ssdCapacityGb());
            diskDto.setHddCount(diskSrc.hddCount());
            diskDto.setHddCapacityGb(diskSrc.hddCapacityGb());
            dto.setDisks(diskDto);
        }
        return dto;
    }
}
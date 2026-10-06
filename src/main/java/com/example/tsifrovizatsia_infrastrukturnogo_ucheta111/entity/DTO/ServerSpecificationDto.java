package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.DTO;

public record ServerSpecificationDto(
        Long serverId,
        String hostname,
        Integer cpuCores,
        Integer ramGb,
        DiskSummaryDTO disks
) {}
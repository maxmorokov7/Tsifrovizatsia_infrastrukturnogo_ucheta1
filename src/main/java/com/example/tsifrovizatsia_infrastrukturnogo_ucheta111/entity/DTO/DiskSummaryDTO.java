package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.DTO;


public record DiskSummaryDTO(
        long totalCount,
        long totalCapacityGb,
        long ssdCount,
        long ssdCapacityGb,
        long hddCount,
        long hddCapacityGb
) {}
package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.DTO;


public record DiskSummaryDto(
        long totalCount,
        long totalCapacityGb,
        long ssdCount,
        long ssdCapacityGb,
        long hddCount,
        long hddCapacityGb
) {}
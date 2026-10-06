package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.DTO.DiskSummaryDTO;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.DiskEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ServerEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.enums.DiskType;

import java.util.List;

public interface DiskService {
    DiskEntity addDiskToServer(Long serverId, DiskType type, Long capacityGb);

    DiskEntity addDiskToServer(ServerEntity server, DiskType type, Long capacityGb);

    void removeDiskFromServer(Long serverId, Long diskId);

    List<DiskEntity> getDisksByServer(Long serverId);

    long totalCapacityGb(Long serverId);

    long capacityByType(Long serverId, DiskType type);

    long countByType(Long serverId, DiskType type);

    DiskSummaryDTO summarize(Long serverId);

}

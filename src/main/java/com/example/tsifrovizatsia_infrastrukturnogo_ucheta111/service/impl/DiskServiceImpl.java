package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.impl;


import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.DTO.DiskSummaryDTO;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.DiskEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ServerEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.enums.DiskType;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.repository.DiskRepository;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.repository.ServerRepository;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.DiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DiskServiceImpl implements DiskService {

    private final DiskRepository diskRepository;
    private final ServerRepository serverRepository;

    @Override
    @Transactional
    public DiskEntity addDiskToServer(Long serverId, DiskType type, Long capacityGb) {
        ServerEntity server = serverRepository.findById(serverId)
                .orElseThrow(() -> new IllegalArgumentException("Server not found: " + serverId));
        return addDiskToServer(server, type, capacityGb);
    }

    @Override
    @Transactional
    public DiskEntity addDiskToServer(ServerEntity server, DiskType type, Long capacityGb) {
        DiskEntity disk = DiskEntity.builder()
                .type(type)
                .capacityGb(capacityGb)
                .server(server)
                .build();
        return diskRepository.save(disk);
    }

    @Override
    @Transactional
    public void removeDiskFromServer(Long serverId, Long diskId) {
        DiskEntity disk = diskRepository.findByIdAndServerId(diskId, serverId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Disk " + diskId + " not found on server " + serverId));
        diskRepository.delete(disk);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DiskEntity> getDisksByServer(Long serverId) {
        return diskRepository.findAllByServerId(serverId);
    }

    @Override
    @Transactional(readOnly = true)
    public long totalCapacityGb(Long serverId) {
        return diskRepository.sumCapacityByServerId(serverId);
    }

    @Override
    @Transactional(readOnly = true)
    public long capacityByType(Long serverId, DiskType type) {
        return diskRepository.sumCapacityByServerIdAndType(serverId, type);
    }

    @Override
    @Transactional(readOnly = true)
    public long countByType(Long serverId, DiskType type) {
        return diskRepository.countByServerIdAndType(serverId, type);
    }

    @Override
    @Transactional(readOnly = true)
    public DiskSummaryDTO summarize(Long serverId) {
        return new DiskSummaryDTO(
                diskRepository.countByServerId(serverId),
                diskRepository.sumCapacityByServerId(serverId),
                diskRepository.countByServerIdAndType(serverId, DiskType.SSD),
                diskRepository.sumCapacityByServerIdAndType(serverId, DiskType.SSD),
                diskRepository.countByServerIdAndType(serverId, DiskType.HDD),
                diskRepository.sumCapacityByServerIdAndType(serverId, DiskType.HDD)
        );
    }
}
package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.impl;


import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.DTO.DiskSummaryDto;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.DiskEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ServerEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.enums.DiskType;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.repository.DiskRepository;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.DiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class DiskServiceImpl implements DiskService {

    private final DiskRepository diskRepository;

    @Transactional
    public DiskEntity addDiskToServer(Long serverId, DiskType type, Long capacityGb) {
        DiskEntity disk = DiskEntity.builder()
                .type(type)
                .capacityGb(capacityGb)
                .build();
        // server будет установлен вызывающим сервисом или ниже:
        // здесь предполагаем, что Server уже загружен в ServerService,
        // но чтобы не плодить зависимость — используем отдельный метод.
        throw new UnsupportedOperationException("see addDiskToServer(Server, ...)");
    }

    @Transactional
    public DiskEntity addDiskToServer(ServerEntity server, DiskType type, Long capacityGb) {
        DiskEntity disk = DiskEntity.builder()
                .type(type)
                .capacityGb(capacityGb)
                .server(server)
                .build();
        return diskRepository.save(disk);
    }

    @Transactional
    public void removeDiskFromServer(Long serverId, Long diskId) {
        diskRepository.findByIdAndServerId(diskId, serverId)
                .ifPresent(diskRepository::delete);
    }

    @Transactional(readOnly = true)
    public List<DiskEntity> getDisksByServer(Long serverId) {
        return diskRepository.findAllByServerId(serverId);
    }

    @Transactional(readOnly = true)
    public long totalCapacityGb(Long serverId) {
        return diskRepository.sumCapacityByServerId(serverId);
    }

    @Transactional(readOnly = true)
    public long capacityByType(Long serverId, DiskType type) {
        return diskRepository.sumCapacityByServerIdAndType(serverId, type);
    }

    @Transactional(readOnly = true)
    public long countByType(Long serverId, DiskType type) {
        return diskRepository.countByServerIdAndType(serverId, type);
    }

    @Transactional(readOnly = true)
    public DiskSummaryDto summarize(Long serverId) {
        return new DiskSummaryDto(
                diskRepository.findAllByServerId(serverId).size(),
                diskRepository.sumCapacityByServerId(serverId),
                diskRepository.countByServerIdAndType(serverId, DiskType.SSD),
                diskRepository.sumCapacityByServerIdAndType(serverId, DiskType.SSD),
                diskRepository.countByServerIdAndType(serverId, DiskType.HDD),
                diskRepository.sumCapacityByServerIdAndType(serverId, DiskType.HDD)
        );
    }
}
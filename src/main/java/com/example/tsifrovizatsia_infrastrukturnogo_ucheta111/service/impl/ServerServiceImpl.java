package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.impl;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.DTO.ServerSpecificationDto;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ServerEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.repository.ServerRepository;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.DiskService;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;
    private final DiskService diskService;

    @Transactional(readOnly = true)
    public ServerEntity getById(Long id) {
        return serverRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Server not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<ServerEntity> findAll() {
        return serverRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<ServerEntity> findByProject(Long projectId) {
        return serverRepository.findAllByProjectId(projectId);
    }

    @Transactional
    public ServerEntity create(ServerEntity server) {
        return serverRepository.save(server);
    }

    @Transactional
    public ServerEntity updateSpecification(Long serverId, Integer cpuCores, Integer ramGb) {
        ServerEntity server = getById(serverId);
        server.setCpuCores(cpuCores);
        server.setRamGb(ramGb);
        return server;
    }

    /**
     * Характеристики сервера: vCPU, RAM, сводка по дискам.
     * Все суммы считаются в БД, без загрузки коллекции в память.
     */
    @Transactional(readOnly = true)
    public ServerSpecificationDto getSpecification(Long serverId) {
        ServerEntity server = getById(serverId);
        return new ServerSpecificationDto(
                server.getId(),
                server.getHostname(),
                server.getCpuCores(),
                server.getRamGb(),
                diskService.summarize(serverId)
        );
    }
}
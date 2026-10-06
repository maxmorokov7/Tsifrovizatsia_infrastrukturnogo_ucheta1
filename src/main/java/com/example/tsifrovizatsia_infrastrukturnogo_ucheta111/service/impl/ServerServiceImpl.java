package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.impl;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.DTO.ServerSpecificationDto;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ProjectEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ServerEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.repository.ProjectRepository;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.repository.ServerRepository;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.DiskService;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.infrastructure.web.model.ServerRequest;
import ru.example.infrastructure.web.model.SpecificationUpdateRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;
    private final ProjectRepository projectRepository;
    private final DiskService diskService;

    @Override
    @Transactional(readOnly = true)
    public ServerEntity getById(Long id) {
        return serverRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Server not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServerEntity> findAll() {
        return serverRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServerEntity> findByProject(Long projectId) {
        return serverRepository.findAllByProjectId(projectId);
    }

    @Override
    @Transactional
    public ServerEntity create(ServerRequest request) {
        ServerEntity server = ServerEntity.builder()
                .hostname(request.getHostname())
                .ipAddress(request.getIpAddress())
                .role(request.getRole())
                .environment(request.getEnvironment())
                .operatingSystem(request.getOperatingSystem())
                .cpuCores(request.getCpuCores())
                .ramGb(request.getRamGb())
                .build();

        if (request.getProjectId() != null) {
            ProjectEntity project = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Project not found: " + request.getProjectId()));
            server.setProject(project);
        }

        return serverRepository.save(server);
    }

    @Override
    @Transactional
    public ServerEntity updateSpecification(Long serverId, SpecificationUpdateRequest request) {
        ServerEntity server = getById(serverId);
        server.setCpuCores(request.getCpuCores());
        server.setRamGb(request.getRamGb());
        return server;
    }

    @Override
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
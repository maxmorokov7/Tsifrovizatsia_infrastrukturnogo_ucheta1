package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.impl;


import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ProjectEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ServerEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.repository.ProjectRepository;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.repository.ServerRepository;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ServerRepository serverRepository;

    @Transactional(readOnly = true)
    public ProjectEntity getById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<ServerEntity> getServers(Long projectId) {
        ProjectEntity project = projectRepository.findByIdWithServers(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found: " + projectId));
        return project.getServers();
    }

    @Transactional
    public void attachServer(Long projectId, Long serverId) {
        ProjectEntity project = getById(projectId);
        ServerEntity server = serverRepository.findById(serverId)
                .orElseThrow(() -> new IllegalArgumentException("Server not found: " + serverId));

        server.setProject(project);
        if (!project.getServers().contains(server)) {
            project.getServers().add(server);
        }
    }

    @Transactional
    public void detachServer(Long projectId, Long serverId) {
        ProjectEntity project = getById(projectId);
        ServerEntity server = serverRepository.findById(serverId)
                .orElseThrow(() -> new IllegalArgumentException("Server not found: " + serverId));

        project.getServers().remove(server);
        server.setProject(null);
    }
}
package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ProjectEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ServerEntity;
import ru.example.infrastructure.web.model.ProjectRequest;

import java.util.List;

public interface ProjectService {

    ProjectEntity getById(Long id);

    List<ProjectEntity> getAll();

    ProjectEntity create(ProjectRequest request);

    List<ServerEntity> getServers(Long projectId);

    void attachServer(Long projectId, Long serverId);

    void detachServer(Long projectId, Long serverId);
}
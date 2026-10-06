package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.controller;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ProjectEntity;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.ProjectService;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.mapper.ProjectMapper;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.mapper.ServerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.infrastructure.web.api.ProjectsApi;
import ru.example.infrastructure.web.model.ProjectRequest;
import ru.example.infrastructure.web.model.ProjectResponse;
import ru.example.infrastructure.web.model.ServerResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectsController implements ProjectsApi {

    private final ProjectService service;
    private final ProjectMapper projectMapper;
    private final ServerMapper serverMapper;

    @Override
    public ResponseEntity<ProjectResponse> createProject(ProjectRequest projectRequest) {
        ProjectEntity saved = service.create(projectRequest);
        return ResponseEntity.ok(projectMapper.toResponse(saved));
    }

    @Override
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        List<ProjectResponse> body = service.getAll().stream()
                .map(projectMapper::toResponse)
                .toList();
        return ResponseEntity.ok(body);
    }

    @Override
    public ResponseEntity<ProjectResponse> getProjectById(Long id) {
        ProjectEntity project = service.getById(id);
        return ResponseEntity.ok(projectMapper.toResponse(project));
    }

    @Override
    public ResponseEntity<List<ServerResponse>> getProjectServers(Long id) {
        List<ServerResponse> body = service.getServers(id).stream()
                .map(serverMapper::toResponse)
                .toList();
        return ResponseEntity.ok(body);
    }

    @Override
    public ResponseEntity<Void> attachServerToProject(Long id, Long serverId) {
        service.attachServer(id, serverId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> detachServerFromProject(Long id, Long serverId) {
        service.detachServer(id, serverId);
        return ResponseEntity.noContent().build();
    }
}
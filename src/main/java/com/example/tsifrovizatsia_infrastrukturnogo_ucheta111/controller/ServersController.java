package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.controller;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ServerEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.ServerService;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.mapper.ServerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.example.infrastructure.web.api.ServersApi;
import ru.example.infrastructure.web.model.ServerRequest;
import ru.example.infrastructure.web.model.ServerResponse;
import ru.example.infrastructure.web.model.ServerSpecificationDto;
import ru.example.infrastructure.web.model.SpecificationUpdateRequest;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ServersController implements ServersApi {

    private final ServerService service;
    private final ServerMapper mapper;

    @Override
    public ResponseEntity<ServerResponse> createServer(ServerRequest serverRequest) {
        ServerEntity saved = service.create(serverRequest);
        return ResponseEntity.ok(mapper.toResponse(saved));
    }

    @Override
    public ResponseEntity<List<ServerResponse>> getAllServers() {
        List<ServerResponse> body = service.findAll().stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(body);
    }

    @Override
    public ResponseEntity<ServerResponse> getServerById(Long id) {
        ServerEntity server = service.getById(id);
        return ResponseEntity.ok(mapper.toResponse(server));
    }

    @Override
    public ResponseEntity<ServerSpecificationDto> getServerSpecification(Long id) {
        var internal = service.getSpecification(id);
        return ResponseEntity.ok(mapper.toGenerated(internal));
    }

    @Override
    public ResponseEntity<ServerResponse> updateServerSpecification(
            Long id, SpecificationUpdateRequest specificationUpdateRequest) {
        ServerEntity updated = service.updateSpecification(id, specificationUpdateRequest);
        return ResponseEntity.ok(mapper.toResponse(updated));
    }
}
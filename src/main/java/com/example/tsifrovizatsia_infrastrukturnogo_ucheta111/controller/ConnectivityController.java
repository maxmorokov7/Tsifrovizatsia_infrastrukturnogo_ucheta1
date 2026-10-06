package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.controller;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ConnectivityEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.ConnectivityService;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.mapper.ConnectivityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.example.infrastructure.web.api.ConnectivitiesApi;
import ru.example.infrastructure.web.model.ConnectivityRequest;
import ru.example.infrastructure.web.model.ConnectivityResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConnectivityController implements ConnectivitiesApi {
    private final ConnectivityService service;
    private final ConnectivityMapper mapper;

    @Override
    public ResponseEntity<ConnectivityResponse> createConnectivity(ConnectivityRequest request) {
        ConnectivityEntity saved = service.create(
                request,
                request.getSourceServerId(),
                request.getDestinationServerId()
        );
        return ResponseEntity.ok(mapper.toResponse(saved));
    }

    @Override
    public ResponseEntity<List<ConnectivityResponse>> getAllConnectivities() {
        List<ConnectivityResponse> body = service.findAll().stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(body);
    }

    @Override
    public ResponseEntity<ConnectivityResponse> getConnectivityById(Long id) {
        ConnectivityEntity entity = service.findById(id);
        return ResponseEntity.ok(mapper.toResponse(entity));
    }

    @Override
    public ResponseEntity<List<ConnectivityResponse>> getConnectivitiesBySource(Long serverId) {
        List<ConnectivityResponse> body = service.findBySource(serverId).stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(body);
    }

    @Override
    public ResponseEntity<List<ConnectivityResponse>> getConnectivitiesByDestination(Long serverId) {
        List<ConnectivityResponse> body = service.findByDestination(serverId).stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(body);
    }

    @Override
    public ResponseEntity<Void> deleteConnectivity(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

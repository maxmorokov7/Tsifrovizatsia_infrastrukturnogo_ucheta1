package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ConnectivityEntity;
import ru.example.infrastructure.web.model.ConnectivityRequest;

import java.util.List;

public interface ConnectivityService {

    ConnectivityEntity create(ConnectivityRequest request, Long sourceServerId, Long destinationServerId);

    List<ConnectivityEntity> findAll();

    ConnectivityEntity findById(Long id);

    List<ConnectivityEntity> findBySource(Long serverId);

    List<ConnectivityEntity> findByDestination(Long serverId);

    void delete(Long id);
}


package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ConnectivityEntity;

import java.util.List;

public interface ConnectivityService {

     ConnectivityEntity create(ConnectivityEntity connectivity, Long sourceServerId, Long destinationServerId);

    List<ConnectivityEntity> findBySource(Long serverId);

    List<ConnectivityEntity> findByDestination(Long serverId);

    void delete(Long id);
}

package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.repository;


import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ConnectivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConnectivityRepository extends JpaRepository<ConnectivityEntity, Long> {

    List<ConnectivityEntity> findAllBySourceServerId(Long sourceServerId);

    List<ConnectivityEntity> findAllByDestinationServerId(Long destinationServerId);
}
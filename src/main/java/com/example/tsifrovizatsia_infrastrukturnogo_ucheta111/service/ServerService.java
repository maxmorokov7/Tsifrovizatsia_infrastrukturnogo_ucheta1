package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.DTO.ServerSpecificationDto;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ServerEntity;

import java.util.List;

public interface ServerService {

    ServerEntity getById(Long id);

    List<ServerEntity> findAll();

    List<ServerEntity> findByProject(Long projectId);

    ServerEntity create(ServerEntity server);

    ServerSpecificationDto getSpecification(Long serverId);

}

package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.DTO.ServerSpecificationDto;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ServerEntity;
import ru.example.infrastructure.web.model.ServerRequest;
import ru.example.infrastructure.web.model.SpecificationUpdateRequest;

import java.util.List;

public interface ServerService {

    ServerEntity getById(Long id);

    List<ServerEntity> findAll();

    List<ServerEntity> findByProject(Long projectId);

    ServerEntity create(ServerRequest request);

    ServerEntity updateSpecification(Long serverId, SpecificationUpdateRequest request);

    ServerSpecificationDto getSpecification(Long serverId);

}

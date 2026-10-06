package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.mapper;


import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ProjectEntity;
import org.springframework.stereotype.Component;
import ru.example.infrastructure.web.model.ProjectResponse;

@Component
public class ProjectMapper {

    public ProjectResponse toResponse(ProjectEntity e) {
        ProjectResponse dto = new ProjectResponse();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setServerCount(e.getServers() != null ? e.getServers().size() : 0);
        return dto;
    }
}
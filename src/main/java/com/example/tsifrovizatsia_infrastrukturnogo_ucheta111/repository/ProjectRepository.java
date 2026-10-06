package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.repository;


import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    Optional<ProjectEntity> findByName(String name);

    @Query("""
            select distinct p from ProjectEntity p
            left join fetch p.servers
            where p.id = :id
            """)
    Optional<ProjectEntity> findByIdWithServers(@Param("id") Long id);
}
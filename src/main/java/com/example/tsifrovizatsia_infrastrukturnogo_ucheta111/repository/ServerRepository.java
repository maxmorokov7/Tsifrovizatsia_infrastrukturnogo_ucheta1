package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.repository;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ServerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ServerRepository extends JpaRepository<ServerEntity, Long> {

    Optional<ServerEntity> findByHostname(String hostname);

    List<ServerEntity> findAllByProjectId(Long projectId);

    @Query("""
            select distinct s from ServerEntity s
            left join fetch s.disks
            where s.id = :id
            """)
    Optional<ServerEntity> findByIdWithDisks(@Param("id") Long id);

    @Query("""
            select distinct s from ServerEntity s
            left join fetch s.disks
            left join fetch s.sourceConnectivities
            left join fetch s.destinationConnectivities
            where s.id = :id
            """)
    Optional<ServerEntity> findByIdWithAll(@Param("id") Long id);
}
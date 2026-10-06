package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.repository;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.DiskEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.enums.DiskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DiskRepository extends JpaRepository<DiskEntity, Long> {
    List<DiskEntity> findAllByServerId(Long serverId);

    long countByServerId(Long serverId);

    long countByServerIdAndType(Long serverId, DiskType type);

    Optional<DiskEntity> findByIdAndServerId(Long id, Long serverId);

    @Query("""
           select coalesce(sum(d.capacityGb), 0)
           from DiskEntity d
           where d.server.id = :serverId
           """)
    long sumCapacityByServerId(@Param("serverId") Long serverId);

    @Query("""
           select coalesce(sum(d.capacityGb), 0)
           from DiskEntity d
           where d.server.id = :serverId and d.type = :type
           """)
    long sumCapacityByServerIdAndType(@Param("serverId") Long serverId,
                                      @Param("type") DiskType type);
}
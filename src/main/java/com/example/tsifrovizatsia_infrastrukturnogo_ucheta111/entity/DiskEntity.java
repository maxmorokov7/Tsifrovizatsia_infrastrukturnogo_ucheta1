package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.enums.DiskType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "disks")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "server")
public class DiskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private DiskType type;

    @Column(name = "capacity_gb", nullable = false)
    private Long capacityGb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id", nullable = false)
    private ServerEntity server;
}
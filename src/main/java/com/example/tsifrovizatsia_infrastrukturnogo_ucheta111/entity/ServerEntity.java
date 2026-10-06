package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ServerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String hostname;

    @Column(nullable = false)
    private String ipAddress;

    private String role;
    private String environment;
    private String operatingSystem;

    @Column(name = "cpu_cores")
    private Integer cpuCores;

    @Column(name = "ram_gb")
    private Integer ramGb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @OneToMany(
            mappedBy = "server",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<DiskEntity> disks = new ArrayList<>();

    @OneToMany(mappedBy = "sourceServer")
    @Builder.Default
    private List<ConnectivityEntity> sourceConnectivities = new ArrayList<>();

    @OneToMany(mappedBy = "destinationServer")
    @Builder.Default
    private List<ConnectivityEntity> destinationConnectivities = new ArrayList<>();
}
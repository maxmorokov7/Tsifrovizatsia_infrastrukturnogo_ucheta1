package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.enums.AccessExpirationType;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.enums.ConnectionDirection;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.enums.ConnectivityStatus;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.enums.ConnectivityType;
import jakarta.persistence.*;
import lombok.*;
import ru.example.infrastructure.web.model.ConnectivityRequest;

import java.time.LocalDate;

@Entity
@Table(name = "connectivities")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"sourceServer", "destinationServer"})
public class ConnectivityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConnectivityType type;

    private String service;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConnectionDirection direction;

    private String subnetName;

    @Column(name = "source_address")
    private String sourceAddress;

    @Column(name = "destination_address")
    private String destinationAddress;

    @Column(columnDefinition = "TEXT")
    private String protocolsAndPorts;

    @Column(columnDefinition = "TEXT")
    private String accessJustification;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccessExpirationType expirationType;

    private LocalDate expirationDate;

    private String requestNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConnectivityStatus status;

    @Column(columnDefinition = "TEXT")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_server_id")
    private ServerEntity sourceServer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_server_id")
    private ServerEntity destinationServer;

    public ConnectivityEntity(ConnectivityRequest connectivity) {

    }
}
package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "specifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = "disks")
public class SpecificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Количество ядер ЦП (vCPU) */
    @NotNull
    @Min(1)
    @Column(name = "cpu_cores", nullable = false)
    private Integer cpuCores;

    /** Объем ОЗУ (RAM) в ГБ */
    @NotNull
    @Min(1)
    @Column(name = "ram_gb", nullable = false)
    private Integer ramGb;

    /** Список подключенных дисков (N дисков) */
    @OneToMany(
            mappedBy = "specification",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<DiskEntity> disks = new ArrayList<>();

}
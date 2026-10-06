package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.controller;

import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.DiskEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.enums.DiskType;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.DiskService;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.mapper.DiskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.example.infrastructure.web.api.DisksApi;
import ru.example.infrastructure.web.model.DiskRequest;
import ru.example.infrastructure.web.model.DiskResponse;
import ru.example.infrastructure.web.model.DiskSummaryDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DisksController implements DisksApi {

    private final DiskService diskService;
    private final DiskMapper diskMapper;

    @Override
    public ResponseEntity<DiskResponse> addDiskToServer(Long id, DiskRequest diskRequest) {
        DiskType type = DiskType.valueOf(diskRequest.getType().name());
        DiskEntity saved = diskService.addDiskToServer(id, type, diskRequest.getCapacityGb());
        return ResponseEntity.ok(diskMapper.toResponse(saved));
    }

    @Override
    public ResponseEntity<Void> deleteServerDisk(Long id, Long diskId) {
        diskService.removeDiskFromServer(id, diskId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<DiskSummaryDto> getServerDiskSummary(Long id) {
        return ResponseEntity.ok(diskMapper.toGenerated(diskService.summarize(id)));
    }

    @Override
    public ResponseEntity<List<DiskResponse>> getServerDisks(Long id) {
        List<DiskResponse> body = diskService.getDisksByServer(id).stream()
                .map(diskMapper::toResponse)
                .toList();
        return ResponseEntity.ok(body);
    }
}
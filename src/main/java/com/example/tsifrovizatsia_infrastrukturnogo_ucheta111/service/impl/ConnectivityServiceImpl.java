package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.impl;


import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ConnectivityEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ServerEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.repository.ConnectivityRepository;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.repository.ServerRepository;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.ConnectivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ConnectivityServiceImpl implements ConnectivityService {

    private final ConnectivityRepository connectivityRepository;
    private final ServerRepository serverRepository;

    @Transactional
    public ConnectivityEntity create(ConnectivityEntity connectivity,
                               Long sourceServerId,
                               Long destinationServerId) {
        if (sourceServerId != null) {
            ServerEntity source = serverRepository.findById(sourceServerId)
                    .orElseThrow(() -> new IllegalArgumentException("Source server not found"));
            connectivity.setSourceServer(source);
        }
        if (destinationServerId != null) {
            ServerEntity dest = serverRepository.findById(destinationServerId)
                    .orElseThrow(() -> new IllegalArgumentException("Destination server not found"));
            connectivity.setDestinationServer(dest);
        }
        return connectivityRepository.save(connectivity);
    }

    @Transactional(readOnly = true)
    public List<ConnectivityEntity> findBySource(Long serverId) {
        return connectivityRepository.findAllBySourceServerId(serverId);
    }

    @Transactional(readOnly = true)
    public List<ConnectivityEntity> findByDestination(Long serverId) {
        return connectivityRepository.findAllByDestinationServerId(serverId);
    }

    @Transactional
    public void delete(Long id) {
        connectivityRepository.deleteById(id);
    }
}
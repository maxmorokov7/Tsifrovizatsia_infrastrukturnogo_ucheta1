package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.impl;


import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ConnectivityEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.ServerEntity;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.repository.ConnectivityRepository;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.repository.ServerRepository;
import com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.service.ConnectivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.infrastructure.web.model.ConnectivityRequest;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ConnectivityServiceImpl implements ConnectivityService {
    private final ConnectivityRepository connectivityRepository;
    private final ServerRepository serverRepository;

    @Override
    @Transactional
    public ConnectivityEntity create(ConnectivityRequest request,
                                     Long sourceServerId,
                                     Long destinationServerId) {

        ConnectivityEntity entity = new ConnectivityEntity(request);

        if (sourceServerId != null) {
            ServerEntity source = serverRepository.findById(sourceServerId)
                    .orElseThrow(() -> new IllegalArgumentException("Source server not found: " + sourceServerId));
            entity.setSourceServer(source);
        }
        if (destinationServerId != null) {
            ServerEntity dest = serverRepository.findById(destinationServerId)
                    .orElseThrow(() -> new IllegalArgumentException("Destination server not found: " + destinationServerId));
            entity.setDestinationServer(dest);
        }

        return connectivityRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConnectivityEntity> findAll() {
        return connectivityRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ConnectivityEntity findById(Long id) {
        return connectivityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Connectivity not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConnectivityEntity> findBySource(Long serverId) {
        return connectivityRepository.findAllBySourceServerId(serverId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConnectivityEntity> findByDestination(Long serverId) {
        return connectivityRepository.findAllByDestinationServerId(serverId);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!connectivityRepository.existsById(id)) {
            throw new IllegalArgumentException("Connectivity not found: " + id);
        }
        connectivityRepository.deleteById(id);
    }
}

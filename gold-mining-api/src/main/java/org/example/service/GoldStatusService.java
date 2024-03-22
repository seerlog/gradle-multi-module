package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.goldStatus.GoldStatus;
import org.example.domain.goldStatus.repository.CustomGoldStatusRepo;
import org.example.domain.goldStatus.repository.GoldStatusRepo;
import org.example.request.GoldStatusRequest;
import org.example.response.GoldStatusResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoldStatusService {
    private final GoldStatusRepo goldStatusRepo;
    private final CustomGoldStatusRepo customGoldStatusRepo;

    public List<GoldStatusResponse> getGoldStatus(String name) {
        return customGoldStatusRepo.getGoldStatus(name);
    }

    public void saveGoldStatus(GoldStatusRequest goldStatusRequest) {
        goldStatusRepo.save(GoldStatus.of(goldStatusRequest));
    }
}

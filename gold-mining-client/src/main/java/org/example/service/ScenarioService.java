package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.company.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScenarioService {
    private final MineralMint mineralMint;
    private final NuggetVentures nuggetVentures;
    private final OreProsEnterprises oreProsEnterprises;
    private final TreasureVein treasureVein;

    public void miningForGold() {
        try {
            Thread.sleep(7000);
            mineralMint.startMining();
            nuggetVentures.startMining();
            oreProsEnterprises.startMining();
            treasureVein.startMining();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

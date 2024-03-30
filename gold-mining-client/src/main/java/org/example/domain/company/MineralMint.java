package org.example.domain.company;

import org.example.domain.miner.Miner;
import org.example.request.GoldMiningActVo;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MineralMint extends GoldMiningCompany {
    private WebClient webClient;

    public MineralMint(WebClient webClient) {
        this.name = "mineralMint";
        this.country = "australia";
        this.foundingDate = "1975-03-14";
        this.goldMinerCount = 5;
        this.goldReservesKilograms = 120;
        this.goldMiners = Executors.newFixedThreadPool(this.goldMinerCount);
        this.webClient = webClient;
    }

    @Override
    public void startMining() {
        ExecutorService executor = Executors.newFixedThreadPool(this.goldMinerCount);
        for (int i = 0; i < this.goldMinerCount; i++) {
            executor.execute(() -> {
                while (true) {
                    try {
                        Thread.sleep(1000);

                        synchronized (this) {
                            if(this.goldReservesKilograms < 1) {
                                break;
                            }

                            GoldMiningActVo goldMiningActVo = GoldMiningActVo.builder()
                                    .name(this.name)
                                    .country(this.country)
                                    .goldMiningKilograms(1)
                                    .goldReservesKilograms(this.goldReservesKilograms)
                                    .build();

                            this.goldReservesKilograms = Miner.act(goldMiningActVo, webClient);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        executor.shutdown();
    }

}

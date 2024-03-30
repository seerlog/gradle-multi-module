package org.example.domain.company;

import org.example.domain.miner.Miner;
import org.example.request.GoldMiningActVo;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class NuggetVentures extends GoldMiningCompany {
    private WebClient webClient;

    public NuggetVentures(WebClient webClient) {
        this.name = "nuggetVentures";
        this.country = "russia";
        this.foundingDate = "1987-06-22";
        this.goldMinerCount = 40;
        this.goldReservesKilograms = 1600;
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

                            this.goldReservesKilograms = Miner.act(GoldMiningActVo.of(this), webClient);
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

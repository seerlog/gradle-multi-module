package org.example.domain.company;

import org.example.domain.miner.Miner;
import org.example.request.GoldMiningActVo;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class OreProsEnterprises extends GoldMiningCompany {
    private WebClient webClient;

    public OreProsEnterprises(WebClient webClient) {
        this.name = "oreProsEnterprises";
        this.country = "usa";
        this.foundingDate = "1981-12-11";
        this.goldMinerCount = 70;
        this.goldReservesKilograms = 11000;
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
                        Thread.sleep(100);

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

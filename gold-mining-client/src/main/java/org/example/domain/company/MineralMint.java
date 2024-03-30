package org.example.domain.company;

import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MineralMint extends GoldMiningCompany {

    public MineralMint() {
        this.name = "mineralMint";
        this.country = "australia";
        this.foundingDate = "1975-03-14";
        this.goldMinerCount = 5;
        this.goldReservesKilograms = 120;
        goldMiners = Executors.newFixedThreadPool(this.goldMinerCount);
    }

    @Override
    public void startMining() {
        ExecutorService executor = Executors.newFixedThreadPool(this.goldMinerCount);
        for (int i = 0; i < this.goldMinerCount; i++) {
            executor.execute(() -> {
                while (0 < this.goldReservesKilograms) {
                    try {
                        Thread.sleep(1000);
                        int beforeMiningKilograms = this.goldReservesKilograms;
                        int afterMiningKilograms = this.goldReservesKilograms - 1;
                        this.goldReservesKilograms = afterMiningKilograms;
                        System.out.println(String.format("[%s] %d 광부가 금을 채굴함 %d → %d", name, Thread.currentThread().getId(), beforeMiningKilograms, afterMiningKilograms));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        executor.shutdown();
    }

}

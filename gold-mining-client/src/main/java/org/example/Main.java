package org.example;

import org.example.service.GoldMiningService;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(7000);
        GoldMiningService goldMiningService = new GoldMiningService();
        goldMiningService.goldMining();
    }
}

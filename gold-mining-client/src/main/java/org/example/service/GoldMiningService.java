package org.example.service;

import org.example.companies.GoldMiningCompany;

public class GoldMiningService {
    private GoldMiningCompany mineralMint;
    private GoldMiningCompany nuggetVentures;
    private GoldMiningCompany oreProsEnterprises;
    private GoldMiningCompany treasureVein;

    public GoldMiningService() {
        this.mineralMint = GoldMiningCompany.of("mineralMint", "australia", "1975-03-14", 5, 120);
        this.nuggetVentures = GoldMiningCompany.of("nuggetVentures", "russia", "1987-06-22", 4, 160);
        this.oreProsEnterprises = GoldMiningCompany.of("oreProsEnterprises", "usa", "1981-12-11", 7, 110);
        this.treasureVein = GoldMiningCompany.of("treasureVein", "peru", "1991-07-23", 12, 230);
    }

    public void goldMining() {
        this.mineralMint.startMining();
        this.nuggetVentures.startMining();
        this.oreProsEnterprises.startMining();
        this.treasureVein.startMining();
    }
}

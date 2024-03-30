package org.example.domain.company;

import lombok.Getter;

import java.util.concurrent.ExecutorService;

@Getter
public abstract class GoldMiningCompany {
    protected String name;
    protected String country;
    protected String foundingDate;
    protected int goldMinerCount;
    protected int goldReservesKilograms;
    protected ExecutorService goldMiners;

    abstract public void startMining();

}

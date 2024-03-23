package org.example.companies;

import lombok.Getter;
import lombok.Setter;
import org.example.request.GoldMiningRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class GoldMiningCompany {
    private String name;
    private String country;
    private String foundingDate;
    private int goldMinerCount;
    private AtomicInteger goldMineReservesKilograms;
    private WebClient webClient;

    public static GoldMiningCompany of(String name,
                                     String country,
                                     String foundingDate,
                                     int goldMinerCount,
                                     int goldMineReservesKilograms) {
        AtomicInteger atomicGoldMineReservesKilograms = new AtomicInteger();
        atomicGoldMineReservesKilograms.set(goldMineReservesKilograms);

        GoldMiningCompany goldMiningCompany = new GoldMiningCompany();
        goldMiningCompany.setName(name);
        goldMiningCompany.setCountry(country);
        goldMiningCompany.setFoundingDate(foundingDate);
        goldMiningCompany.setGoldMinerCount(goldMinerCount);
        goldMiningCompany.setGoldMineReservesKilograms(atomicGoldMineReservesKilograms);
        goldMiningCompany.setWebClient(WebClient
                .builder()
                .baseUrl("http://localhost:9999")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
        );

        return goldMiningCompany;
    }

    synchronized public void mining() {
        final int beforeMiningKilograms = this.goldMineReservesKilograms.get();
        final int afterMiningKilograms = this.goldMineReservesKilograms.decrementAndGet();
        if (afterMiningKilograms < 0) return;
        this.goldMineReservesKilograms.set(afterMiningKilograms);
        System.out.println(String.format("[%s] %d 광부가 금을 채굴함 %d → %d", name, Thread.currentThread().getId(), beforeMiningKilograms, afterMiningKilograms));

        GoldMiningRequest goldStatusRequest = GoldMiningRequest.builder()
                .name(this.getName())
                .country(this.getCountry())
                .goldMiningKilograms(1)
                .build();

        webClient.post()
                .uri("/gold-status")
                .body(Mono.just(goldStatusRequest), GoldMiningRequest.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void startMining() {
        ExecutorService executor = Executors.newFixedThreadPool(this.getGoldMinerCount());
        for (int i = 0; i < this.getGoldMinerCount(); i++) {
            executor.execute(() -> {
                while (0 < this.goldMineReservesKilograms.intValue()) {
                    try {
                        Thread.sleep(1000);
                        mining();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        executor.shutdown();
    }
}

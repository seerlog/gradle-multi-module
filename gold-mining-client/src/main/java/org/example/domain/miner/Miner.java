package org.example.domain.miner;

import org.example.request.GoldMiningRequest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class Miner extends Thread {
    private WebClient webClient;

    public Miner(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public void run() {
        try {
            GoldMiningRequest goldStatusRequest = GoldMiningRequest.builder()
                    .name("name")
                    .country("country")
                    .goldMiningKilograms(1)
                    .build();

            webClient.post()
                    .uri("/gold-status")
                    .body(Mono.just(goldStatusRequest), GoldMiningRequest.class)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package org.example.domain.miner;

import org.example.request.GoldMiningActVo;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class Miner {

    public static int act(GoldMiningActVo goldMiningActVo,
                          WebClient webClient) {
        int afterGoldReservesKilograms = goldMiningActVo.getGoldReservesKilograms() - goldMiningActVo.getGoldMiningKilograms();

        webClient.post()
                .uri("/gold-status")
                .body(Mono.just(goldMiningActVo), GoldMiningActVo.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        System.out.println(String.format("[%s] 광부가 금을 채굴함 %d → %d",
                goldMiningActVo.getName(),
                goldMiningActVo.getGoldReservesKilograms(),
                afterGoldReservesKilograms));

        return afterGoldReservesKilograms;
    }

}

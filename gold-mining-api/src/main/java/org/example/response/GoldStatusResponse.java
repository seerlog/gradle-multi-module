package org.example.response;

import lombok.*;
import org.example.domain.goldStatus.GoldStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoldStatusResponse {
    private String name;
    private String country;
    private int goldMiningKilograms;

    public static GoldStatusResponse of(GoldStatus goldStatus) {
        return GoldStatusResponse.builder()
                .name(goldStatus.getName())
                .country(goldStatus.getCountry())
                .goldMiningKilograms(goldStatus.getGoldMiningKilograms())
                .build();
    }
}

package org.example.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.example.domain.company.GoldMiningCompany;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoldMiningActVo {
    private String name;
    private String country;
    private int goldMiningKilograms;

    @JsonIgnore
    private int goldReservesKilograms;

    public static GoldMiningActVo of(GoldMiningCompany goldMiningCompany) {
        return GoldMiningActVo.builder()
                .name(goldMiningCompany.getName())
                .country(goldMiningCompany.getCountry())
                .goldMiningKilograms(1)
                .goldReservesKilograms(goldMiningCompany.getGoldReservesKilograms())
                .build();
    }
}

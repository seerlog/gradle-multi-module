package org.example.domain.goldStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.request.GoldStatusRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class GoldStatus {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "NO", nullable = false)
    private Long no;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "COUNTRY", nullable = false)
    private String country;

    @Column(name = "GOLD_MINING_KILOGRAMS", nullable = false)
    private int goldMiningKilograms;

    public static GoldStatus of(GoldStatusRequest goldStatusRequest) {
        return GoldStatus.builder()
                .name(goldStatusRequest.getName())
                .country(goldStatusRequest.getCountry())
                .goldMiningKilograms(goldStatusRequest.getGoldMiningKilograms())
                .build();
    }

}

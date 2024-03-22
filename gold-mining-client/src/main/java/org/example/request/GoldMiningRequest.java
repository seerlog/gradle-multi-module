package org.example.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoldMiningRequest {
    private String name;
    private String country;
    private int goldMiningKilograms;
}

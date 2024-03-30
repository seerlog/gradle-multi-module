package org.example.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
}

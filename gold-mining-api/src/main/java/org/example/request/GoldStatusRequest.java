package org.example.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoldStatusRequest {
    private String name;
    private String country;
    private int goldMiningKilograms;
}

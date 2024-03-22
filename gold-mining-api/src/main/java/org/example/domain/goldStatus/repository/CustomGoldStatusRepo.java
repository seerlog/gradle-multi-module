package org.example.domain.goldStatus.repository;

import org.example.response.GoldStatusResponse;

import java.util.List;

public interface CustomGoldStatusRepo {
    List<GoldStatusResponse> getGoldStatus(String name);
}

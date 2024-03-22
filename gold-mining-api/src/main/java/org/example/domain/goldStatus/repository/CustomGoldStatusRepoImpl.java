package org.example.domain.goldStatus.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.response.GoldStatusResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.domain.goldStatus.QGoldStatus.goldStatus;

@Repository
@RequiredArgsConstructor
public class CustomGoldStatusRepoImpl implements CustomGoldStatusRepo {
    private final JPAQueryFactory jpaQueryFactory;

    public List<GoldStatusResponse> getGoldStatus(String name) {
        return jpaQueryFactory
                .selectFrom(goldStatus)
                .where(goldStatus.name.eq(name))
                .fetch().stream().map(GoldStatusResponse::of)
                .collect(Collectors.toList());
    }
}

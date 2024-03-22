package org.example.domain.goldStatus.repository;

import org.example.domain.goldStatus.GoldStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoldStatusRepo extends JpaRepository<GoldStatus, Long> {
}

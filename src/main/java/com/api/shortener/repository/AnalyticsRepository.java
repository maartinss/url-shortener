package com.api.shortener.repository;

import com.api.shortener.entity.AnalyticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyticsRepository extends JpaRepository<AnalyticEntity, Long> {
}

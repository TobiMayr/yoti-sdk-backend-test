package com.tobimayr.hoover.repository;

import com.tobimayr.hoover.model.HooverResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<HooverResult, Long> {
}
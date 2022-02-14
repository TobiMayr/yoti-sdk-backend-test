package com.tobimayr.hoover.repository;

import com.tobimayr.hoover.model.HooverInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputRepository extends JpaRepository<HooverInput, Long> {
}
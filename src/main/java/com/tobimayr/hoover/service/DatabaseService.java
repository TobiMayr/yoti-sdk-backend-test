package com.tobimayr.hoover.service;

import com.tobimayr.hoover.model.HooverInput;
import com.tobimayr.hoover.repository.InputRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatabaseService {

    private final InputRepository inputRepository;

    public void saveInput(HooverInput hooverInput) {
        inputRepository.save(hooverInput);
    }
}

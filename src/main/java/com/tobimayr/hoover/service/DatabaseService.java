package com.tobimayr.hoover.service;

import com.tobimayr.hoover.model.HooverInput;
import com.tobimayr.hoover.model.HooverResult;
import com.tobimayr.hoover.repository.InputRepository;
import com.tobimayr.hoover.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatabaseService {

    private final InputRepository inputRepository;
    private final ResultRepository resultRepository;

    public void saveInput(HooverInput hooverInput) {
        inputRepository.save(hooverInput);
    }

    public void saveResult(HooverResult hooverResult) {
        resultRepository.save(hooverResult);
    }
}

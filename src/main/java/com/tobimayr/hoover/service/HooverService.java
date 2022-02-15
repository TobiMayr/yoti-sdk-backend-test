package com.tobimayr.hoover.service;

import com.tobimayr.hoover.dto.HooverResultDto;
import com.tobimayr.hoover.enums.Direction;
import com.tobimayr.hoover.model.HooverInput;
import com.tobimayr.hoover.model.HooverResult;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class HooverService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DatabaseService databaseService;

    public HooverResultDto start(HooverInput hooverInput) {

        databaseService.saveInput(hooverInput);

        Point currentPosition = hooverInput.getCoords();

        if (!hooverInput.getRoom().contains(currentPosition)) {
            throw new IllegalArgumentException("Coords are invalid. Outside of the room");
        }

        int patchesCount = 0;
        for (Direction direction : hooverInput.getDirections()) {
            patchesCount += hooverCurrentPosition(hooverInput.getPatches(), currentPosition);

            movePosition(direction, currentPosition, hooverInput.getRoom());
        }

        HooverResult hooverResult = new HooverResult();
        hooverResult.setCoords(currentPosition);
        hooverResult.setPatches(patchesCount);
        hooverResult.setHooverInput(hooverInput);

        databaseService.saveResult(hooverResult);

        return modelMapper.map(hooverResult, HooverResultDto.class);
    }

    private void movePosition(Direction direction, Point currentPosition, Rectangle room) {

        switch (direction) {
            case N -> {
                log.info("Going North");
                currentPosition.translate(0, 1);
                if (!room.contains(currentPosition)) {
                    currentPosition.translate(0, -1);
                }
            }
            case E -> {
                log.info("Going East");
                currentPosition.translate(1, 0);
                if (!room.contains(currentPosition)) {
                    currentPosition.translate(-1, 0);
                }
            }
            case S -> {
                log.info("Going South");
                currentPosition.translate(0, -1);
                if (!room.contains(currentPosition)) {
                    currentPosition.translate(0, 1);
                }
            }
            case W -> {
                log.info("Going West");
                currentPosition.translate(-1, 0);
                if (!room.contains(currentPosition)) {
                    currentPosition.translate(1, 0);
                }
            }
        }
    }

    private int hooverCurrentPosition(ArrayList<Point> patches, Point currentPosition) {
        for (Point patch : patches) {
            if (currentPosition.equals(patch)) {
                patches.remove(patch);
                return 1;
            }
        }

        return 0;
    }
}

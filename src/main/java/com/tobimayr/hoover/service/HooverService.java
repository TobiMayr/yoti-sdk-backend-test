package com.tobimayr.hoover.service;

import com.tobimayr.hoover.dto.HooverResultDto;
import com.tobimayr.hoover.enums.Direction;
import com.tobimayr.hoover.model.HooverInput;
import com.tobimayr.hoover.model.HooverResult;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;

@Slf4j
@Service
public class HooverService {

    private ModelMapper modelMapper = new ModelMapper();

    public HooverResultDto start(HooverInput hooverInput) {
        HooverResult hooverResult = new HooverResult();
        Point currentPosition = hooverInput.getCoords();

        if (!hooverInput.getRoom().contains(currentPosition)) {
            throw new IllegalArgumentException("Coords are invalid. Outside of the room");
        }

        int patchesCount = 0;
        for (Direction direction : hooverInput.getDirections()) {
            patchesCount += move(direction, currentPosition, hooverInput.getRoom(), hooverInput.getPatches());
        }

        hooverResult.setCoords(currentPosition);
        hooverResult.setPatches(patchesCount);
        return modelMapper.map(hooverResult, HooverResultDto.class);
    }

    private int move(Direction direction, Point currentPosition, Rectangle room, ArrayList<Point> patches) {

        switch (direction) {
            case N:
                log.info("Going North");
                currentPosition.translate(0, 1);
                if (!room.contains(currentPosition)) {
                    currentPosition.translate(0, -1);
                }
                break;
            case E:
                log.info("Going East");
                currentPosition.translate(1, 0);
                if (!room.contains(currentPosition)) {
                    currentPosition.translate(-1, 0);
                }
                break;
            case S:
                log.info("Going South");
                currentPosition.translate(0, -1);
                if (!room.contains(currentPosition)) {
                    currentPosition.translate(0, 1);
                }
                break;
            case W:
                log.info("Going West");
                currentPosition.translate(-1, 0);
                if (!room.contains(currentPosition)) {
                    currentPosition.translate(1, 0);
                }
                break;
        }
        for (Point patch: patches) {
            if (currentPosition.equals(patch)){
                patches.remove(patch);
                return 1;
            }
        }

        return 0;
    }
}

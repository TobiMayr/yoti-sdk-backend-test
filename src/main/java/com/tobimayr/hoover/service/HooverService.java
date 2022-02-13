package com.tobimayr.hoover.service;

import com.tobimayr.hoover.model.HooverInput;
import com.tobimayr.hoover.model.HooverResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;

@Slf4j
@Service
public class HooverService {

    public HooverResult start(HooverInput hooverInput) {

        // if (!isHooverInRoom(hooverInput.getCoords(), hooverInput.getRoomSize()))


        // use instructions to navigate room

        HooverResult hooverResult = new HooverResult();
        hooverResult.setCoords(new int[]{1, 3});
        hooverResult.setPatches(1);
        return hooverResult;
    }

/*    private boolean isHooverInRoom(Point coords, Point roomSize) {
        if (coords.getX() <= roomSize.getX() && coords.getY() <= roomSize.getY())
            return true;
        return false;
    }*/
}

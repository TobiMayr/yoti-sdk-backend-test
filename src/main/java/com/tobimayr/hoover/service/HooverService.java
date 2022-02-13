package com.tobimayr.hoover.service;

import com.tobimayr.hoover.model.HooverInput;
import com.tobimayr.hoover.model.HooverResult;

public class HooverService {

    public HooverResult start(HooverInput hooverInput) {
        /*
        Do stuff
         */
        HooverResult hooverResult = new HooverResult();
        hooverResult.setCoords(new int[]{1, 3});
        hooverResult.setPatches(1);
        return hooverResult;
    }
}

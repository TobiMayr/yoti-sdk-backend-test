package com.tobimayr.hoover.dto;

import com.tobimayr.hoover.model.HooverResult;
import lombok.Data;

import java.awt.*;

@Data
public class HooverResultDto {

    int[] coords;

    int patches;

    public HooverResult convertToEntity() {
        HooverResult hooverResult = new HooverResult();
        hooverResult.setPatches(patches);
        hooverResult.setCoords(new Point(coords[0], coords[1]));
        return hooverResult;
    }
}
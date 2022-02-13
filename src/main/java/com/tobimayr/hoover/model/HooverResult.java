package com.tobimayr.hoover.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tobimayr.hoover.dto.HooverResultDto;
import lombok.Data;

import java.awt.*;
import java.io.Serializable;

@Data
public class HooverResult implements Serializable {

    Point coords;

    int patches;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String error;

    public HooverResultDto convertToDto() {
        HooverResultDto hooverResultDto = new HooverResultDto();
        hooverResultDto.setPatches(patches);
        int x = (int) coords.getX();
        int y = (int) coords.getY();
        int[] coords = new int[]{x, y};
        hooverResultDto.setCoords(coords);
        return hooverResultDto;
    }
}

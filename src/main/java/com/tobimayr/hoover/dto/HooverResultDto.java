package com.tobimayr.hoover.dto;

import com.tobimayr.hoover.model.HooverResult;
import lombok.Data;

import java.awt.*;

@Data
public class HooverResultDto {

    int[] coords;

    int patches;

    public void setCoords(Point coords) {
        int x = (int) coords.getX();
        int y = (int) coords.getY();
        this.coords = new int[]{x, y};
    }
}
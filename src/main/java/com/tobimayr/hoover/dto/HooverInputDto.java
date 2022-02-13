package com.tobimayr.hoover.dto;

import com.tobimayr.hoover.model.HooverInput;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;

@Data
public class HooverInputDto {

    private int[] roomSize;

    private int[] coords;

    private int[][] patches;

    private String instructions;

    public HooverInput convertToEntity() {
        HooverInput hooverInput = new HooverInput();
        hooverInput.setRoomSize(new Point(roomSize[0], roomSize[1]));
        hooverInput.setCoords(new Point(coords[0], coords[1]));
        hooverInput.setPatches(getPatches());
        hooverInput.setInstructions(instructions);
        return hooverInput;
    }

    private ArrayList<Point> getPatches() {
        ArrayList<Point> patchesList = new ArrayList<>();
        for (int i = 0; i < patches.length; i++) {
            patchesList.add(new Point(patches[i][0], patches[i][1]));
        }
        return patchesList;
    }

}
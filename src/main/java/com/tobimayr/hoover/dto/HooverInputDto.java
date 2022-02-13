package com.tobimayr.hoover.dto;

import com.tobimayr.hoover.enums.Direction;
import com.tobimayr.hoover.model.HooverInput;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.awt.*;
import java.util.ArrayList;

@Data
public class HooverInputDto {

    @NotNull(message = "Attribute missing: roomSize")
    private int[] roomSize;

    @NotNull(message = "Attribute missing: coords")
    private int[] coords;

    @NotNull(message = "Attribute missing: patches")
    private int[][] patches;

    @NotNull(message = "Attribute missing: instructions")
    @Pattern(regexp = "^[NSWEnswe]*$", message = "Instructions are invalid. Allowed: 'N' 'E' 'S' 'W' (upper or lower)")
    private String instructions;

    public HooverInput convertToEntity() {
        HooverInput hooverInput = new HooverInput();
        hooverInput.setRoom(new Rectangle(roomSize[0], roomSize[1]));
        hooverInput.setCoords(new Point(coords[0], coords[1]));
        hooverInput.setPatches(getPatches());
        hooverInput.setInstructions(getDirections());
        return hooverInput;
    }

    private ArrayList<Point> getPatches() {
        ArrayList<Point> patchesList = new ArrayList<>();
        for (int i = 0; i < patches.length; i++) {
            patchesList.add(new Point(patches[i][0], patches[i][1]));
        }
        return patchesList;
    }

    private ArrayList<Direction> getDirections() {
        ArrayList<Direction> directionList = new ArrayList<>();
        for (String direction : instructions.split("")) {
            directionList.add(Direction.valueOf(direction));
        }
        return directionList;
    }
}
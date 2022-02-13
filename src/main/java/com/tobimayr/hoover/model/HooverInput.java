package com.tobimayr.hoover.model;

import com.tobimayr.hoover.enums.Direction;
import lombok.Data;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

@Data
public class HooverInput implements Serializable {

    private Rectangle room;

    private Point coords;

    private ArrayList<Point> patches;

    private ArrayList<Direction> instructions;
}
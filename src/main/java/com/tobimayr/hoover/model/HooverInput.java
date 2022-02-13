package com.tobimayr.hoover.model;

import lombok.Data;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

@Data
public class HooverInput implements Serializable {

    private Point roomSize;

    private Point coords;

    private ArrayList<Point> patches;

    private String instructions;
}
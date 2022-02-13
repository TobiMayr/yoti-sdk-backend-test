package com.tobimayr.hoover.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class HooverInput implements Serializable {

    private int[] roomSize;

    private int[] chords;

    private int[][] patches;

    private String instructions;
}
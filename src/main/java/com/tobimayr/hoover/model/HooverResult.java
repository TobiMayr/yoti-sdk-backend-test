package com.tobimayr.hoover.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class HooverResult implements Serializable {

    int[] coords;
    int patches;
}

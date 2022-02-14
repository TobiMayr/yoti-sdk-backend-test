package com.tobimayr.hoover.model;

import com.tobimayr.hoover.converter.DirectionConverter;
import com.tobimayr.hoover.converter.PatchesConverter;
import com.tobimayr.hoover.enums.Direction;
import lombok.Data;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

@Data
@Entity
public class HooverInput implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Rectangle room;

    private Point coords;

    @Convert(converter = PatchesConverter.class)
    private ArrayList<Point> patches;

    @Convert(converter = DirectionConverter.class)
    private ArrayList<Direction> directions;

}
package com.tobimayr.hoover.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tobimayr.hoover.converter.CoordsConverter;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class HooverResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CoordsConverter.class)
    Point coords;

    int patches;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String error;

    public int[] getCoords() {
        int x = (int) coords.getX();
        int y = (int) coords.getY();
        return new int[]{x, y};
    }
}

package com.tobimayr.hoover.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tobimayr.hoover.converter.CoordsConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Table(name = "result")
@Entity
public class HooverResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "input_id", referencedColumnName = "id")
    private HooverInput hooverInput;

    @Column(name = "end_position")
    @Convert(converter = CoordsConverter.class)
    Point coords;

    @Column(name = "cleaned_patches")
    int patches;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String error;

    public int[] getCoords() {
        int x = (int) coords.getX();
        int y = (int) coords.getY();
        return new int[]{x, y};
    }
}

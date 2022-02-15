package com.tobimayr.hoover.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.awt.*;

@Converter
public class CoordsConverter implements AttributeConverter<Point, String> {
    @Override
    public String convertToDatabaseColumn(Point point) {
        return String.format("%s:%s", (int) point.getX(), (int) point.getY());
    }

    @Override
    public Point convertToEntityAttribute(String s) {
        String[] pointArray = s.split(":");
        return new Point(Integer.parseInt(pointArray[0]), Integer.parseInt(pointArray[1]));
    }
}

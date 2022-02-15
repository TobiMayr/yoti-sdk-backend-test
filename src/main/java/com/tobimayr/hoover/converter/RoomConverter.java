package com.tobimayr.hoover.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.awt.*;

@Converter
public class RoomConverter implements AttributeConverter<Rectangle, String> {
    @Override
    public String convertToDatabaseColumn(Rectangle rectangle) {
        return String.format("%s:%s", rectangle.width, rectangle.height);
    }

    @Override
    public Rectangle convertToEntityAttribute(String s) {
        String[] pointArray = s.split(":");
        return new Rectangle(Integer.parseInt(pointArray[0]), Integer.parseInt(pointArray[1]));
    }
}

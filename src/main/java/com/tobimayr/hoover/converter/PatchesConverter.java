package com.tobimayr.hoover.converter;

import javax.persistence.AttributeConverter;
import java.awt.*;
import java.util.ArrayList;

public class PatchesConverter implements AttributeConverter<ArrayList<Point>, String> {


    @Override
    public String convertToDatabaseColumn(ArrayList<Point> points) {
        StringBuilder resultStringBuilder = new StringBuilder();
        points.forEach(resultStringBuilder::append);
        return resultStringBuilder.toString();
    }

    @Override
    public ArrayList<Point> convertToEntityAttribute(String s) {
        return null;
    }
}

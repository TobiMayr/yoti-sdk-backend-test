package com.tobimayr.hoover.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.awt.*;
import java.util.ArrayList;

@Converter
public class PatchesConverter implements AttributeConverter<ArrayList<Point>, String> {


    @Override
    public String convertToDatabaseColumn(ArrayList<Point> points) {
        StringBuilder resultStringBuilder = new StringBuilder();
        for (Point point : points) {
            resultStringBuilder.append(String.format("%s:%s,", point.x, point.y));
        }
        String resultString = resultStringBuilder.toString();
        return resultString.substring(0, resultString.length() - 1);
    }

    @Override
    public ArrayList<Point> convertToEntityAttribute(String s) {
        ArrayList<Point> patchesList = new ArrayList<>();
        for (String pointString : s.split(",")) {
            String[] pointArray = pointString.split(":");
            Point point = new Point(Integer.parseInt(pointArray[0]), Integer.parseInt(pointArray[1]));
            patchesList.add(point);

        }
        return patchesList;
    }
}

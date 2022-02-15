package com.tobimayr.hoover.converter;

import com.tobimayr.hoover.enums.Direction;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;

@Converter
public class DirectionConverter implements AttributeConverter<ArrayList<Direction>, String> {


    @Override
    public String convertToDatabaseColumn(ArrayList<Direction> directions) {
        StringBuilder resultStringBuilder = new StringBuilder();
        directions.forEach(resultStringBuilder::append);
        return resultStringBuilder.toString();
    }

    @Override
    public ArrayList<Direction> convertToEntityAttribute(String directionsString) {
        ArrayList<Direction> directionList = new ArrayList<>();
        for (String direction : directionsString.split("")) {
            directionList.add(Direction.valueOf(direction));
        }
        return directionList;
    }

}

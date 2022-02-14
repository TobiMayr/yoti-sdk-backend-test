package com.tobimayr.hoover.converter;

import com.tobimayr.hoover.enums.Direction;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;


public class DirectionConverter implements AttributeConverter<ArrayList<Direction>, String> {


    @Override
    public String convertToDatabaseColumn(ArrayList<Direction> directions) {
        StringBuilder resultStringBuilder = new StringBuilder();
        directions.forEach(resultStringBuilder::append);
        return resultStringBuilder.toString();
    }

    @Override
    public ArrayList<Direction> convertToEntityAttribute(String s) {
        return null;
    }

}

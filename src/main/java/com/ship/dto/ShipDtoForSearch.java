package com.ship.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ShipDtoForSearch {
    private String name;
    private String planet;
    private String ship_type;
    private String minRating;
    private String maxRating;
    private String minCrewSize;
    private String maxCrewSize;
    private String minSpeed;
    private String maxSpeed;
    private String after;
    private String before;

    public Map<String, String> paramToMap(){
        Map<String, String> param = new HashMap<>();
        param.put("name", name);
        param.put("planet", planet);
        param.put("ship_type", ship_type);
        param.put("minRating", minRating);
        param.put("maxRating", maxRating);
        param.put("minCrewSize", minCrewSize);
        param.put("maxCrewSize", maxCrewSize);
        param.put("minSpeed", minSpeed);
        param.put("maxSpeed", maxSpeed);
        param.put("after", after);
        param.put("before", before);
        return param;
    }

    @Override
    public String toString() {
        return "ShipDtoForSearch{" +
                "name='" + name + '\'' +
                ", planet='" + planet + '\'' +
                ", ship_type='" + ship_type + '\'' +
                ", minRating='" + minRating + '\'' +
                ", maxRating='" + maxRating + '\'' +
                ", minCrewSize='" + minCrewSize + '\'' +
                ", maxCrewSize='" + maxCrewSize + '\'' +
                ", minSpeed='" + minSpeed + '\'' +
                ", maxSpeed='" + maxSpeed + '\'' +
                ", after='" + after + '\'' +
                ", before='" + before + '\'' +
                '}';
    }
}

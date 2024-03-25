package com.example.movie.resources.enums;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class ShowTimesDeserializer extends JsonDeserializer<ShowTimes> {
    @Override
    public ShowTimes deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int ordinalValue = p.getIntValue();
        for (ShowTimes showTime : ShowTimes.values()) {
            if (showTime.getTime() == ordinalValue) {
                return showTime;
            }
        }
        throw new IllegalArgumentException("Unknown ordinal value: " + ordinalValue);
    }
}

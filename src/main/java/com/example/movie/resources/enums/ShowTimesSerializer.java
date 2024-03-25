package com.example.movie.resources.enums;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ShowTimesSerializer extends JsonSerializer<ShowTimes> {
    @Override
    public void serialize(ShowTimes value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(value.getTime());
    }
    
}
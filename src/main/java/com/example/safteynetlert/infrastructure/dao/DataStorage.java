package com.example.safteynetlert.infrastructure.dao;

import com.example.safteynetlert.infrastructure.models.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class DataStorage {
    private final Data data;
    private final String DATA_FILE = "data.json";

    public DataStorage() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new ClassPathResource(DATA_FILE).getFile();
        this.data = objectMapper.readValue(file, Data.class);
    }

    public Data getData() {
        return data;
    }
}

package com.example.safteynetlert.infrastructure.dao;

import com.example.safteynetlert.infrastructure.models.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class DataStorage {
    private final Data data;

    @Value("${safe-net-alert.data-file}")
    private String dataFile = "data.json";

    public DataStorage() throws IOException {

        if (StringUtils.isEmpty(dataFile)) {
            throw new IllegalArgumentException(
                    "safe-net-alert.data-file cant be null or " +
                            "empty");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new ClassPathResource(this.dataFile).getFile();
        this.data = objectMapper.readValue(file, Data.class);
    }

    public Data getData() {
        return data;
    }
}

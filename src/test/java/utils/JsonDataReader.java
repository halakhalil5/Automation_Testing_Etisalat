package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class JsonDataReader {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode readJson(String fileName) {
        try {
            // 1. Try to load from classpath (resources folder)
            InputStream inputStream = JsonDataReader.class.getClassLoader().getResourceAsStream(fileName);
            if (inputStream != null) {
                return objectMapper.readTree(inputStream);
            }

            // 2. Fallback: try filesystem path (relative to project root)
            File file = new File(fileName);
            if (file.exists()) {
                return objectMapper.readTree(file);
            }

            throw new RuntimeException("❌ JSON file not found in classpath or filesystem: " + fileName);

        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to read JSON: " + fileName, e);
        }
    }
}

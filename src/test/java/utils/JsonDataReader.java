package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonDataReader {

    public static JsonNode readJson(String fileName) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        InputStream is = JsonDataReader.class.getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            throw new RuntimeException("❌ JSON file not found: " + fileName);
        }

        return mapper.readTree(is);
    }
}

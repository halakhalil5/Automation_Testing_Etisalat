package utils;

import java.io.File;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonDataReader {
    public static JsonNode readJson(String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(new File(filePath));
    }
}
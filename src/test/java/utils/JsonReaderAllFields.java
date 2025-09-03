package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

public class JsonReaderAllFields {

    private static final ObjectMapper mapper = new ObjectMapper();

    // Load JSON file from resources
    public static JsonNode readJson(String fileName) throws Exception {
        InputStream is = JsonReaderAllFields.class.getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            throw new RuntimeException("❌ JSON file not found: " + fileName);
        }
        return mapper.readTree(is);
    }

    // Print all JSON fields recursively (for debugging)
    public static void printJsonRecursive(JsonNode node, String indent) {
        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                System.out.println(indent + field.getKey() + ":");
                printJsonRecursive(field.getValue(), indent + "  ");
            }
        } else if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                System.out.println(indent + "[" + i + "]");
                printJsonRecursive(node.get(i), indent + "  ");
            }
        } else {
            System.out.println(indent + node.asText());
        }
    }
}

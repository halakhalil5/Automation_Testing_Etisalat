package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

public class JsonReaderAllFields {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Load JSON file from resources
        InputStream is = JsonReaderAllFields.class.getClassLoader().getResourceAsStream("Data.json");
        if (is == null) {
            throw new RuntimeException("❌ JSON file not found!");
        }

        JsonNode root = mapper.readTree(is);

        // Print the entire JSON recursively
        printJsonRecursive(root, "");
    }

    // Recursive method to print all fields dynamically
    private static void printJsonRecursive(JsonNode node, String indent) {
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

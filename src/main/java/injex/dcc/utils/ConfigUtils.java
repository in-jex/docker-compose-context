package injex.dcc.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConfigUtils {


    public static List<String> loadConfigList() throws IOException {
        try {
            return Files.readAllLines(FileUtils.getContextPath());
        } catch (NoSuchFileException nsfe) {
            return new ArrayList<>();
        }

    }
    public static Map<String, String> loadConfig() throws IOException {
        return loadConfigList()
                .stream().map(it -> it.split("="))
                .collect(Collectors.toMap(it -> it[0], it -> it[1]));
    }
    public static void writeConfig(List<String> configs) {
        try {
            Files.writeString(FileUtils.getContextPath(), String.join("\n", configs), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

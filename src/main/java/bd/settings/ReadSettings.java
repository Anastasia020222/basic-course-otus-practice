package bd.settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadSettings implements IReadSettings {

    @Override
    public Map<String, String> getSettings() {
        Properties properties = new Properties();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("SQLSettings.properties");

        Map<String, String> mapProperties = new HashMap<>();
        try {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new RuntimeException("Файл настроек SQLSettings.properties не найден.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла настроек.", e);
        }

        for (String key : properties.stringPropertyNames()) {
            mapProperties.put(key, properties.getProperty(key));
        }
        return mapProperties;
    }
}

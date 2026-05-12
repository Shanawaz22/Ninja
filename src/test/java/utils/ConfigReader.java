
package utils;
import java.io.FileInputStream;
import java.util.Properties;
public class ConfigReader {
    static Properties prop = new Properties();
    public static void load() {
        try {
            FileInputStream f = new FileInputStream("src/test/resources/config.properties");
            prop.load(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String get(String key) {
        return prop.getProperty(key);
    }

}
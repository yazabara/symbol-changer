package yazabara.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yaroslav Zabara
 */
@ConfigurationProperties("root")
public class RootProperties {

    private String path;

    private Map<String, String> alphabet = new HashMap<>();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(Map<String, String> alphabet) {
        this.alphabet = alphabet;
    }
}

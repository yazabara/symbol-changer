package yazabara.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author Yaroslav Zabara
 */
@Configuration
@EnableConfigurationProperties({RootProperties.class})
public class RootConfig {

    public String getPath(RootProperties properties) {
        return properties.getPath();
    }

    public Map<String, String> getAlphabet(RootProperties properties) {
        return properties.getAlphabet();
    }
}

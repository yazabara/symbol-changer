package yazabara;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import yazabara.config.RootConfig;
import yazabara.services.IFileNameReplacer;

/**
 * @author Yaroslav Zabara
 */
@ComponentScan
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    private RootConfig config;

    @Autowired
    private IFileNameReplacer fileNameReplacer;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fileNameReplacer.replaceNames();
    }
}

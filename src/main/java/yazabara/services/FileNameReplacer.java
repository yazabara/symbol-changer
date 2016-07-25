package yazabara.services;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yazabara.AlphabetUtils;
import yazabara.config.RootProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yaroslav Zabara
 */
@Service
public class FileNameReplacer implements IFileNameReplacer {

    private static String EXTENSION_SEPARATOR = ".";

    private static final Logger LOGGER = LoggerFactory.getLogger(FileNameReplacer.class);

    @Autowired
    private RootProperties properties;

    public void replaceNames() throws IOException {
        replaceDirectoriesNames();
        replaceFilesNames();
    }

    private void replaceDirectoriesNames() throws IOException {
        List<Path> paths = Files.walk(Paths.get(properties.getPath())).filter(Files::isDirectory).collect(Collectors.toList());
        Collections.reverse(paths);
        paths.forEach(this::renameFile);
    }

    private void replaceFilesNames() throws IOException {
        List<Path> paths = Files.walk(Paths.get(properties.getPath())).filter(Files::isRegularFile).collect(Collectors.toList());
        Collections.reverse(paths);
        paths.forEach(this::renameFile);
    }

    private void renameFile(Path path) {
        String fileName = FilenameUtils.getBaseName(path.toString());
        String ext = FilenameUtils.getExtension(path.toString());
        String pathToFile = FilenameUtils.getFullPath(path.toString());
        String newFileName = AlphabetUtils.replace(fileName, properties.getAlphabet());

        if (StringUtils.equals(fileName, newFileName)) {
            return;
        }
        try {
            Files.move(path, path.resolveSibling(pathToFile + newFileName + EXTENSION_SEPARATOR + ext), StandardCopyOption.REPLACE_EXISTING);
            LOGGER.debug(pathToFile + fileName + " --> " + pathToFile + newFileName + EXTENSION_SEPARATOR + ext);
        } catch (IOException e) {
            LOGGER.error("Unable to rename directory/file: ", e);
        }
    }

}

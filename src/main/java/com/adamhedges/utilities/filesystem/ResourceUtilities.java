package com.adamhedges.utilities.filesystem;

import java.net.URL;
import java.nio.file.Path;
import java.util.Objects;

public class ResourceUtilities {

    public static String getResourceFilePath(String rootPath, String filename) {
        URL resourceFileUrl = Thread.currentThread().getContextClassLoader().getResource(filename);
        if (!Objects.isNull(resourceFileUrl)) {
            return resourceFileUrl.getPath();
        }

        return Path.of(rootPath, filename).toString();
    }

}

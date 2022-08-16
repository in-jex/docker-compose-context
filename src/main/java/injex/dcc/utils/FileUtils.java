package injex.dcc.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    private FileUtils() {

    }

    public static String getUserRootDir() {
        return System.getProperty("user.home");
    }

    public static Path getPathRelative(String path) {
        return Paths.get(System.getProperty("user.dir"), path).normalize();
    }

    public static Path getUserRootDirPath() {
        return Paths.get(getUserRootDir());
    }

    public static Path getContextPath() {
        return Paths.get(getUserRootDir(), ".docker-compose-context");
    }

}

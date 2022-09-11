package injex.dcc.command.down;

import injex.dcc.utils.ConfigUtils;
import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(name = "all",
        mixinStandardHelpOptions = true,
        description = "Stop all of your registered docker-compose instances"
)
public class DownAllCommand implements Runnable {
    @Override
    public void run() {
        try {
            ConfigUtils.loadConfig().values().stream().parallel().forEach(this::runDown);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int runDown(String path) {
        try {
            ProcessBuilder pb = new ProcessBuilder("docker-compose", "-f", path, "down");
            pb.inheritIO();
            Process process = pb.start();
            return process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return 0;
        }
    }
}

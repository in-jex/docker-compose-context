package injex.dcc.command;

import injex.dcc.utils.ConfigUtils;
import picocli.CommandLine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CommandLine.Command(name = "edit",
        mixinStandardHelpOptions = true,
        description = "Edit your docker-compose without looking for a file"
)
public class EditCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<service_name>",
            description = "Name of your service")
    String serviceName;

    @Override
    public void run() {
        try {
            Map<String, String> config = ConfigUtils.loadConfig();
            ArrayList<String> command = new ArrayList<>(List.of("sensible-editor", config.get(serviceName)));
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.inheritIO();
            Process process = pb.start();
            int exitCode = process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}

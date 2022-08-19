package injex.dcc.command;

import injex.dcc.utils.ConfigUtils;
import picocli.CommandLine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CommandLine.Command(name = "up",
        mixinStandardHelpOptions = true,
        description = "Deregister your docker-compose from the global context"
)
public class UpCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<service_name>",
            description = "Name of your service")
    String serviceName;

    @CommandLine.Option(names = {"-s", "--service"}, description = "Specify what service to run from compose file")
    String service = "";


    @Override
    public void run() {
        try {
            Map<String, String> config = ConfigUtils.loadConfig();
            String servicePath = config.get(serviceName);
            ArrayList<String> command = new ArrayList<>(List.of("docker-compose", "-f", servicePath, "up", "-d"));
            if (!service.isEmpty()) {
                command.add(service);
            }
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.inheritIO();
            Process process = pb.start();
            int exitCode = process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}

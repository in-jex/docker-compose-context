package injex.dcc.command;

import injex.dcc.utils.ConfigUtils;
import picocli.CommandLine;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CommandLine.Command(name = "ps",
        mixinStandardHelpOptions = true,
        description = "docker ps specified compose context"
)
public class PsCommand implements Runnable {
    @CommandLine.Parameters(paramLabel = "<service_name>",
            description = "Name of your service", defaultValue = "")
    String serviceName;

    @Override
    public void run() {
        try {
            if (serviceName.equals("")) {
                System.out.println("Please specify service_name to be \"ps\"-ed");
                System.out.println("e.g dcc down my_service");
                return;
            }
            Map<String, String> config = ConfigUtils.loadConfig();
            String servicePath = config.get(serviceName);
            ProcessBuilder pb = new ProcessBuilder("docker-compose", "-f", servicePath, "ps");
            pb.inheritIO();
            Process process = pb.start();
            int exitCode = process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}

package injex.dcc.command;

import injex.dcc.command.down.DownAllCommand;
import injex.dcc.utils.ConfigUtils;
import picocli.CommandLine;

import java.io.IOException;
import java.util.Map;

@CommandLine.Command(name = "down",
        mixinStandardHelpOptions = true,
        description = "Deregister your docker-compose from the global context",
        subcommands = {DownAllCommand.class},
        aliases = "d"
)
public class DownCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<service_name>",
            description = "Name of your service", defaultValue = "")
    String serviceName;


    @Override
    public void run() {
        try {
            if (serviceName.equals("")) {
                System.out.println("Please specify service_name to be stopped");
                System.out.println("e.g dcc down my_service");
                return;
            }
            Map<String, String> config = ConfigUtils.loadConfig();
            String servicePath = config.get(serviceName);
            ProcessBuilder pb = new ProcessBuilder("docker-compose", "-f", servicePath, "down");
            pb.inheritIO();
            Process process = pb.start();
            int exitCode = process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}

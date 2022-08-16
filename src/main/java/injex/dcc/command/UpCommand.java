package injex.dcc.command;

import injex.dcc.utils.ConfigUtils;
import injex.dcc.utils.StreamGobbler;
import picocli.CommandLine;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executors;

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
            String command = "docker-compose -f " + servicePath + " up -d " + service;
            System.out.println(command);
            Process process = Runtime.getRuntime()
                    .exec(command);
            StreamGobbler streamGobbler =
                    new StreamGobbler(process.getInputStream(), process.getErrorStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamGobbler);
            int exitCode = process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}

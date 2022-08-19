package injex.dcc.command;

import injex.dcc.utils.ConfigUtils;
import injex.dcc.utils.FileUtils;
import picocli.CommandLine;

import java.io.IOException;
import java.util.List;

@CommandLine.Command(name = "register",
        mixinStandardHelpOptions = true,
        description = "Register your docker-compose into the global context"
)
public class RegisterCommand implements Runnable {
    @CommandLine.Parameters(paramLabel = "<path>",
            description = "Relative path to your docker-compose.yaml (e.g `./docker-compose.yaml`)")
    String path;

    @CommandLine.Parameters(paramLabel = "<service_name>",
            description = "Name you'll give you'll give to your service")
    String serviceName;


    @Override
    public void run() {
        try {
            List<String> strings = ConfigUtils.loadConfigList();
            strings.add(serviceName + "=" + FileUtils.getPathRelative(path));
            ConfigUtils.writeConfig(strings);
            System.out.printf("Successfully added %s (at %s) to context%n", serviceName, FileUtils.getPathRelative(path));
        } catch (IOException e) {
            System.err.println("Something went wrong:");
            e.printStackTrace();
        }
    }

}

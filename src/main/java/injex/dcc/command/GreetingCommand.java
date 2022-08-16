package injex.dcc.command;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "greeting", mixinStandardHelpOptions = true)
public class GreetingCommand implements Runnable {

    @Parameters(paramLabel = "<path>",
        description = "Relative path to your docker-compose.yaml (e.g `./docker-compose.yaml`)")
    String path;

    @Parameters(paramLabel = "<service_name>",
            description = "Name you'll give you'll give to your service")
    String serviceName;


    @Override
    public void run() {
        System.out.printf("Hello %s, go go commando!\n", path);
    }

}

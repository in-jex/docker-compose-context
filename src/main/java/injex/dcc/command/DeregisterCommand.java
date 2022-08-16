package injex.dcc.command;

import injex.dcc.utils.ConfigUtils;
import injex.dcc.utils.FileUtils;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

@CommandLine.Command(name = "deregister",
        mixinStandardHelpOptions = true,
        description = "Deregister your docker-compose from the global context"
)
public class DeregisterCommand implements Runnable {


    @CommandLine.Parameters(paramLabel = "<service_name>",
            description = "Name you gave to your service")
    String serviceName;


    @Override
    public void run() {
        try {
            List<String> filteredList = ConfigUtils.loadConfigList()
                    .stream()
                    .filter(it -> !it.startsWith(serviceName + "="))
                    .collect(Collectors.toList());
            ConfigUtils.writeConfig(filteredList);
            System.out.println("Filetered");
        } catch (IOException e) {
            System.err.println("Something went wrong:");
            e.printStackTrace();
        }
    }

}

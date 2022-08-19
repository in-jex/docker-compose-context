package injex.dcc.command;

import picocli.CommandLine;

@CommandLine.Command(name = "restart",
        mixinStandardHelpOptions = true,
        description = "Restart(stop and start) your docker-compose from the global context"
)
public class RestartCommand implements Runnable {

    @CommandLine.Parameters(paramLabel = "<service_name>",
            description = "Name of your service")
    String serviceName;

    @CommandLine.Option(names = {"-s", "--service"}, description = "Specify what service to run from compose file")
    String service = "";

    @Override
    public void run() {
        UpCommand upCommand = new UpCommand();
        upCommand.serviceName = serviceName;
        upCommand.service = service;
        DownCommand downCommand = new DownCommand();
        downCommand.serviceName = serviceName;
        downCommand.run();
        upCommand.run();
    }

}

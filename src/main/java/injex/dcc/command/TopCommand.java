package injex.dcc.command;

import picocli.CommandLine;

import javax.enterprise.context.ApplicationScoped;

@io.quarkus.picocli.runtime.annotations.TopCommand
@CommandLine.Command(mixinStandardHelpOptions = true,
        subcommands = {RegisterCommand.class, GreetingCommand.class, DeregisterCommand.class, ListCommand.class, UpCommand.class, DownCommand.class})
@ApplicationScoped
public class TopCommand {

}

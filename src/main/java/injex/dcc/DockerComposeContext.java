package injex.dcc;

import injex.dcc.command.*;
import picocli.CommandLine;

import javax.enterprise.context.ApplicationScoped;

@io.quarkus.picocli.runtime.annotations.TopCommand
@CommandLine.Command(mixinStandardHelpOptions = true,
        subcommands = {RegisterCommand.class, DeregisterCommand.class, ListCommand.class, UpCommand.class, DownCommand.class, RestartCommand.class, PsCommand.class, EditCommand.class})
@ApplicationScoped
public class DockerComposeContext {

}

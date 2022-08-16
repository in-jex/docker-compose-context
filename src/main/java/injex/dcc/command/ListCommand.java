package injex.dcc.command;

import injex.dcc.utils.ConfigUtils;
import picocli.CommandLine;

import java.io.IOException;
import java.util.List;

@CommandLine.Command(name = "list",
        mixinStandardHelpOptions = true,
        description = "List all your registered dcs"
)
public class ListCommand implements Runnable {

    @Override
    public void run() {
        try {
            List<String> strings = ConfigUtils.loadConfigList();
            System.out.println(String.join("\n", strings));
        } catch (IOException e) {
            System.err.println("Something went wrong:");
            e.printStackTrace();
        }
    }

}

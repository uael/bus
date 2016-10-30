package unice.s3a.bus.command;

import io.airlift.airline.Arguments;
import io.airlift.airline.Command;
import io.airlift.airline.Option;
import unice.s3a.bus.Bbus;
import unice.s3a.bus.User;
import unice.s3a.bus.UserType;
import unice.s3a.bus.console.Response;

import java.util.List;

/**
 * The type Bus remove.
 */
@Command(name = "remove", description = "remove buses")
public class BusRemove extends BbusCommand {
    /**
     * The Names.
     */
    @Arguments(description = "The buses name", required = true)
    List<String> names;
    /**
     * The User name.
     */
    @Option(name = { "-u", "--user" }, description = "The agent that will create the bus", required = true)
    String userName;

    @Override
    public Object call() throws Exception {
        User user = this.getUser(userName);
        this.assertUserType(user, UserType.AGENT);
        for (String name : this.names) {
            if (!Bbus.busMap.containsKey(name)) {
                names.remove(name);
                continue;
            }
            Bbus.busMap.remove(name);
        }
        Bbus.save();

        return new Response("Successfully removed buses "+String.join(", ", names)+".");
    }
}

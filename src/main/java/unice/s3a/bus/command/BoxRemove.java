package unice.s3a.bus.command;

import io.airlift.airline.Arguments;
import io.airlift.airline.Command;
import io.airlift.airline.Option;
import unice.s3a.bus.Bbus;
import unice.s3a.bus.Bus;
import unice.s3a.bus.User;
import unice.s3a.bus.UserType;
import unice.s3a.bus.console.Response;

import java.util.List;

/**
 * The type Box remove.
 */
@Command(name = "remove", description = "remove boxes from a bus")
public class BoxRemove extends BbusCommand {
    /**
     * The Bus name.
     */
    @Option(name = { "-n", "--name" }, description = "the bus name", required = true)
    String busName;
    /**
     * The Names.
     */
    @Arguments(description = "The boxes name", required = true)
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
        Bus bus = this.getBus(busName);
        for (String name : names) {
            bus.getBoxMap().remove(name);
        }
        Bbus.save();

        return new Response("Successfully removed boxes "+String.join(", ", names)+" from bus "+busName);
    }
}

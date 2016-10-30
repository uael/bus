package unice.s3a.bus.command;

import io.airlift.airline.Arguments;
import io.airlift.airline.Command;
import io.airlift.airline.Option;
import unice.s3a.bus.Bbus;
import unice.s3a.bus.User;
import unice.s3a.bus.console.Response;

import java.util.List;

/**
 * The type User unsubscribe.
 */
@Command(name = "unsubscribe", description = "unsubscribe from a bus")
public class UserUnsubscribe extends BbusCommand {
    /**
     * The Names.
     */
    @Arguments(description = "The buses name", required = true)
    List<String> names;
    /**
     * The Type.
     */
    @Option(name = { "-u", "--user" }, description = "The user name", required = true)
    String userName;

    @Override
    public Object call() throws Exception {
        User u = this.getUser(userName);
        for (String name : names) {
            if (!Bbus.busMap.containsKey(name)) {
                names.remove(name);
                continue;
            }
            u.unsubscribe(Bbus.busMap.get(name));
        }
        Bbus.save();
        return new Response("Successfully unsubscribed user "+userName+" from buses "+String.join(", ", names)+".");
    }
}

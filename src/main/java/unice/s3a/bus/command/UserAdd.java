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
 * The type User add.
 */
@Command(name = "add", description = "add users")
public class UserAdd extends BbusCommand {
    /**
     * The Names.
     */
    @Arguments(description = "The users name", required = true)
    List<String> names;
    /**
     * The Type.
     */
    @Option(name = { "-t", "--type" }, description = "<DEFAULT, AGENT, PRODUCER>")
    UserType type;

    @Override
    public Object call() throws Exception {
        for (String name : names) {
            if (Bbus.users.containsKey(name)) {
                names.remove(name);
                continue;
            }
            if (type == null) {
                type = UserType.DEFAULT;
            }
            Bbus.users.put(name, new User(name, type));
        }
        Bbus.save();
        return new Response("Successfully added users "+String.join(", ", names)+".");
    }
}

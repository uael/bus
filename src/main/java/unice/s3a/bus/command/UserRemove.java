package unice.s3a.bus.command;

import io.airlift.airline.Arguments;
import io.airlift.airline.Command;
import unice.s3a.bus.Bbus;
import unice.s3a.bus.console.Response;

import java.util.List;

/**
 * The type User remove.
 */
@Command(name = "remove", description = "remove users")
public class UserRemove extends BbusCommand {
    /**
     * The Names.
     */
    @Arguments(description = "The users name", required = true)
    List<String> names;

    @Override
    public Object call() {
        for (String name : names) {
            if (!Bbus.users.containsKey(name)) {
                names.remove(name);
                continue;
            }
            Bbus.users.remove(name);
        }
        Bbus.save();
        return new Response("Successfully removed users "+String.join(", ", names)+".");
    }
}

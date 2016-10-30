package unice.s3a.bus.command;

import io.airlift.airline.Command;
import unice.s3a.bus.Bbus;

/**
 * The type User list.
 */
@Command(name = "list", description = "list all users")
public class UserList extends BbusCommand {
    @Override
    public Object call() {
        return Bbus.users.values();
    }
}

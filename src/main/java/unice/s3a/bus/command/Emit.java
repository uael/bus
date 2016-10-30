package unice.s3a.bus.command;

import io.airlift.airline.Arguments;
import io.airlift.airline.Command;
import io.airlift.airline.Option;
import unice.s3a.bus.Bbus;
import unice.s3a.bus.User;
import unice.s3a.bus.console.Response;

/**
 * The type Emit.
 */
@Command(name = "emit", description = "add boxes to a bus")
public class Emit extends BbusCommand {
    /**
     * The Box name.
     */
    @Option(name = { "-b", "--box" }, description = "the box name")
    String boxName;
    /**
     * The Bus name.
     */
    @Option(name = { "-n", "--bus" }, description = "the bus name", required = true)
    String busName;
    /**
     * The Message.
     */
    @Arguments(description = "The message", required = true)
    String message;
    /**
     * The User name.
     */
    @Option(name = { "-u", "--user" }, description = "the user")
    String userName;

    @Override
    public Object call() throws Exception {
        if (!Bbus.busMap.containsKey(busName)) {
            throw new Exception("The bus "+busName+" does not exists.");
        }
        if (userName != null) {
            User user = this.getUser(userName);
            if (boxName != null && !boxName.isEmpty()) {
                Bbus.busMap.emitAt(busName, boxName, user, message);
            } else {
                Bbus.busMap.emit(busName, user, message);
            }
        } else {
            if (boxName != null && !boxName.isEmpty()) {
                Bbus.busMap.emitAt(busName, boxName, message);
            } else {
                Bbus.busMap.emit(busName, message);
            }
        }
        Bbus.save();
        return new Response("Successfully added message "+message);
    }
}

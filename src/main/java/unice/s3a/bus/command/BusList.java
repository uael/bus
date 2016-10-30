package unice.s3a.bus.command;

import io.airlift.airline.Command;
import unice.s3a.bus.Bbus;

/**
 * The type Bus list.
 */
@Command(name = "list", description = "list all buses")
public class BusList extends BbusCommand {

    @Override
    public Object call() throws Exception {
        return Bbus.busMap;
    }
}

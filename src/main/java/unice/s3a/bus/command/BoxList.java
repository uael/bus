package unice.s3a.bus.command;

import io.airlift.airline.Command;
import io.airlift.airline.Option;

/**
 * The type Box list.
 */
@Command(name = "list", description = "list boxes from a bus")
public class BoxList extends BbusCommand {
    /**
     * The Bus name.
     */
    @Option(name = { "-n", "--name" }, description = "the bus name", required = true)
    String busName;

    @Override
    public Object call() throws Exception {
        return this.getBus(busName).getBoxMap();
    }
}

package unice.s3a.bus.command;

import io.airlift.airline.Command;
import io.airlift.airline.Option;
import unice.s3a.bus.Bus;

/**
 * The type Msg list.
 */
@Command(name = "list", description = "list messages")
public class MsgList extends BbusCommand {
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

    @Override
    public Object call() throws Exception {
        Bus bus = this.getBus(busName);
        if (boxName != null) {
            if (!bus.getBoxMap().containsKey(boxName)) {
                throw new Exception("Box "+boxName+" does not exists.");
            }
            return bus.getBoxMap().get(boxName);
        }
        return bus.getBoxMap();
    }
}

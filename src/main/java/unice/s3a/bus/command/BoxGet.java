package unice.s3a.bus.command;

import io.airlift.airline.Arguments;
import io.airlift.airline.Command;
import io.airlift.airline.Option;
import unice.s3a.bus.Bus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The type Box get.
 */
@Command(name = "get", description = "get boxes from a bus")
public class BoxGet extends BbusCommand {
    /**
     * The Bus name.
     */
    @Option(name = { "-n", "--name" }, description = "the bus name", required = true)
    String busName;
    /**
     * The Names.
     */
    @Arguments(description = "The boxes name")
    List<String> names;

    @Override
    public Object call() throws Exception {
        Bus bus = this.getBus(busName);
        if (names == null || names.size() <= 0) {
            names = new ArrayList<>();
            names.add("Default");
        }
        return bus.getBoxMap().entrySet().parallelStream().filter(e -> names.contains(e.getKey()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}

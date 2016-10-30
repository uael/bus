package unice.s3a.bus.command;

import org.junit.Before;
import org.junit.Test;
import unice.s3a.bus.Bbus;
import unice.s3a.bus.Bus;
import unice.s3a.bus.User;
import unice.s3a.bus.UserType;

import java.util.ArrayList;

public class BoxAddTest {
    BoxAdd command;

    @Before
    public void setUp() throws Exception {
        Bbus.busMap.clear();
        Bbus.users.clear();
        Bbus.busMap.put("t", new Bus("t"));
        Bbus.users.put("t", new User("t", UserType.AGENT));
        command = new BoxAdd();
        command.userName = "t";
        command.busName = "t";
        command.names = new ArrayList<>();
        command.names.add("a");
        command.names.add("b");
        command.names.add("c");
    }

    @Test
    public void runTest() throws Exception {
    }
}
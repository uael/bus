package unice.s3a.bus.command;

import com.google.gson.GsonBuilder;
import unice.s3a.bus.Bbus;
import unice.s3a.bus.Bus;
import unice.s3a.bus.User;
import unice.s3a.bus.UserType;
import unice.s3a.bus.console.Response;

import java.util.concurrent.Callable;

public abstract class BbusCommand implements Callable<Object>, Runnable {

    public Bus getBus(String busName) throws Exception {
        if (!Bbus.busMap.containsKey(busName)) {
            throw new Exception("The bus "+busName+" does not exists.");
        }
        return Bbus.busMap.get(busName);
    }

    public User getUser(String userName) throws Exception {
        if (!Bbus.users.containsKey(userName)) {
            throw new Exception("The user "+userName+" does not exists.");
        }
        return Bbus.users.get(userName);
    }

    public void assertUserType(User user, UserType type) throws Exception {
        if (user.getType() != type) {
            throw new Exception("The user must of type "+type.toString()+" to perform this action.");
        }
    }

    @Override
    public void run() {
        try {
            Object o = this.call();
            if (o instanceof Response) {
                System.out.println(((Response) o).content);
            } else {
                System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(o, o.getClass()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

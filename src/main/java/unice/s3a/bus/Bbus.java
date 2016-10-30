package unice.s3a.bus;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.airlift.airline.Cli;
import io.airlift.airline.Help;
import io.airlift.airline.ParseException;
import unice.s3a.bus.command.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * The type Bbus.
 */
public class Bbus {
    /**
     * The Bus provider.
     */
    public static final BusMap busMap;
    /**
     * The Users.
     */
    public static final HashMap<String, User> users;

    public static final Cli<Runnable> cli;

    static {
        HashMap<String, User> _users = null;
        BusMap _busMap = null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File dataFile = new File("users.json");
        if (!dataFile.exists()) {
            try {
                new FileOutputStream(dataFile).close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        } else {
            try {
                _users = gson.fromJson(
                    new String(Files.readAllBytes(Paths.get("users.json")), Charsets.UTF_8),
                    new TypeToken<HashMap<String, User>>() { }.getType()
                );
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        dataFile = new File("buses.json");
        if (!dataFile.exists()) {
            try {
                new FileOutputStream(dataFile).close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        } else {
            try {
                _busMap = gson.fromJson(
                    new String(Files.readAllBytes(Paths.get("buses.json")), Charsets.UTF_8),
                    new TypeToken<BusMap>() { }.getType()
                );
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        busMap = _busMap != null ? _busMap : new BusMap();
        users = _users != null ? _users : new HashMap<>();

        Cli.CliBuilder<Runnable> builder = Cli.<Runnable>builder("bbus")
            .withDescription("Bus message controller")
            .withDefaultCommand(Help.class)
            .withCommands(Help.class, Emit.class);

        builder.withGroup("bus")
            .withDescription("bus actions")
            .withDefaultCommand(BusList.class)
            .withCommands(BusAdd.class, BusRemove.class, BusList.class);

        builder.withGroup("box")
            .withDescription("box actions")
            .withDefaultCommand(BoxAdd.class)
            .withCommands(BoxAdd.class, BoxRemove.class, BoxList.class);

        builder.withGroup("msg")
            .withDescription("msg actions")
            .withDefaultCommand(MsgList.class)
            .withCommands(MsgList.class);

        builder.withGroup("user")
            .withDescription("box actions")
            .withDefaultCommand(UserList.class)
            .withCommands(UserAdd.class, UserRemove.class, UserList.class, UserSubscribe.class, UserUnsubscribe.class);

        cli = builder.build();
    }

    /**
     * The entry point of application.
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            cli.parse(args).run();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Save.
     */
    public static void save() {
        try {
            Gson gson = new Gson();
            PrintWriter w = new PrintWriter("users.json");
            String s = gson.toJson(users, new TypeToken<HashMap<String, User>>() { }.getType());
            w.write(s);
            w.close();
            w = new PrintWriter("buses.json");
            s = gson.toJson(busMap, new TypeToken<BusMap>() { }.getType());
            w.write(s);
            w.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

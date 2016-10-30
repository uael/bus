package unice.s3a.bus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

/**
 * The type Box map.
 */
public class BoxMap extends HashMap<String, Box> {
    /**
     * Add box boolean.
     * @param name the name
     * @return the boolean
     */
    public boolean add(String name) {
        return this.put(name, new Box()) != null;
    }

    /**
     * Gets or add.
     * @param name the name
     * @return the or add
     */
    public Box getOrAdd(String name) {
        if (this.containsKey(name)) {
            return this.get(name);
        }
        Box m = new Box();
        this.put(name, m);
        return m;
    }

    @Override
    public Box put(final String key, final Box value) {
        if (Bbus.busMap != null) {
            for (Bus bus : Bbus.busMap.values()) {
                for (String boxName : this.keySet()) {
                    if (boxName.equals(key)) {
                        throw new IllegalArgumentException("Box "+key+" already exists in bus "+bus.getName()+".");
                    }
                }
            }
        }
        return super.put(key, value);
    }

    /**
     * Json string.
     * @return the string
     */
    public String json() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this, this.getClass());
    }
}

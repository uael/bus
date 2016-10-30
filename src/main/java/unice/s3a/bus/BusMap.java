package unice.s3a.bus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.HashMap;

/**
 * The type Bus busMap.
 */
public class BusMap extends HashMap<String, Bus> {
    /**
     * Emit boolean.
     * @param busName the bus name
     * @param message the message
     * @return the boolean
     */
    public boolean emit(final String busName, final String message) {
        return this.getOrAdd(busName).emit(message);
    }

    /**
     * Emit boolean.
     * @param busName        the bus name
     * @param message        the message
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emit(final String busName, final String message, final Date expirationDate) {
        return this.getOrAdd(busName).emit(message, expirationDate);
    }

    /**
     * Emit boolean.
     * @param busName  the bus name
     * @param producer the producer
     * @param content  the content
     * @return the boolean
     */
    public boolean emit(final String busName, User producer, String content) {
        return this.getOrAdd(busName).emit(producer, content);
    }

    /**
     * Emit boolean.
     * @param busName        the bus name
     * @param producer       the producer
     * @param content        the content
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emit(final String busName, User producer, String content, final Date expirationDate) {
        return this.getOrAdd(busName).emit(producer, content, expirationDate);
    }

    /**
     * Emit at boolean.
     * @param busName the bus name
     * @param boxName the box name
     * @param message the message
     * @return the boolean
     */
    public boolean emitAt(final String busName, final String boxName, final String message) {
        return this.getOrAdd(busName).emitAt(boxName, message);
    }

    /**
     * Emit at boolean.
     * @param busName        the bus name
     * @param boxName        the box name
     * @param message        the message
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emitAt(final String busName, final String boxName, final String message, final Date expirationDate) {
        return this.getOrAdd(busName).emitAt(boxName, message, expirationDate);
    }

    /**
     * Emit at boolean.
     * @param busName  the bus name
     * @param boxName  the box name
     * @param producer the producer
     * @param content  the content
     * @return the boolean
     */
    public boolean emitAt(final String busName, final String boxName, User producer, String content) {
        return this.getOrAdd(busName).emitAt(boxName, producer, content);
    }

    /**
     * Emit at boolean.
     * @param busName        the bus name
     * @param boxName        the box name
     * @param producer       the producer
     * @param content        the content
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emitAt(final String busName, final String boxName, User producer, String content, final Date
        expirationDate) {
        return this.getOrAdd(busName).emitAt(boxName, producer, content, expirationDate);
    }

    /**
     * Gets or add.
     * @param name the name
     * @return the or add
     */
    public Bus getOrAdd(String name) {
        if (this.containsKey(name)) {
            return this.get(name);
        }
        Bus b = new Bus(name);
        this.put(name, b);
        return b;
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

package unice.s3a.bus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The type unice.s3a.bus.User.
 */
public class User {
    private String nickname;
    private UserType type;

    /**
     * Instantiates a new User.
     * @param nickname the nickname
     */
    public User(final String nickname) {
        this(nickname, UserType.DEFAULT);
    }

    /**
     * Instantiates a new User.
     * @param nickname the nickname
     * @param type     the type
     */
    public User(final String nickname, final UserType type) {
        this.nickname = nickname;
        this.type = type;
    }

    /**
     * Gets nickname.
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Gets type.
     * @return the type
     */
    public UserType getType() {
        return type;
    }

    /**
     * Subscribe.
     * @param bus the bus
     */
    public void subscribe(Bus bus) {
        bus.getSubscribers().add(this.nickname);
    }

    /**
     * Unsubscribe.
     * @param bus the bus
     */
    public void unsubscribe(Bus bus) {
        bus.getSubscribers().remove(this.nickname);
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

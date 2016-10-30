package unice.s3a.bus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

/**
 * The type unice.s3a.bus.Message.
 */
public class Message {
    private String content;
    private Date expirationDate;
    private User producer;

    /**
     * Instantiates a new Message.
     * @param content the content
     */
    public Message(final String content) {
        this(null, content, null);
    }

    /**
     * Instantiates a new Message.
     * @param content        the content
     * @param expirationDate the expiration date
     */
    public Message(final String content, final Date expirationDate) {
        this(null, content, expirationDate);
    }

    /**
     * Instantiates a new Message.
     * @param producer the producer
     * @param content  the content
     */
    public Message(final User producer, final String content) {
        this(producer, content, null);
    }

    /**
     * Instantiates a new Message.
     * @param producer       the producer
     * @param content        the content
     * @param expirationDate the expiration date
     */
    public Message(final User producer, final String content, final Date expirationDate) {
        this.producer = producer;
        this.content = content;
        if (expirationDate == null) {
            this.expirationDate = new Date();
            this.expirationDate.setMinutes(this.expirationDate.getMinutes()+30);
        } else {
            this.expirationDate = expirationDate;
        }
    }

    /**
     * Gets content.
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets content.
     * @param content the content
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     * Gets producer.
     * @return the producer
     */
    public User getProducer() {
        return producer;
    }

    /**
     * Is active boolean.
     * @return the boolean
     */
    public boolean isActive() {
        return this.expirationDate.after(new Date());
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

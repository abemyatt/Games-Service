package abemyatt.games.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Comment {
    private String user;
    private String message;
    private long dateCreated;
    private int like;

    public Comment(String user, String message, long dateCreated, int like) {
        this.user = user;
        this.message = message;
        this.dateCreated = dateCreated;
        this.like = like;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Dates are stored as seconds since epoch, but require readable format
     * @return  the date in string format of yyyy-MM-dd
     */
    public String getDateCreated() {
        // Assume time since epoch is in seconds (spec uses 12423423453 as example)
        LocalDate date = Instant.ofEpochSecond(dateCreated).atZone(ZoneId.systemDefault()).toLocalDate();

        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}

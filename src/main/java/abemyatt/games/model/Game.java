package abemyatt.games.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "games")
public class Game {

    @Id
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("by")
    private String by;
    @JsonProperty("platform")
    private List<String> platform;
    @Field("age_rating") // Assume age_rating is stored with snake case
    @JsonProperty("age_rating")
    private String ageRating;
    @JsonProperty("likes")
    private int likes;
    @JsonProperty("comments")
    private List<Comment> comments;

    public Game(String id, String title, String description, String by,
                List<String> platform, String ageRating, int likes, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.by = by;
        this.platform = platform;
        this.ageRating = ageRating;
        this.likes = likes;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public List<String> getPlatform() {
        return platform;
    }

    public void setPlatform(List<String> platform) {
        this.platform = platform;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

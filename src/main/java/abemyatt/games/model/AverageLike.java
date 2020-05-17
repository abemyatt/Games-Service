package abemyatt.games.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AverageLike {

    private String title;
    @JsonProperty("average_likes")
    private int averageLikes;

    public AverageLike(String title, int averageLikes) {
        this.title = title;
        this.averageLikes = averageLikes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAverageLikes() {
        return averageLikes;
    }

    public void setAverageLikes(int averageLikes) {
        this.averageLikes = averageLikes;
    }
}

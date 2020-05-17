package abemyatt.games.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Summary {

    @JsonProperty("user_with_most_comments")
    private String userWithMostComments;
    @JsonProperty("highest_rated_game")
    private String highestRatedGame;
    @JsonProperty("average_likes_per_game")
    private List<AverageLike> averageLikesPerGame;

    public Summary(String userWithMostComments, String highestRatedGame,
                   List<AverageLike> averageLikesPerGame) {
        this.userWithMostComments = userWithMostComments;
        this.highestRatedGame = highestRatedGame;
        this.averageLikesPerGame = averageLikesPerGame;
    }

    public String getUserWithMostComments() {
        return userWithMostComments;
    }

    public void setUserWithMostComments(String userWithMostComments) {
        this.userWithMostComments = userWithMostComments;
    }

    public String getHighestRatedGame() {
        return highestRatedGame;
    }

    public void setHighestRatedGame(String highestRatedGame) {
        this.highestRatedGame = highestRatedGame;
    }

    public List<AverageLike> getAverageLikesPerGame() {
        return averageLikesPerGame;
    }

    public void setAverageLikesPerGame(List<AverageLike> averageLikesPerGame) {
        this.averageLikesPerGame = averageLikesPerGame;
    }

}

package abemyatt.games;

import abemyatt.games.model.AverageLike;
import abemyatt.games.model.Comment;
import abemyatt.games.model.Game;
import abemyatt.games.model.Summary;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

/**
 * Sets the common components between the unit test classes
 */
public class GameTestParent {

    private static final long TIME_IN_EPOCH = 1589760000L;
    private static final long TIME_IN_EPOCH_TWO = 1583107200L;
    protected static final String GAME_TITLE = "The Game";
    private static final String GAME_TITLE_TWO = "Cool Game";
    protected static final String USER = "John Smith";
    private static final String USER_TWO = "Bob";
    private static final String USER_THREE = "Alice";

    protected Game game;

    protected List<Game> games;

    protected Summary summary;

    protected List<AverageLike> averageLikes;

    @BeforeEach
    void setup() {
        // Setup the comments for the games
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment(USER, "This is a comment", TIME_IN_EPOCH, 10));
        comments.add(new Comment(USER_TWO, "I am writing a comment", TIME_IN_EPOCH_TWO, 3));

        List<Comment> commentsTwo = new ArrayList<>();
        commentsTwo.add(new Comment(USER, "This is another comment", TIME_IN_EPOCH, 20));
        commentsTwo.add(new Comment(USER_THREE, "I am writing a comment like Bob", TIME_IN_EPOCH_TWO, 20));

        // Setup the game object and games arrays
        ArrayList<String> platforms = new ArrayList<>();
        platforms.add("HOT WATER AIR");
        platforms.add("XBLOX");
        game = new Game("1", GAME_TITLE, "You just lost the game",
                "Factivision", platforms, "18", 100, comments);
        Game gameTwo = new Game("2", GAME_TITLE_TWO, "This is a cool game",
                "Bintendo", platforms, "3", 20, commentsTwo);

        games = new ArrayList<>();
        games.add(game);
        games.add(gameTwo);

        // Setup expected average likes by adding the comment like values and averaging
        averageLikes = new ArrayList<>();
        averageLikes.add(new AverageLike(GAME_TITLE, (10 + 3) / 2));
        averageLikes.add(new AverageLike(GAME_TITLE_TWO, (20 + 20) / 2));

        summary = new Summary(USER, GAME_TITLE, averageLikes);
    }
}

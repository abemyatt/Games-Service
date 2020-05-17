package abemyatt.games.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import abemyatt.games.model.AverageLike;
import abemyatt.games.model.Comment;
import abemyatt.games.model.Game;
import abemyatt.games.model.Summary;
import abemyatt.games.service.GameServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {
    private static final long TIME_IN_EPOCH = 1589760000L;
    private static final long TIME_IN_EPOCH_TWO = 1583107200L;
    private static final String GAME_TITLE = "The Game";
    private static final String GAME_TITLE_TWO = "Cool Game";
    private static final String USER = "John Smith";
    private static final String USER_TWO = "Bob";
    private static final String USER_THREE = "Alice";

    @Mock
    private GameServiceImpl service;

    @InjectMocks
    private GameController controller;

    private List<Game> games;
    private Game game;
    private Game gameTwo;

    private Summary summary;

    private List<AverageLike> averageLikes;

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
        gameTwo = new Game("2", GAME_TITLE_TWO, "This is a cool game",
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

    @Test
    void testGetGameWithIdSuccess() {
        // Given
        when(service.findById(anyString())).thenReturn(game);
        Game expected = game;

        // When
        Game actual = controller.findGameById("1");

        // Then
        assertThat(expected, is(actual));
    }

    @Test
    void testGetGameSummarySuccess() {
        // Given
        when(service.getGameSummary()).thenReturn(summary);
        Summary expected = summary;

        // When
        Summary actual = controller.getGameSummary();

        // Then
        assertThat(expected, is(actual));
    }

}

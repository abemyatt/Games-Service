package abemyatt.games.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import abemyatt.games.model.AverageLike;
import abemyatt.games.model.Comment;
import abemyatt.games.model.Game;
import abemyatt.games.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {
    private static final long TIME_IN_EPOCH = 1589760000L;
    private static final long TIME_IN_EPOCH_TWO = 1583107200L;
    private static final String GAME_TITLE = "The Game";
    private static final String GAME_TITLE_TWO = "Cool Game";
    private static final String USER = "John Smith";
    private static final String USER_TWO = "Bob";
    private static final String USER_THREE = "Alice";

    @Mock
    private GameRepository repository;

    @InjectMocks
    private GameServiceImpl service;

    private List<Game> games;
    private Game game;

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
        platforms.add("PC");
        platforms.add("XBOX");
        game = new Game("1", GAME_TITLE, "You just lost the game",
                "Abe Myatt", platforms, "18", 100, comments);
        Game gameTwo = new Game("2", GAME_TITLE_TWO, "This is a cool game",
                "Graham", platforms, "3", 20, commentsTwo);
        games = new ArrayList<>();
        games.add(game);
        games.add(gameTwo);

        // Setup expected average likes by adding the comment like values and averaging
        averageLikes = new ArrayList<>();
        averageLikes.add(new AverageLike(GAME_TITLE, (10 + 3) / 2));
        averageLikes.add(new AverageLike(GAME_TITLE_TWO, (20 + 20) / 2));
    }

    @Test
    void testFindByIdExists() {
        // Given
        when(repository.findById(anyString())).thenReturn(Optional.of(game));

        Game expected = game;

        // When
        Game actual = service.findById("1");

        // Then
        assertThat(expected, is(actual));
    }

    @Test
    void testFindByIdDoesNotExistReturnsNull() {
        // Given
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        // When
        Game actual = service.findById("1");

        // Then
        assertNull(actual);
    }

    @Test
    void testFindAllWithEntries() {
        // Given
        when(repository.findAll()).thenReturn(games);
        List<Game> expected = games;

        // When
        List<Game> actual = service.findAllGames();

        // Then
        assertThat(expected, is(actual));
    }

    @Test
    void testHighestRatedGameSuccess() {
        // Given
        when(repository.findAll()).thenReturn(games);

        String expected = GAME_TITLE;

        // When
        String actual = service.getGameSummary().getHighestRatedGame();

        // Then
        assertThat(expected, is(actual));
    }

    @Test
    void testHighestRatedGameNoGames() {
        // Given
        when(repository.findAll()).thenReturn(new ArrayList<>());

        String expected = "";

        // When
        String actual = service.getGameSummary().getHighestRatedGame();

        // Then
        assertThat(expected, is(actual));
    }

    @Test
    void testUserMostCommentsSuccess() {
        // Given
        when(repository.findAll()).thenReturn(games);

        String expected = USER;

        // When
        String actual = service.getGameSummary().getUserWithMostComments();

        // Then
        assertThat(expected, is(actual));
    }

    @Test
    void testUserMostCommentsNoGames() {
        // Given
        when(repository.findAll()).thenReturn(new ArrayList<>());

        String expected = "";

        // When
        String actual = service.getGameSummary().getUserWithMostComments();

        // Then
        assertThat(expected, is(actual));
    }


    @Test
    void testGetAverageLikesSuccess() {
        // Given
        when(repository.findAll()).thenReturn(games);
        List<AverageLike> expected = averageLikes;

        // When
        List<AverageLike> actual = service.getGameSummary().getAverageLikesPerGame();

        // Then
        assertEquals(expected.get(0).getTitle(), actual.get(0).getTitle());
        assertEquals(expected.get(0).getAverageLikes(), actual.get(0).getAverageLikes());
        assertEquals(expected.get(1).getTitle(), actual.get(1).getTitle());
        assertEquals(expected.get(1).getAverageLikes(), actual.get(1).getAverageLikes());
    }

    @Test
    void testGetAverageLikesNoGames() {
        // Given
        when(repository.findAll()).thenReturn(new ArrayList<>());
        int expectedListSize = 0;

        // When
        int actual = service.getGameSummary().getAverageLikesPerGame().size();


        // Then
        assertThat(expectedListSize, is(actual));
    }


}

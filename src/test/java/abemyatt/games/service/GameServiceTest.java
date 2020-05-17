package abemyatt.games.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import abemyatt.games.GameTestParent;
import abemyatt.games.model.AverageLike;
import abemyatt.games.model.Game;
import abemyatt.games.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class GameServiceTest extends GameTestParent {

    @Mock
    private GameRepository repository;

    @InjectMocks
    private GameServiceImpl service;

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

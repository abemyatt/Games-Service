package abemyatt.games.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import abemyatt.games.GameTestParent;
import abemyatt.games.model.Game;
import abemyatt.games.model.Summary;
import abemyatt.games.service.GameServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GameControllerTest  extends GameTestParent {

    @Mock
    private GameServiceImpl service;

    @InjectMocks
    private GameController controller;

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

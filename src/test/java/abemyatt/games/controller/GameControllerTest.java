package abemyatt.games.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import abemyatt.games.GameTestParent;
import abemyatt.games.service.GameServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class GameControllerTest  extends GameTestParent {

    @Mock
    private GameServiceImpl service;

    @InjectMocks
    private GameController controller;

    @Test
    void testFindGameByIdSuccess() {
        // Given
        when(service.findById(anyString())).thenReturn(game);
        ResponseEntity expected = ResponseEntity.status(HttpStatus.OK).body(game);

        // When
        ResponseEntity actual = controller.findGameById("1");

        // Then
        assertThat(expected, is(actual));
    }
    @Test
    void testFindGameByIdNotFound() {
        // Given
        when(service.findById(anyString())).thenReturn(null);
        ResponseEntity expected = new ResponseEntity(HttpStatus.NOT_FOUND);

        // When
        ResponseEntity actual = controller.findGameById("1");

        // Then
        assertThat(expected, is(actual));
    }

    @Test
    void testGetGameSummarySuccess() {
        // Given
        when(service.getGameSummary()).thenReturn(summary);
        ResponseEntity expected = ResponseEntity.status(HttpStatus.OK).body(summary);

        // When
        ResponseEntity actual = controller.getGameSummary();

        // Then
        assertThat(expected, is(actual));
    }
    @Test
    void testGetGameSummaryNotFound() {
        // Given
        when(service.getGameSummary()).thenReturn(null);
        ResponseEntity expected = new ResponseEntity(HttpStatus.NOT_FOUND);

        // When
        ResponseEntity actual = controller.getGameSummary();

        // Then
        assertThat(expected, is(actual));
    }

}

package abemyatt.games.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommentTest {
    private static final long TIME_IN_EPOCH = 1589760000L; // epoch time in seconds for 2011-01-20

    private Comment comment;

    @BeforeEach
    void setup() {
        comment = new Comment("John Smith", "Very good game", TIME_IN_EPOCH, 5);
    }

    @Test
    void getTimeFormattedyyyyMMddSuccess() {
        // Given
        String expected = "2020-05-18";

        // When
        String result = comment.getDateCreated();

        // Then
        assertThat(expected, is(result));
    }
}

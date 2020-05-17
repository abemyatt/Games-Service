package abemyatt.games.service;

import abemyatt.games.model.AverageLike;
import abemyatt.games.model.Comment;
import abemyatt.games.model.Game;
import abemyatt.games.model.Summary;
import abemyatt.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository repository;

    @Autowired
    public GameServiceImpl(GameRepository repository) {
        this.repository = repository;
    }

    @Override
    public Game findById(String id) {
        Optional<Game> game = repository.findById(id);

        return game.orElse(null);
    }

    @Override
    public List<Game> findAllGames() {
        return repository.findAll();
    }

    @Override
    public Summary getGameSummary() {
        return new Summary(getUserMostComments(), highestRatedGame(), getAverageLikes());
    }

    /**
     * Get all the games stored and store the user and their amount of comments in a map
     * Use the user as a key and the value as the amount of comments
     * @return  The user with the most comments
     */
    private String getUserMostComments() {
        List<Game> games = findAllGames();
        Map<String, Integer> userComments  = new HashMap<>();

        /*
            Loop through all of the games, then through all of the comments
            Get the current user's number of comments and add 1 to the value
         */
        for(Game game : games) {
            for(Comment comment : game.getComments()) {
                String user = comment.getUser();
                int count = userComments.getOrDefault(user, 0);
                userComments.put(user, count + 1);
            }
        }

        // Use a stream to compare the comments to find the user with the most
        Optional<Map.Entry<String, Integer>> opt = userComments.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue));

        // Check if the optional is present
        String userMostComments = "";

        if(opt.isPresent()) {
            userMostComments = opt.get().getKey();
        }

        return userMostComments;
    }

    /**
     * Find the highest rated game stored in the repository
     * based on the `likes' integer associated with a game
     * @return  The highest rated game
     */
    private String highestRatedGame() {
        List<Game> games = findAllGames();

        // Compare all games to find the one with the highest `likes'
        Optional<Game> opt = games
                .stream()
                .max(Comparator.comparing(Game::getLikes));

        // Check if the optional is present
        String highestRated = "";

        if(opt.isPresent()) {
            highestRated = opt.get().getTitle();
        }

        return highestRated;
    }

    /**
     * Retrieve all the games and loop through each game averaging the comment likes
     * @return  A list of AverageLike objects which contain a game title and average like
     */
    private List<AverageLike> getAverageLikes() {
        List<Game> games = findAllGames();
        ArrayList<AverageLike> averageLikes = new ArrayList<>();

        for(Game game : games) {
            List<Comment> comments = game.getComments();
            // Stream to sum all of the like values on a game's comments
            int sum = comments
                    .stream()
                    .mapToInt(Comment::getLike)
                    .sum();
            int average = sum / comments.size();
            averageLikes.add(new AverageLike(game.getTitle(), average));
        }

        return averageLikes;
    }
}

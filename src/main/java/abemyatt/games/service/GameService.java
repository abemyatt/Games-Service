package abemyatt.games.service;

import abemyatt.games.model.Game;
import abemyatt.games.model.Summary;

import java.util.List;

/**
 * A Game Service which is responsible for retrieving game information
 * and displaying `reports' about game data
 */
public interface GameService {

    /**
     * Call the repository to retrieve the game
     * If found, return the game, else return null
     * @param id    The ID of the game
     * @return      The Game if found or null
     */
    Game findById(String id);

    /**
     * Call the repository to retrieve all stored games
     * @return  A list of the games or an empty list
     */
    List<Game> findAllGames();

    /**
     * Calculate three statistics of the stored games
     * User with most comments across all games
     * Highest rated game - Based on the like value
     * Average likes per game - The amount of likes on all comments on a game
     * @return  A Summary object which stores the above information
     */
    Summary getGameSummary();
}

package abemyatt.games.controller;

import abemyatt.games.model.Game;
import abemyatt.games.model.Summary;
import abemyatt.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService service;

    @Autowired
    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Game findGameById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/report")
    public Summary getGameSummary() {
        return service.getGameSummary();
    }

}

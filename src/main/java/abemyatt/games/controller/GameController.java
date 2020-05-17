package abemyatt.games.controller;

import abemyatt.games.model.Game;
import abemyatt.games.model.Summary;
import abemyatt.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService service;

    @Autowired
    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity findGameById(@PathVariable String id) {
        Game game = service.findById(id);

        if (game == null) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @GetMapping("/report")
    public ResponseEntity getGameSummary() {
        Summary summary = service.getGameSummary();

        if (summary == null) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.OK).body(summary);
    }

}

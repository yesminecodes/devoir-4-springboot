package com.yesmine.games.restcontrollers;

import com.yesmine.games.dto.GameDTO;
import com.yesmine.games.service.GameService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class GameRESTController {

    private final GameService gameService;

    public GameRESTController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<GameDTO> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/page")
    public Page<GameDTO> getAllGamesByPage(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "5") int size) {
        return gameService.getAllGamesParPage(page, size);
    }

    @GetMapping("/{id}")
    public GameDTO getGameById(@PathVariable Long id) {
        return gameService.getGame(id);
    }

    @PostMapping
    public GameDTO createGame(@RequestBody GameDTO game) {
        return gameService.saveGame(game);
    }

    @PutMapping
    public GameDTO updateGame(@RequestBody GameDTO game) {
        return gameService.updateGame(game);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Long id) {
        gameService.deleteGameById(id);
    }

    @GetMapping("/prodscat/{idType}")
    public List<GameDTO> getGamesByTypeId(@PathVariable Long idType) {
        return gameService.findByTypeIdType(idType);
    }
}
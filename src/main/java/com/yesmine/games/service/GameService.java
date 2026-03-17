package com.yesmine.games.service;

import com.yesmine.games.dto.GameDTO;
import com.yesmine.games.model.Game;
import com.yesmine.games.model.Type;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GameService {

    GameDTO saveGame(GameDTO g);
    GameDTO updateGame(GameDTO g);
    void deleteGame(Long id);
    void deleteGameById(Long id);
    GameDTO getGame(Long id);
    List<GameDTO> getAllGames();

    Page<GameDTO> getAllGamesParPage(int page, int size);
    List<GameDTO> findByNomGame(String nom);
    List<GameDTO> findByNomGameContains(String nom);
    List<GameDTO> findByNomPrix(String nom, Double prix);
    List<GameDTO> findByType(Type type);
    List<GameDTO> findByTypeIdType(Long id);
    List<GameDTO> findByOrderByNomGameAsc();
    List<GameDTO> trierGamesNomsPrix();
    List<Type> getAllTypes();
    GameDTO convertEntityToDto(Game game);
    Game convertDtoToEntity(GameDTO gameDto);
}
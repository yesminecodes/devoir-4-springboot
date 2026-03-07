package com.yesmine.games.service;

import com.yesmine.games.model.Game;
import com.yesmine.games.model.Type;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GameService {

    Game saveGame(Game g);
    Game updateGame(Game g);
    void deleteGame(Game g);
    void deleteGameById(Long id);
    Game getGame(Long id);
    List<Game> getAllGames();

    Page<Game> getAllGamesParPage(int page, int size);
    List<Game> findByNomGame(String nom);
    List<Game> findByNomGameContains(String nom);
    List<Game> findByNomPrix (String nom, Double prix);
    List<Game> findByType (Type type);
    List<Game> findByTypeIdType(Long id);
    List<Game> findByOrderByNomGameAsc();
    List<Game> trierGamesNomsPrix();
    List<Type> getAllTypes();


}

package com.yesmine.games.repos;

import com.yesmine.games.model.Game;
import com.yesmine.games.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "rest")
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByNomGame(String nom);
    List<Game> findByNomGameContains(String nom);
    @Query("select g from Game g where g.nomGame like %?1 and g.prixGame > ?2")
    List<Game> findByNomPrix (String nom, Double prix);
    @Query("select g from Game g where g.type = ?1")
    List<Game> findByType (Type type);
    List<Game> findByTypeIdType(Long id);
    List<Game> findByOrderByNomGameAsc();
    @Query("select g from Game g order by g.nomGame ASC, g.prixGame DESC")
    List<Game> trierGamesNomsPrix ();

}

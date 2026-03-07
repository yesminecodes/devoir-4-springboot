package com.yesmine.games;

import com.yesmine.games.model.Game;
import com.yesmine.games.model.Type;
import com.yesmine.games.repos.GameRepository;
import com.yesmine.games.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

@SpringBootTest
class GamesApplicationTests {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameService gameService;
    @Test
    public void testCreateGame() {
        Game game = new Game("League Of Legends",0.0,new Date());
        gameRepository.save(game);
    }

    @Test
    public void testFindGame() {
        Game g = gameRepository.findById(20L).get();
        System.out.println(g);
    }
    
    @Test
    public void testUpdateGame() {
        Game g = gameRepository.findById(21L).get();
        g.setPrixGame(1000.0);
        gameRepository.save(g);
    }

    @Test
    public void testDeleteGame() {
        gameRepository.deleteById(20L);;
    }

    @Test
    public void testListerTousGames() {
        List<Game> games = gameRepository.findAll();
        for (Game g : games)
        {
            System.out.println(g);
        }
    }

    @Test
    public void testFindByNomGame()
    {
        List<Game> games = gameRepository.findByNomGame("FIFA");
        for (Game g : games)
        {
            System.out.println(g);
        }
    }

    @Test
    public void testFindByNomGameContains ()
    {
        List<Game> games=gameRepository.findByNomGameContains("FIFA");
        for (Game g : games)
        {
            System.out.println(g);
        } }

    @Test
    public void testfindByNomPrix()
    {
        List<Game> games = gameRepository.findByNomPrix("FIFA", 60.0);
        for (Game g : games)
        {
            System.out.println(g);
        }
    }

    @Test
    public void testfindByType()
    {
        Type type = new Type();
        type.setIdType(2L);
        List<Game> games = gameRepository.findByType(type);
        for (Game g : games)
        {
            System.out.println(g);
        }
    }

    @Test
    public void findByTypeIdType()
    {
        List<Game> games = gameRepository.findByTypeIdType(8L);
        for (Game g : games)
        {
            System.out.println(g);
        }
    }

    @Test
    public void testfindByOrderByNomGameAsc()
    {
        List<Game> games = gameRepository.findByOrderByNomGameAsc();
        for (Game g : games)
        {
            System.out.println(g);
        }
    }

    @Test
    public void testTrierGamesNomsPrix()
    {
        List<Game> games = gameRepository.trierGamesNomsPrix();
        for (Game g : games)
        {
            System.out.println(g);
        }
    }



    /*@Test
    public void testFindByNomGameContains()
    {
        Page<Game> games = gameService.getAllGamesParPage(0,2);
        System.out.println(games.getSize());
        System.out.println(games.getTotalElements());
        System.out.println(games.getTotalPages());
        games.getContent().forEach(g -> {System.out.println(g.toString());
        });
/*ou bien
for (Game p : games)
{
System.out.println(p);
} */
    }


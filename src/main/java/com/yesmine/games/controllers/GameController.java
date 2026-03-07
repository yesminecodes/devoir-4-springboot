package com.yesmine.games.controllers;

import com.yesmine.games.model.Game;
import com.yesmine.games.model.Type;
import com.yesmine.games.service.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class GameController {
    @Autowired
    GameService gameService;

    @RequestMapping("/ListeGames")
    public String listeGames(ModelMap modelMap,
                             @RequestParam(name="page", defaultValue="0") int page,
                             @RequestParam(name="size", defaultValue="5") int size) {
        Page<Game> games = gameService.getAllGamesParPage(page, size);
        modelMap.addAttribute("Games", games);
        modelMap.addAttribute("pages", new int[games.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeGames";
    }

    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap)
    {
        List<Type> types = gameService.getAllTypes();
        modelMap.addAttribute("Game", new Game());
        modelMap.addAttribute("mode", "new");
        modelMap.addAttribute("Types", types);
        return "formGame";
    }

    @RequestMapping("/saveGame")
    public String saveGame(@Valid Game Game, BindingResult bindingResult,
                              @RequestParam (name="page",defaultValue = "0") int page,
                              @RequestParam (name="size",defaultValue = "5") int size)
    {
        int currentPage;
        boolean isNew = false;
        if (bindingResult.hasErrors()) return "formGame";
        if (Game.getIdGame()==null) //ajout
            isNew=true;
        gameService.saveGame(Game);
        if (isNew) //ajout
        {
            Page<Game> games = gameService.getAllGamesParPage(page, size);
            currentPage = games.getTotalPages()-1;
        }
        else //modif
            currentPage=page;
        return ("redirect:/ListeGames?page="+currentPage+"&size="+size);
    }


    @RequestMapping("/supprimerGame")
    public String supprimerGame(@RequestParam("id") Long id,
                                @RequestParam(name="page", defaultValue="0") int page,
                                @RequestParam(name="size", defaultValue="5") int size,
                                ModelMap modelMap) {
        gameService.deleteGameById(id);
        Page<Game> games = gameService.getAllGamesParPage(page, size);
        modelMap.addAttribute("Games", games);
        modelMap.addAttribute("pages", new int[games.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeGames";
    }

    @RequestMapping("/modifierGame")
    public String editerGame(@RequestParam("id") Long id,ModelMap modelMap)
    {
        Game g= gameService.getGame(id);
        List<Type> types = gameService.getAllTypes();
        modelMap.addAttribute("Game", g);
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("Types", types);
        return "formGame";
    }

    @RequestMapping("/updateGame")
    public String updateGame(@ModelAttribute("Game") Game game,
                             @RequestParam("date") String date,
                             @RequestParam(name="page", defaultValue="0") int page,
                             @RequestParam(name="size", defaultValue="5") int size,
                             ModelMap modelMap) throws ParseException {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        game.setDateCreation(dateformat.parse(date));
        gameService.updateGame(game);
        Page<Game> games = gameService.getAllGamesParPage(page, size);
        modelMap.addAttribute("Games", games);
        modelMap.addAttribute("pages", new int[games.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeGames";
    }
}



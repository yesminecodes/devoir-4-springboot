package com.yesmine.games.controllers;

import com.yesmine.games.model.Game;
import com.yesmine.games.model.Type;
import com.yesmine.games.service.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "/")
    public String welcome() {
        return "index";
    }

    @RequestMapping("/ListeGames")
    public String listeGames(ModelMap modelMap,
                             @RequestParam(name="page", defaultValue="0") int page,
                             @RequestParam(name="size", defaultValue="5") int size) {
        Page<Game> games = gameService.getAllGamesParPage(page, size);
        modelMap.addAttribute("Games", games);
        modelMap.addAttribute("pages", new int[games.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        addSecurityAttributes(modelMap);
        return "listeGames";
    }

    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap)
    {
        List<Type> types = gameService.getAllTypes();
        modelMap.addAttribute("Game", new Game());
        modelMap.addAttribute("mode", "new");
        modelMap.addAttribute("Types", types);
        modelMap.addAttribute("isAdmin", isAdmin());
        addSecurityAttributes(modelMap);
        return "formGame";
    }

    @RequestMapping("/saveGame")
    public String saveGame(@Valid @ModelAttribute("Game") Game game,
                           BindingResult bindingResult,
                           @RequestParam(name="page", defaultValue="0") int page,
                           @RequestParam(name="size", defaultValue="5") int size,
                           ModelMap modelMap) {

        if (bindingResult.hasErrors()) {
            List<Type> types = gameService.getAllTypes();
            modelMap.addAttribute("Game", game);
            modelMap.addAttribute("Types", types);
            modelMap.addAttribute("mode", game.getIdGame() == null ? "new" : "edit");
            return "formGame";
        }

        gameService.saveGame(game);

        if (game.getIdGame() == null) {
            Page<Game> games = gameService.getAllGamesParPage(page, size);
            page = games.getTotalPages() - 1;
        }
        addSecurityAttributes(modelMap);
        return "redirect:/ListeGames?page=" + page + "&size=" + size;
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
        modelMap.addAttribute("isAdmin", isAdmin());
        addSecurityAttributes(modelMap);
        return "listeGames";
    }

    @RequestMapping("/modifierGame")
    public String editerGame(@RequestParam("id") Long id,
                             @RequestParam(name="page", defaultValue="0") int page,
                             @RequestParam(name="size", defaultValue="5") int size,
                             ModelMap modelMap)
    {
        Game g = gameService.getGame(id);
        List<Type> types = gameService.getAllTypes();
        modelMap.addAttribute("Game", g);
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("Types", types);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        addSecurityAttributes(modelMap);
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
        modelMap.addAttribute("isAdmin", isAdmin());
        addSecurityAttributes(modelMap);
        return "listeGames";
    }
    private boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));
    }

    private void addSecurityAttributes(ModelMap modelMap) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));
        boolean isAgent = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("AGENT"));
        modelMap.addAttribute("isAdmin", isAdmin);
        modelMap.addAttribute("isAgent", isAgent);
        modelMap.addAttribute("username", auth.getName());
    }
}



package com.yesmine.games;

import com.yesmine.games.model.Game;
import com.yesmine.games.model.Role;
import com.yesmine.games.model.User;
import com.yesmine.games.service.GameService;
import com.yesmine.games.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;


@SpringBootApplication
public class GamesApplication implements CommandLineRunner {


    @Autowired
    GameService gameService;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;
    public static void main(String[] args) {
        SpringApplication.run(GamesApplication.class, args);
    }

    /*@PostConstruct
    void init_users() {
//ajouter les rôles
        userService.addRole(new Role(null,"ADMIN"));
        userService.addRole(new Role(null,"AGENT"));
        userService.addRole(new Role(null,"USER"));
//ajouter les users
        userService.saveUser(new User(null,"admin","123",true,null));
        userService.saveUser(new User(null,"yesmine","123",true,null));
        userService.saveUser(new User(null,"user1","123",true,null));
//ajouter les rôles aux users
        userService.addRoleToUser("admin", "ADMIN");
        userService.addRoleToUser("yesmine", "USER");
        userService.addRoleToUser("yesmine", "AGENT");
        userService.addRoleToUser("user1", "USER");
    }*/
    @Override
    public void run(String... args) throws Exception {
        //System.out.println("Password Encoded BCRYPT :******************** ");
        //System.out.println(passwordEncoder.encode("123"));
        /*gameService.saveGame(new Game("FIFA 25", 60.0, new Date()));
        gameService.saveGame(new Game("Call of Duty", 55.0, new Date()));
        gameService.saveGame(new Game("Minecraft", 30.0, new Date()));
        gameService.saveGame(new Game("GTA V", 40.0, new Date()));
        gameService.saveGame(new Game("Fortnite", 0.0, new Date()));*/
        repositoryRestConfiguration.exposeIdsFor(Game.class);
    }

}

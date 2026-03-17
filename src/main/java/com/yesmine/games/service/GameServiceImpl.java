package com.yesmine.games.service;

import com.yesmine.games.dto.GameDTO;
import com.yesmine.games.model.Game;
import com.yesmine.games.model.Type;
import com.yesmine.games.repos.GameRepository;
import com.yesmine.games.repos.TypeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TypeRepository typeRepository;

    @Override
    public GameDTO saveGame(GameDTO g) {
        return convertEntityToDto(gameRepository.save(convertDtoToEntity(g)));
    }

    @Override
    public GameDTO updateGame(GameDTO g) {
        return convertEntityToDto(gameRepository.save(convertDtoToEntity(g)));
    }

    @Override
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public void deleteGameById(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public GameDTO getGame(Long id) {
        return convertEntityToDto(gameRepository.findById(id).get());
    }

    @Override
    public List<GameDTO> getAllGames() {
        return gameRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<GameDTO> getAllGamesParPage(int page, int size) {
        return gameRepository.findAll(PageRequest.of(page, size))
                .map(this::convertEntityToDto);
    }

    @Override
    public List<GameDTO> findByNomGame(String nom) {
        return gameRepository.findByNomGame(nom).stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GameDTO> findByNomGameContains(String nom) {
        return gameRepository.findByNomGameContains(nom).stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GameDTO> findByNomPrix(String nom, Double prix) {
        return gameRepository.findByNomPrix(nom, prix).stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GameDTO> findByType(Type type) {
        return gameRepository.findByType(type).stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GameDTO> findByTypeIdType(Long id) {
        return gameRepository.findByTypeIdType(id).stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GameDTO> findByOrderByNomGameAsc() {
        return gameRepository.findByOrderByNomGameAsc().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GameDTO> trierGamesNomsPrix() {
        return gameRepository.trierGamesNomsPrix().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    @Override
    public GameDTO convertEntityToDto(Game game) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(game, GameDTO.class);
    }

    @Override
    public Game convertDtoToEntity(GameDTO gameDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Game game = modelMapper.map(gameDTO, Game.class);
        game.setIdGame(gameDTO.getIdGame());
        game.setNomGame(gameDTO.getNomGame());
        game.setPrixGame(gameDTO.getPrixGame());
        game.setDateCreation(gameDTO.getDateCreation());
        game.setType(gameDTO.getType());
        if (gameDTO.getType() != null && gameDTO.getType().getIdType() != null) {
            Type type = typeRepository.findById(gameDTO.getType().getIdType()).orElse(null);
            game.setType(type);
        }
        return game;
    }
}
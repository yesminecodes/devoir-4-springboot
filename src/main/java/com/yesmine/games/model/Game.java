package com.yesmine.games.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGame;
    @NotNull
    @Size(min = 3,max = 30)
    private String nomGame;
    @Min(value = 0)
    @Max(value = 10000)
    private Double prixGame;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private Date dateCreation;
    @ManyToOne
    private Type type;


    public Game() {
        super();
    }
    public Game(String nomGame, Double prixGame, Date dateCreation) {
        super();
        this.nomGame = nomGame;
        this.prixGame = prixGame;
        this.dateCreation = dateCreation;
    }

    public Long getIdGame() {
        return idGame;
    }
    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }
    public String getNomGame() {
        return nomGame;
    }
    public void setNomGame(String nomGame) {
        this.nomGame = nomGame;
    }
    public Double getPrixGame() {
        return prixGame;
    }
    public void setPrixGame(Double prixGame) {
        this.prixGame = prixGame;
    }
    public Date getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type Type) {
        this.type = Type;
    }


    @Override
    public String toString() {
        return "Game [idGame=" + idGame + ", nomGame=" + nomGame + ", prixGame=" + prixGame + ", dateCreation=" + dateCreation + "]";
    }


}

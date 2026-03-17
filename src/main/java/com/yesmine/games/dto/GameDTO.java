package com.yesmine.games.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yesmine.games.model.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDTO {
    private Long idGame;
    private String nomGame;
    /*@JsonIgnore*/
    private Double prixGame;
    private Date dateCreation;
    private Type type;
    private String nomType;
}

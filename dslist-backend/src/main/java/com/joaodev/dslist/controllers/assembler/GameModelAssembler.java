package com.joaodev.dslist.controllers.assembler;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.joaodev.dslist.dto.GameDTO;
import com.joaodev.dslist.entities.Game;

@Component
public class GameModelAssembler implements RepresentationModelAssembler<Game, GameDTO> {

    @Override
    public GameDTO toModel(Game entity) {
        
        GameDTO dto = new GameDTO(entity);

        return dto;
    }

}

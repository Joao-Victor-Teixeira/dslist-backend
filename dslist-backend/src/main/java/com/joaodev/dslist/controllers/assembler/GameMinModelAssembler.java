package com.joaodev.dslist.controllers.assembler;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.joaodev.dslist.dto.GameMinDTO;
import com.joaodev.dslist.entities.Game;

@Component
public class GameMinModelAssembler implements RepresentationModelAssembler<Game, GameMinDTO> {

    @Override
    public GameMinDTO toModel(Game entity) {
        
        GameMinDTO dto = new GameMinDTO(entity);

        return dto;
    }

}

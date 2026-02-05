package com.joaodev.dslist.controllers.assembler;

import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.joaodev.dslist.dto.GameListDTO;
import com.joaodev.dslist.entities.GameList;

public class GameListModelAssembler implements RepresentationModelAssembler<GameList, GameListDTO> {

    @Override
    public GameListDTO toModel(GameList entity) {
        
        GameListDTO dto = new GameListDTO(entity);

        return dto;
    }

}

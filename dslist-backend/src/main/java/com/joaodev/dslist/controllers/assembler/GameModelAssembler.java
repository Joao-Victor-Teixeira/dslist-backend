package com.joaodev.dslist.controllers.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.joaodev.dslist.controllers.GameController;
import com.joaodev.dslist.dto.GameDTO;
import com.joaodev.dslist.entities.Game;

@Component
public class GameModelAssembler implements RepresentationModelAssembler<Game, GameDTO> {

    @Override
    public GameDTO toModel(Game entity) {
        
        GameDTO dto = new GameDTO(entity);

        dto.add(linkTo(methodOn(GameController.class).findById(entity.getId())).withSelfRel());
        dto.add(linkTo(methodOn(GameController.class).findAll(null, null)).withRel("all-games"));

        return dto;
    }

}

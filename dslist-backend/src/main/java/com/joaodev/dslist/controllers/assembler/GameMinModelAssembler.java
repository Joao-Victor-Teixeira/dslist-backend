package com.joaodev.dslist.controllers.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.joaodev.dslist.controllers.GameController;
import com.joaodev.dslist.dto.GameMinDTO;
import com.joaodev.dslist.entities.Game;

@Component
public class GameMinModelAssembler implements RepresentationModelAssembler<Game, GameMinDTO> {

    @Override
    public GameMinDTO toModel(Game entity) {
        
        GameMinDTO dto = new GameMinDTO(entity);

        dto.add(linkTo(methodOn(GameController.class).findById(entity.getId())).withSelfRel());

        dto.add(linkTo(methodOn(GameController.class).findAll(null, null)).withRel("all-games"));

        return dto;
    }

}

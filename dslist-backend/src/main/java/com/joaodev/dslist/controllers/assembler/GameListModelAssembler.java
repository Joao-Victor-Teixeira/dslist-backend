package com.joaodev.dslist.controllers.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.joaodev.dslist.controllers.GameListController;
import com.joaodev.dslist.dto.GameListDTO;
import com.joaodev.dslist.entities.GameList;

@Component
public class GameListModelAssembler implements RepresentationModelAssembler<GameList, GameListDTO> {

    @Override
    public GameListDTO toModel(GameList entity) {

        GameListDTO dto = new GameListDTO(entity);

        dto.add(linkTo(methodOn(GameListController.class).findGames(entity.getId())).withSelfRel());

        dto.add(linkTo(methodOn(GameListController.class).findAll(null, null)).withRel("all-lists"));

        return dto;
    }

}

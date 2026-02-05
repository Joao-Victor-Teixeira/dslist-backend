package com.joaodev.dslist.dto;

import org.springframework.hateoas.RepresentationModel;

import com.joaodev.dslist.entities.GameList;

public class GameListDTO extends RepresentationModel<GameListDTO> {

	private Long id;
	private String name;
	
	public GameListDTO() {
	}
	
	public GameListDTO(GameList entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}

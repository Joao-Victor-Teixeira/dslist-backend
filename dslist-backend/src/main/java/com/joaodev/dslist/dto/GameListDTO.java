package com.joaodev.dslist.dto;

import org.springframework.hateoas.RepresentationModel;

import com.joaodev.dslist.entities.GameList;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO responsável por exibir dados das listas de games")
public class GameListDTO extends RepresentationModel<GameListDTO> {

	@Schema(description = "Atributo identificador único da lista de games", example = "1L")
	private Long id;
	
	@Schema(description = "Nome da lista", example = "Aventura e RPG")
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

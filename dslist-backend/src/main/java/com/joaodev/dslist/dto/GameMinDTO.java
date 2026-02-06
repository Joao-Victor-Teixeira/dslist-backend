package com.joaodev.dslist.dto;

import org.springframework.hateoas.RepresentationModel;

import com.joaodev.dslist.entities.Game;
import com.joaodev.dslist.projections.GameMinProjection;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO responsável por exibir os dados mínimos dos Games ")
public class GameMinDTO extends RepresentationModel<GameMinDTO> {

	@Schema(description = "Atributo identificador único do game", example = "1L")
	private Long id;
	
	@Schema(description = "Título do jogo", example = "Mass Effect Trilogy")
	private String title;
	
	@Schema(description = "Ano de lançamento do jogo", example = "2012")
	private Integer year;
	
	@Schema(description = "Imagem para representar o game", example = "https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/1.png")
	private String imgUrl;
	
	@Schema(description = "Descrição curta sobre o game", example = "Lorem ipsum dolor sit amet consectetur")
	private String shortDescription;
	
	public GameMinDTO() {
	}
	
	public GameMinDTO(Game entity) {
		id = entity.getId();
		title = entity.getTitle();
		year = entity.getYear();
		imgUrl = entity.getImgUrl();
		shortDescription = entity.getShortDescription();
	}

	public GameMinDTO(GameMinProjection projection) {
		id = projection.getId();
		title = projection.getTitle();
		year = projection.getGameYear();
		imgUrl = projection.getImgUrl();
		shortDescription = projection.getShortDescription();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Integer getYear() {
		return year;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public String getShortDescription() {
		return shortDescription;
	}
}

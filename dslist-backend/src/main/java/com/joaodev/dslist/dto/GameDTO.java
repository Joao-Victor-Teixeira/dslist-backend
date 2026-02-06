package com.joaodev.dslist.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;

import com.joaodev.dslist.entities.Game;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO responsável por exibir todos os dados dos Games ")
public class GameDTO extends RepresentationModel<GameDTO> {

	@Schema(description = "Atributo identificador único do game", example = "1L")
	private Long id;
	
	@Schema(description = "Título do jogo", example = "Mass Effect Trilogy")
	private String title;
	
	@Schema(description = "Ano de lançamento do jogo", example = "2012")
	private Integer year;
	
	@Schema(description = "Gênero ou gêneros do jogo", example = "Role-playing (RPG), Shooter")
	private String genre;
	
	@Schema(description = "Plataformas compatíveis", example = "XBox, Playstation, PC")
	private String platforms;
	
	@Schema(description = "Atributo responsavel por ordenar a lista", example = "4.8")
	private Double score;
	
	@Schema(description = "Imagem para representar o game", example = "https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/1.png")
	private String imgUrl;
	
	@Schema(description = "Descrição curta sobre o game", example = "Lorem ipsum dolor sit amet consectetur")
	private String shortDescription;
	
	@Schema(description = "Descrição aprofundada sobre o game", example = "Lorem ipsum dolor sit amet consectetur")
	private String longDescription;
	
	public GameDTO() {
	}
	
	public GameDTO(Game entity) {
		BeanUtils.copyProperties(entity, this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPlatforms() {
		return platforms;
	}

	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
}

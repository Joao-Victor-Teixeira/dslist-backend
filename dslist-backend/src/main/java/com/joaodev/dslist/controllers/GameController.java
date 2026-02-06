package com.joaodev.dslist.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaodev.dslist.controllers.assembler.GameMinModelAssembler;
import com.joaodev.dslist.controllers.assembler.GameModelAssembler;
import com.joaodev.dslist.dto.GameDTO;
import com.joaodev.dslist.dto.GameMinDTO;
import com.joaodev.dslist.entities.Game;
import com.joaodev.dslist.services.GameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/games", produces = "application/json")
@Tag(name = "Games", description = "Controlador REST para exibir coleção de Games")
public class GameController {

	private final GameService gameService;

	private final GameModelAssembler assembler;

	private final GameMinModelAssembler minModelAssembler;

	public GameController(GameService gameService, GameModelAssembler assembler,
			GameMinModelAssembler minModelAssembler) {
		this.gameService = gameService;
		this.assembler = assembler;
		this.minModelAssembler = minModelAssembler;
	}

	@Operation(summary = "Retorna um game por id", description = "Retorna um JSON com um único game correspondente ao id passado na requisição", responses = {
			@ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = GameDTO.class))),
			@ApiResponse(description = "Not Found - game não encontrado", responseCode = "404", content = @Content)
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<GameDTO> findById(@PathVariable Long id) {
		Game game = gameService.findById(id);
		GameDTO dto = assembler.toModel(game);
		return ResponseEntity.ok(dto);
	}

	@Operation(summary = "Retorna todos os games de forma paginada", description = "Retorna um JSON com todos os games ", responses = {
			@ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GameMinDTO.class)))),
			@ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content)
	})
	@GetMapping
	public ResponseEntity<PagedModel<GameMinDTO>> findAll(
			Pageable pageable,
			PagedResourcesAssembler<Game> pagedResourcesAssembler) {
		Page<Game> page = gameService.findAll(pageable);
		PagedModel<GameMinDTO> pagedModel = pagedResourcesAssembler.toModel(page, minModelAssembler);

		return ResponseEntity.ok(pagedModel);
	}

}

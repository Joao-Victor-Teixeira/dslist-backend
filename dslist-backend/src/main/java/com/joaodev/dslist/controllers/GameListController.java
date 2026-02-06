package com.joaodev.dslist.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaodev.dslist.controllers.assembler.GameListModelAssembler;
import com.joaodev.dslist.dto.GameDTO;
import com.joaodev.dslist.dto.GameListDTO;
import com.joaodev.dslist.dto.GameMinDTO;
import com.joaodev.dslist.dto.ReplacementDTO;
import com.joaodev.dslist.entities.GameList;
import com.joaodev.dslist.services.GameListService;
import com.joaodev.dslist.services.GameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/lists", produces = "application/json")
@Tag(name = "GameList", description = "Controlador REST para exibir as listas de coleção de Games")
public class GameListController {

	private final GameListService gameListService;

	private final GameService gameService;

	private final GameListModelAssembler assembler;

	public GameListController(GameListService gameListService, GameService gameService,
			GameListModelAssembler assembler) {
		this.gameListService = gameListService;
		this.gameService = gameService;
		this.assembler = assembler;
	}

	@Operation(summary = "Retorna as listas games de forma paginada", description = "Retorna um JSON com todos os games ", responses = {
			@ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GameListDTO.class)))),
			@ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content)
	})
	@GetMapping
	public ResponseEntity<PagedModel<GameListDTO>> findAll(
			Pageable pageable,
			PagedResourcesAssembler<GameList> pagedResourcesAssembler) {
		Page<GameList> page = gameListService.findAll(pageable);
		PagedModel<GameListDTO> pagedModel = pagedResourcesAssembler.toModel(page, assembler);
		return ResponseEntity.ok(pagedModel);
	}

	@Operation(summary = "Retorna uma lisda de game por id", description = "Retorna um JSON com uma única lista de game correspondente ao id passado na requisição", responses = {
			@ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = GameListDTO.class))),
			@ApiResponse(description = "Not Found - game não encontrado", responseCode = "404", content = @Content)
	})
	@GetMapping(value = "/{listId}/games")
	public ResponseEntity<List<GameMinDTO>> findGames(@PathVariable Long listId) {
		List<GameMinDTO> result = gameService.findByGameList(listId);
		return ResponseEntity.ok(result);
	}

	@Operation(summary = "Resposnsável por mover um game na lista", responses = {
			@ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = ReplacementDTO.class))),
	})
	@PostMapping(value = "/{listId}/replacement")
	public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
		gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
	}

}

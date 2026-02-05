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
import com.joaodev.dslist.dto.GameListDTO;
import com.joaodev.dslist.dto.GameMinDTO;
import com.joaodev.dslist.dto.ReplacementDTO;
import com.joaodev.dslist.entities.GameList;
import com.joaodev.dslist.services.GameListService;
import com.joaodev.dslist.services.GameService;

@RestController
@RequestMapping(value = "/lists", produces = "application/json")
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

	@GetMapping
	public ResponseEntity<PagedModel<GameListDTO>> findAll(
			Pageable pageable,
			PagedResourcesAssembler<GameList> pagedResourcesAssembler) {
		Page<GameList> page = gameListService.findAll(pageable);
		PagedModel<GameListDTO> pagedModel = pagedResourcesAssembler.toModel(page, assembler);
		return ResponseEntity.ok(pagedModel);
	}

	@GetMapping(value = "/{listId}/games")
	public ResponseEntity<List<GameMinDTO>> findGames(@PathVariable Long listId) {
		List<GameMinDTO> result = gameService.findByGameList(listId);
		return ResponseEntity.ok(result);
	}

	@PostMapping(value = "/{listId}/replacement")
	public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
		gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
	}

}

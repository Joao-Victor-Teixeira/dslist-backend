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

@RestController
@RequestMapping(value = "/games", produces = "application/json")
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

	@GetMapping(value = "/{id}")
	public ResponseEntity<GameDTO> findById(@PathVariable Long id) {
		Game game = gameService.findById(id);
		GameDTO dto = assembler.toModel(game);
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	public ResponseEntity<PagedModel<GameMinDTO>> findAll(
			Pageable pageable,
			PagedResourcesAssembler<Game> pagedResourcesAssembler) {
		Page<Game> page = gameService.findAll(pageable);
		PagedModel<GameMinDTO> pagedModel = pagedResourcesAssembler.toModel(page, minModelAssembler);
		
		return ResponseEntity.ok(pagedModel);
	}

}

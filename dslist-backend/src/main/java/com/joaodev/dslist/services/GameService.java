package com.joaodev.dslist.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaodev.dslist.dto.GameMinDTO;
import com.joaodev.dslist.entities.Game;
import com.joaodev.dslist.projections.GameMinProjection;
import com.joaodev.dslist.repositories.GameRepository;
import com.joaodev.dslist.services.exceptions.ResourceNotFoundException;

@Service
public class GameService {

	private final GameRepository gameRepository;

	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}
	
	@Transactional(readOnly = true)
	public Game findById(Long listId) {
		return gameRepository.findById(listId).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
	}
	
	@Transactional(readOnly = true)
	public Page<Game> findAll(Pageable pageable) {
		return gameRepository.findAll(pageable);
		
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByGameList(Long listId) {
		List<GameMinProjection> games = gameRepository.searchByList(listId);
		return games.stream().map(GameMinDTO::new).toList();
	}

	
}

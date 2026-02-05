package com.joaodev.dslist.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaodev.dslist.entities.GameList;
import com.joaodev.dslist.projections.GameMinProjection;
import com.joaodev.dslist.repositories.GameListRepository;
import com.joaodev.dslist.repositories.GameRepository;

@Service
public class GameListService {

	private final GameListRepository gameListRepository;
	
	private final GameRepository gameRepository;
	
	public GameListService(GameListRepository gameListRepository, GameRepository gameRepository) {
		this.gameListRepository = gameListRepository;
		this.gameRepository = gameRepository;
	}

	@Transactional(readOnly = true)
	public Page<GameList> findAll(Pageable pageable) {
		return gameListRepository.findAll(pageable);
	}
	
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {

		List<GameMinProjection> list = gameRepository.searchByList(listId);

		GameMinProjection obj = list.remove(sourceIndex);
		list.add(destinationIndex, obj);

		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;

		for (int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}

	
}

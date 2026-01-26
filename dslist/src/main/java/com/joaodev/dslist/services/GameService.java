package com.joaodev.dslist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.joaodev.dslist.dto.GameDTO;
import com.joaodev.dslist.dto.GameMinDTO;
import com.joaodev.dslist.entities.Game;
import com.joaodev.dslist.repositories.GameRepository;
import com.joaodev.dslist.services.exceptions.ResourceNotFoundException;

@Service
public class GameService {

    @Autowired
    private GameRepository repository;

    public Page<GameMinDTO> findAll(Pageable pageable){
        Page<Game> result = repository.findAll(pageable);
        return result.map(x -> new GameMinDTO(x));
    }

    public GameDTO findByid(Long id){
        Game game = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new GameDTO(game);
        
    }
}

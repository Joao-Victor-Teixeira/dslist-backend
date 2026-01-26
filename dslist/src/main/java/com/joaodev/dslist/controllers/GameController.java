package com.joaodev.dslist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaodev.dslist.dto.GameDTO;
import com.joaodev.dslist.dto.GameMinDTO;
import com.joaodev.dslist.services.GameService;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    private GameService service;

    @GetMapping
    public Page<GameMinDTO> findaAll(Pageable pageable){
        return service.findAll(pageable);
    }

    @GetMapping(value = "{id}")
    public GameDTO findById(@PathVariable Long id){
        return service.findByid(id);
    }
}

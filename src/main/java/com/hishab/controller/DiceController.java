package com.hishab.controller;

import com.hishab.service.abstraction.DiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/dice")
@RestController
public class DiceController {
    private DiceService diceService;

    @GetMapping("/roll")
    public ResponseEntity letTheDiceRoll(){

        return new ResponseEntity<>(diceService.rollTheDice(), HttpStatus.OK);
    }
}
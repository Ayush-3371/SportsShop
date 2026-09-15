package com.example.controller;

import com.example.entity.Card;
import com.example.service.CardServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardServicesImpl cardService;

    public CardController() {
    }

    public CardController(CardServicesImpl cardService) {
        this.cardService = cardService;
    }

    //ADD
    @PostMapping("/add")
    Card addCard(@RequestBody Card card) {
        return cardService.addCard(card);
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    void deleteCard(@PathVariable("id") long id) {
        cardService.deleteCard(id);
    }

    //UPDATE
    @PutMapping("/update")
    Card updateCard(@RequestBody Card card) {
        return cardService.updateCard(card);
    }

    //GET
    @GetMapping("/get/{id}")
    Card getCardById(@PathVariable("id") long id) {
        return cardService.getCardById(id);
    }

    //GET ALL
    @GetMapping("/getAll")
    List<Card> getAllCard() {
        return cardService.getAllCard();
    }
}

package com.example.service;

import com.example.entity.Card;
import com.example.entity.Order;

import java.util.List;

public interface CardService {
    //ADD
    Card addCard(Card card);

    //DELETE
    void deleteCard(long id);

    //UPDATE
    Card updateCard(Card card);

    //GET
    Card getCardById(long id);

    //GET ALL
    List<Card> getAllCard();


}

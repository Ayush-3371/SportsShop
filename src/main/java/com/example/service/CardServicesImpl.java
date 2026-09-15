package com.example.service;

import com.example.entity.Card;
import com.example.entity.Order;
import com.example.exceptions.CardNotFoundException;
import com.example.repository.CardRepository;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServicesImpl implements CardService {

    @Autowired
    CardRepository cardRepo;

    @Override
    public Card addCard(Card card) {
       return cardRepo.save(card);
    }

    @Override
    public void deleteCard(long id) {
        Optional<Card> optObj = cardRepo.findById(id);
        if(optObj.isPresent()) {
            cardRepo.deleteById(id);
            System.out.println("Card details if card with id "+id+" deleted.");
            return;
        }
        throw new CardNotFoundException("Cannot perform the delete operation as no card with id " + id +" found.");
    }

    @Override
    public Card updateCard(Card card) {
        Optional<Card> optObj = cardRepo.findById(card.getId());
        if(optObj.isEmpty()) {
            throw new CardNotFoundException("Cannot find any card with id " + card.getId() + " to delete.");
        }

        return cardRepo.save(card);
    }

    @Override
    public Card getCardById(long id) {
        Optional<Card> optObj = cardRepo.findById(id);
        if(optObj.isPresent()) {
            return optObj.get();
        }

        throw new CardNotFoundException("Cannot find any card with id " + id);
    }

    @Override
    public List<Card> getAllCard() {
        return cardRepo.findAll();
    }

}

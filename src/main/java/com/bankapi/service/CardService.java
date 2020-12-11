package com.bankapi.service;

import com.bankapi.exception.RecordNotFoundException;
import com.bankapi.model.CardEntity;
import com.bankapi.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {


    private final CardRepository repository;

    @Autowired
    public CardService(CardRepository repository) {
        this.repository = repository;
    }

    public List<CardEntity> getAllAccounts() {
        return repository.findAll();
    }

    public CardEntity getAccountById(Long id) throws RecordNotFoundException {
        Optional<CardEntity> account = repository.findById(id);
        return account.orElseThrow(() -> new RecordNotFoundException("No account record exist for given id"));
    }

    public CardEntity createCard(CardEntity account) {
        return repository.createCard(account);
    }

    public CardEntity updateCard(Long id, CardEntity account) {
        return repository.updateCard(id, account);
    }

    public void deleteCardById(Long id) throws RecordNotFoundException {
        Optional<CardEntity> account = repository.findById(id);
        if (account.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No account record exist for given id");
        }
    }

}

package com.bankapi.service;

import java.util.List;
import java.util.Optional;

import com.bankapi.model.CardEntity;
import com.bankapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapi.exception.RecordNotFoundException;
import com.bankapi.model.AccountEntity;

@Service
public class AccountService {

    private final AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public List<AccountEntity> getAllAccounts() {
        return repository.findAll();
    }
 
    public AccountEntity getAccountById(Long id) throws RecordNotFoundException {
        Optional<AccountEntity> account = repository.findById(id);
        return account.orElseThrow(() -> new RecordNotFoundException("No account record exist for given id"));
    }

    public List<AccountEntity> getAccountByAcc(Long accId) throws RecordNotFoundException {
        return repository.findByAcc(accId);
    }

    public AccountEntity createAccount(AccountEntity account) {
        return repository.createAccount(account);
    }

    public AccountEntity updateAccount(Long id, AccountEntity account) {
        return repository.updateAccount(id, account);
    }

    public AccountEntity addBalance(long id, float money) throws RecordNotFoundException {
        AccountEntity accountById = getAccountById(id);
        float newBalance = accountById.getBalance()+money;
        accountById.setBalance(newBalance);
        return updateAccount(id, accountById);
    }

    public void deleteAccountById(Long id) throws RecordNotFoundException {
        Optional<AccountEntity> account = repository.findById(id);
        if (account.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No account record exist for given id");
        }
    }
}
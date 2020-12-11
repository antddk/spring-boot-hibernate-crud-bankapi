package com.bankapi.web;

import com.bankapi.exception.RecordNotFoundException;
import com.bankapi.model.AccountEntity;
import com.bankapi.model.CardEntity;
import com.bankapi.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AccountEntity>> getAllAccounts() {
        List<AccountEntity> list = service.getAllAccounts();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountEntity> getAccountById(@PathVariable("id") Long id) throws RecordNotFoundException {
        AccountEntity entity = service.getAccountById(id);
        return ResponseEntity.ok(entity);
    }
    @GetMapping("/search/{accId}")
    public ResponseEntity<List<AccountEntity>> getAccountByAccId(@PathVariable("accId") Long accId) throws RecordNotFoundException {
        List<AccountEntity> entity = service.getAccountByAcc(accId);
        return ResponseEntity.ok(entity);
    }




    
    @PostMapping
    public ResponseEntity<AccountEntity> createOrUpdateAccount(@RequestBody AccountEntity account) {
        AccountEntity updated = service.createAccount(account);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/{id}")
    public ResponseEntity<AccountEntity> createOrUpdateAccount(@PathVariable("id") Long id, @RequestBody AccountEntity account) {
        AccountEntity updated = service.updateAccount(id, account);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/change/{id}/{money}")
    public ResponseEntity<AccountEntity> addBalance(@PathVariable("id") Long id, @PathVariable("money") Float money) throws RecordNotFoundException{
        AccountEntity updated = service.addBalance(id, money);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteAccountById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteAccountById(id);
        return HttpStatus.FORBIDDEN;
    }

}
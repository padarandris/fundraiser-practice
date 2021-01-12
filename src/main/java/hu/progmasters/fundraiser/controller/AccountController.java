package hu.progmasters.fundraiser.controller;

import hu.progmasters.fundraiser.dto.AccountFormCommand;
import hu.progmasters.fundraiser.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity registerNewAccount(@RequestBody AccountFormCommand accountFormCommand) {
        accountService.create(accountFormCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

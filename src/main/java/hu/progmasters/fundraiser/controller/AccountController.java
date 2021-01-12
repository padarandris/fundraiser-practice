package hu.progmasters.fundraiser.controller;

import hu.progmasters.fundraiser.dto.AccountFormCommand;
import hu.progmasters.fundraiser.service.AccountService;
import hu.progmasters.fundraiser.validation.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;
    private AccountValidator accountValidator;

    @Autowired
    public AccountController(AccountService accountService, AccountValidator accountValidator) {
        this.accountService = accountService;
        this.accountValidator = accountValidator;
    }

    @InitBinder(value = {"accountFormCommand"})
    protected void init(WebDataBinder binder){
        binder.addValidators(accountValidator);
    }

    @PostMapping
    public ResponseEntity registerNewAccount(@RequestBody @Valid AccountFormCommand accountFormCommand,
                                             HttpServletRequest httpServletRequest) {
        accountService.create(accountFormCommand, httpServletRequest.getRemoteAddr());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

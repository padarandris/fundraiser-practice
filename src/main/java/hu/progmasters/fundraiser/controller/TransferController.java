package hu.progmasters.fundraiser.controller;

import hu.progmasters.fundraiser.domain.Transfer;
import hu.progmasters.fundraiser.dto.TransferFormCommand;
import hu.progmasters.fundraiser.service.TransferService;
import hu.progmasters.fundraiser.validation.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private TransferService transferService;
    private AccountValidator accountValidator;

    @Autowired
    public TransferController(TransferService transferService, AccountValidator accountValidator) {
        this.transferService = transferService;
        this.accountValidator = accountValidator;
    }

    @InitBinder(value = {"transferFormCommand"})
    protected void init(WebDataBinder binder) {
        binder.addValidators(accountValidator);
    }

    @PostMapping
    public ResponseEntity savetransfer(@RequestBody @Valid TransferFormCommand transferFormCommand) {
        Transfer transfer = transferService.saveTransfer(transferFormCommand);
        if (transfer == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity(HttpStatus.CREATED);
        }
    }
}

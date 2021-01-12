package hu.progmasters.fundraiser.validation;

import hu.progmasters.fundraiser.domain.Account;
import hu.progmasters.fundraiser.dto.TransferFormCommand;
import hu.progmasters.fundraiser.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class TransferValidator implements Validator {

    AccountRepository accountRepository;

    @Autowired
    public TransferValidator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TransferFormCommand.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TransferFormCommand transferFormCommand = (TransferFormCommand) o;
        Optional<Account> sender = accountRepository.findById(transferFormCommand.getSender());

        if (transferFormCommand.getSender().equals(transferFormCommand.getReceiver())){
            errors.rejectValue("receiver","sender.receiver.same");
        }
        if (transferFormCommand.getAmount() < 50 || transferFormCommand.getAmount() > 1000){
            errors.rejectValue("amount","amount.bad");
        }
        if (sender.isPresent()) {
            if (sender.get().getBalance() - transferFormCommand.getAmount() < 0) {
                errors.rejectValue("amount", "balance.low");
            }
        }
    }
}

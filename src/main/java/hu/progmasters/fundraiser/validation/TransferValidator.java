package hu.progmasters.fundraiser.validation;

import hu.progmasters.fundraiser.dto.TransferFormCommand;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TransferValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return TransferFormCommand.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TransferFormCommand transferFormCommand = (TransferFormCommand) o;
        if (transferFormCommand.getSender().equals(transferFormCommand.getReceiver())){
            errors.rejectValue("receiver","sender.receiver.same");
        }
        if (transferFormCommand.getAmount() < 50 || transferFormCommand.getAmount() > 1000){
            errors.rejectValue("amount","amount.bad");
        }
    }
}

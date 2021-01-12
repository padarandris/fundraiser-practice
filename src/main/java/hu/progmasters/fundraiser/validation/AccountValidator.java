package hu.progmasters.fundraiser.validation;

import hu.progmasters.fundraiser.dto.AccountFormCommand;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return AccountFormCommand.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AccountFormCommand accountFormCommand = (AccountFormCommand) o;
        if (accountFormCommand.getUsername().isEmpty()){
            errors.rejectValue("username","username.isempty");
        }
        if (accountFormCommand.getUsername().length() < 4 || accountFormCommand.getUsername().length() > 30){
            errors.rejectValue("username","username.size.bad");
        }
        if (accountFormCommand.getGoal().isEmpty()){
            errors.rejectValue("goal","goal.isempty");
        }
        if (accountFormCommand.getGoal().length() < 5 || accountFormCommand.getGoal().length() > 100){
            errors.rejectValue("goal","goal.size.bad");
        }

    }
}

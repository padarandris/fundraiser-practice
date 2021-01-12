package hu.progmasters.fundraiser.service;

import hu.progmasters.fundraiser.domain.Account;
import hu.progmasters.fundraiser.dto.AccountFormCommand;
import hu.progmasters.fundraiser.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public void create(AccountFormCommand accountFormCommand, String ipAddress) {
        accountRepository.save(new Account(accountFormCommand, ipAddress));
    }
}

package hu.progmasters.fundraiser.service;

import hu.progmasters.fundraiser.domain.Account;
import hu.progmasters.fundraiser.domain.Transfer;
import hu.progmasters.fundraiser.dto.TransferFormCommand;
import hu.progmasters.fundraiser.repository.AccountRepository;
import hu.progmasters.fundraiser.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class TransferService {

    private TransferRepository transferRepository;
    private AccountRepository accountRepository;

    @Autowired
    public TransferService(TransferRepository transferRepository, AccountRepository accountRepository) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
    }

    public Transfer saveTransfer(TransferFormCommand transferFormCommand) {
        Optional<Account> sender = accountRepository.findById(transferFormCommand.getSender());
        Optional<Account> receiver = accountRepository.findById(transferFormCommand.getReceiver());
        Transfer transfer = null;
        if (sender.isPresent() && receiver.isPresent()) {
            transfer = new Transfer(sender.get(), receiver.get(), transferFormCommand.getAmount());
            sender.get().setFunds(sender.get().getFunds() - transferFormCommand.getAmount());
            transferRepository.save(transfer);
        }
        return transfer;
    }
}

package springboot.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    // createAccount: Adds a new account to the database.
    public Account createAccount(Account account){
        return accountRepository.save(account);
    }

    // getAccount: Retrieves an account's details by ID.
    public Optional<Account> getAccount(Long id){
        return accountRepository.findById(id);
    }

    // deposit: Deposits a specified amount into an account.
    public Account deposit(Long id, double amount){
        Account account = getAccount(id).orElseThrow( ()-> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }

    // withdraw: Withdraws a specified amount from an account.
    public Account withdraw(Long id, double amount){
        Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance() < amount){
            throw new RuntimeException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }
}

package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Services;

import org.springframework.stereotype.Service;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Account;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Repositories.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account save(Account account){
        return accountRepository.save(account);
    }

    public boolean existAccount(Account account) {
        return accountRepository.existsByAccount(account.getAccount());
    }
}

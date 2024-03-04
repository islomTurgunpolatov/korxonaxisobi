package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Account;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Services.AccountService;

@RestController
@RequestMapping("/api")
public class AccountResource {

    private final AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account")
    public ResponseEntity save(@RequestBody Account account){
        if (accountService.existAccount(account)){
            return new ResponseEntity("already exist", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(accountService.save(account));
    }
}

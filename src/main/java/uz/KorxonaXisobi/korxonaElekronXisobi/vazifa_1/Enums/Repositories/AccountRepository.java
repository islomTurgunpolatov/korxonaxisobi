package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    Boolean existsByAccount(String account);
}

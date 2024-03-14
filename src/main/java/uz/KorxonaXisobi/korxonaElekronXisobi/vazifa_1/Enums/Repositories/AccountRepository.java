package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    Boolean existsByAccount(String account);

    @Query(value = "SELECT a.id, a.account\n" +
            "FROM account a\n" +
            "WHERE a.id IN (\n" +
            "    SELECT employee_account_id\n" +
            "    FROM client\n" +
            "    GROUP BY employee_account_id\n" +
            "    ORDER BY count(*) DESC\n" +
            "    LIMIT 1\n" +
            ")", nativeQuery = true)
    Account getActiveEmployeeAccount();

    @Query(value = "select top_3_employees()", nativeQuery = true)
    String getTopThreeEmployees();
}

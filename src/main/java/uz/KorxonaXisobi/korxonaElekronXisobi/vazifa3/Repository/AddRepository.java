package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa3.Entities.Advertising;

import java.util.List;

@Repository
public interface AddRepository extends JpaRepository<Advertising, Long> {

    Boolean existsByEmployeeAccountId(Long id);

    @Query(value = "select get_employee_spent_more_money()", nativeQuery = true)
    String getEmployeeAccount();

    @Query(value = "select count_add_within_last_month()",nativeQuery = true)
    int countAdvertisingWithinMonth();

    @Query(value = "select count_add_stopped_within_last_month()", nativeQuery = true)
    int countAdvertisingStoppedWithinLastMonth();

    List<Advertising> findByEmployeeAccountId(Long id);

    @Query(value = "select add_type_most_money_spent()", nativeQuery = true)
    String addTypeMostMoneySpent();
}

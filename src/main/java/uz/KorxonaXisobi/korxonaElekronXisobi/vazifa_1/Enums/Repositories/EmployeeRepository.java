package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("SELECT e.jshshir FROM Employee e WHERE e.jshshir=:jshshir")
    Boolean getJshshir(String jshshir);

    Boolean findByPassportNumber(String passportNumber);

    Employee findEmployeeByPassportNumber(String passportNumber);

    Employee findEmployeeByJshshir(String jshshir);

//    @Query(value = "select update_employee(:employee)", nativeQuery = true)
//    String updateEmployeeById(Employee employee,Long id);
}

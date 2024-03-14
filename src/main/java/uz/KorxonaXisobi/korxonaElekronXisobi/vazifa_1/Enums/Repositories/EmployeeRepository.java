package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Repositories;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Boolean existsEmployeeById(Long id);

    @Query("SELECT e.jshshir FROM Employee e WHERE e.jshshir=:jshshir")
    Boolean getJshshir(String jshshir);

    @Query("select e.jshshir from Employee e")
    List<String> findJshshir();

    @Query("select e.passportNumber from Employee e")
    List<String> findPassportNumber();

    @Query(value = "select get_department_info()", nativeQuery = true)
    String countEmployee();

    @Query(value = "select SUM(salary) from Employee", nativeQuery = true)
    double findSalary();

    List<Employee> findEmployeeByAge(int age);

    Employee findEmployeeByJshshir(String jshshir);
}

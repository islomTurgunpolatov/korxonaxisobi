package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa4.Services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa4.Model.DepartmentModel;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Employee;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Repositories.EmployeeRepository;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class StatisticsService {

    private final EmployeeRepository employeeRepository;

    public StatisticsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<DepartmentModel> countEmployee() {
        Gson gson = new Gson();
        String str = employeeRepository.countEmployee();
        Type list = new TypeToken<List<DepartmentModel>>() {
        }.getType();
        return gson.fromJson(str, list);
    }

    public Page<Employee> findEmployee(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public List<Employee> findByAge(int age) {
        return employeeRepository.findEmployeeByAge(age);
    }

    public double findSalary() {
        return employeeRepository.findSalary();
    }
}

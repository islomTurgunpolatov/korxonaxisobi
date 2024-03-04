package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Employee;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Employee save(Employee employee){
        employee.setPassportNumber(passwordEncoder.encode(employee.getPassportNumber()));
        employee.setJshshir(passwordEncoder.encode(employee.getJshshir()));
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Boolean checkJshshir(String jshshir){
        Employee result = employeeRepository.findEmployeeByJshshir(jshshir);

        if (result != null && verifyPassword(jshshir, result.getJshshir())){
            return true;
        }
        else return false;
    }
    private boolean verifyPassword(String plainPassword, String encodedPassword) {
        return passwordEncoder.matches(plainPassword, encodedPassword);
    }

    public Optional<Employee> findOne(Long id) {
        return employeeRepository.findById(id);
    }


    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }


//    public String update(Employee employee, Long id) {
//        return employeeRepository.updateEmployeeById(employee,id);
//    }
}

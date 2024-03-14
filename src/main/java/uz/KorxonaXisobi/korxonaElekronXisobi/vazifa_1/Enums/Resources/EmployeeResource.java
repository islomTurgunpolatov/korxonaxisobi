package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Employee;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeResource {

    private final EmployeeService employeeService;

    private final PasswordEncoder passwordEncoder;

    public EmployeeResource(EmployeeService employeeService, PasswordEncoder passwordEncoder) {

        this.employeeService = employeeService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/add/employee")
    public ResponseEntity save(@RequestBody Employee employee) {

        List<String> jshshirs = employeeService.findJshshir();
        List<String> passportNumbers = employeeService.findPassportNumber();

        int countJshshir = 0, countPassportNumber = 0;

        for (String jshshir : jshshirs) {
            if (passwordEncoder.matches(employee.getJshshir(), jshshir)) {
                countJshshir++;
                break;
            }
        }

        for (String passportNumber : passportNumbers) {
            if (passwordEncoder.matches(employee.getPassportNumber(), passportNumber)) {
                countPassportNumber++;
                break;
            }
        }
        if (countJshshir == 0 && countPassportNumber == 0){
            return ResponseEntity.ok(employeeService.save(employee));
        }
        else
            return new ResponseEntity("already exist", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/employee/list")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/one/employee/{id}")
    public ResponseEntity findOne(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findOne(id));
    }

    @DeleteMapping("/delete/employee")
    public ResponseEntity delete(@RequestParam Long id) {

        if (employeeService.exist(id)) {
            return ResponseEntity.ok("employee with "+employeeService.deleteById(id)+" id has been deleted");
        }
        else
            return new ResponseEntity("not found",HttpStatus.NOT_FOUND);
    }
}
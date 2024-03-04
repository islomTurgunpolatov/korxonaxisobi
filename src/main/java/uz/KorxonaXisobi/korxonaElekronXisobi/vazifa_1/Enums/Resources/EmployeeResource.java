package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Employee;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Services.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeResource {

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @PostMapping("/add/employee")
    public ResponseEntity save(@RequestBody Employee employee) {
        if (employeeService.checkJshshir(employee.getJshshir())) {
            return new ResponseEntity("already exist", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(employeeService.save(employee));
    }

    @GetMapping("/list")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/one/employee/{id}")
    public ResponseEntity findOne(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findOne(id));
    }

    @DeleteMapping("/delete/employee")
    public ResponseEntity delete(@RequestParam Long id) {

        employeeService.delete(id);
        return ResponseEntity.ok("deleted");
    }
}
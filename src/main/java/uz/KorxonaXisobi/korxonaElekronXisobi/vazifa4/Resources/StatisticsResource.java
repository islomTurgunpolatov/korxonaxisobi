package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa4.Resources;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa4.Services.StatisticsService;

@RestController
@RequestMapping("/api")
public class StatisticsResource {

    private final StatisticsService statisticsService;

    public StatisticsResource(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/statistics/employee/pagable")
    public ResponseEntity findEmployee(Pageable pageable) {
        return ResponseEntity.ok(statisticsService.findEmployee(pageable));
    }

    @GetMapping("/statistics/employee/count")
    public ResponseEntity countEmployee() {
        return ResponseEntity.ok(statisticsService.countEmployee());
    }

    @GetMapping("/statistics/employee/param")
    public ResponseEntity filterEmployeeByAge(@RequestParam int age) {
        return ResponseEntity.ok(statisticsService.findByAge(age));
    }

    @GetMapping("/statistics/employee/salary")
    public ResponseEntity findSalary() {
        return ResponseEntity.ok(statisticsService.findSalary());
    }
}

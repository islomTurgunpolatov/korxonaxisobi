package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Department;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Services.DepartmentService;

@RestController
@RequestMapping("/api")
public class DepartmentResource {

    private final DepartmentService departmentService;

    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/save/department")
    public ResponseEntity save(@RequestBody Department department){
        return ResponseEntity.ok(departmentService.save(department));
    }
}

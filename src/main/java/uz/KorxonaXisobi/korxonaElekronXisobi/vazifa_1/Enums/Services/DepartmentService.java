package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Services;

import org.springframework.stereotype.Service;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Department;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Repositories.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department save(Department department){
        return departmentRepository.save(department);
    }
}

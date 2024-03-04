package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}

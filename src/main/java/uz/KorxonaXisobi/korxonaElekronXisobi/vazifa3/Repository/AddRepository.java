package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa3.Entities.Advertising;

import java.util.List;

@Repository
public interface AddRepository extends JpaRepository<Advertising,Long> {

    Boolean existsByEmployeeAccountId(Long id);

    List<Advertising> findByEmployeeAccountId(Long id);
}

package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_2.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_2.Entities.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Boolean existsByEmployeeAccountId(Long id);

    List<Client> findClientByEmployeeAccountId(Long id);
}

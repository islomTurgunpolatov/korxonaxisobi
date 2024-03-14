package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_2.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_2.Entities.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Boolean existsByEmployeeAccountId(Long id);

    List<Client> findClientByEmployeeAccountId(Long id);

    @Query("select c.passportNumber from Client c")
    List<String> findPassportNumber();

    @Query("select c.jshshir from Client c")
    List<String> findJshshir();

    @Query(value = "select get_client_info();", nativeQuery = true)
    String getNumberOfClientsByDate();

    @Query(value = "select get_clients_in_last_month()", nativeQuery = true)
    int countClientsByDate();

    @Query(value = "select date from client group by date order by count(id) desc limit 1", nativeQuery = true)
    String dateMostClientsRegistered();
}

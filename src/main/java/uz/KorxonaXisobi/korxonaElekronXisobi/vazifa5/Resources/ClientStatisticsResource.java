package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa5.Resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa5.Services.ClientStatisticsService;

@RestController
@RequestMapping("/api")
public class ClientStatisticsResource {

    private final ClientStatisticsService clientStatisticsService;

    public ClientStatisticsResource(ClientStatisticsService clientStatisticsService) {
        this.clientStatisticsService = clientStatisticsService;
    }

    @GetMapping("/statistics/client/date")
    public ResponseEntity getNumberOfClientsByDate() {
        return ResponseEntity.ok(clientStatisticsService.getNumberOfClientsByDate());
    }

    @GetMapping("/statistics/active/employee")
    public ResponseEntity getActiveEmployeeAccount() {
        return ResponseEntity.ok(clientStatisticsService.getActiveEmployeeAccount());
    }

    @GetMapping("/statistics/top3-employees")
    public ResponseEntity getTopThreeEmployees(){
        return ResponseEntity.ok(clientStatisticsService.getTopThreeEmployees());
    }

    @GetMapping("/statistics/clients/last-month")
    public ResponseEntity countClientsByDate(){
        return ResponseEntity.ok(clientStatisticsService.countClientsByDate());
    }

    @GetMapping("/statistics/date/most_clients-regitered")
    public ResponseEntity dateMostClientsRegistered(){
        return ResponseEntity.ok(clientStatisticsService.dateMostClientsRegistered());
    }
}

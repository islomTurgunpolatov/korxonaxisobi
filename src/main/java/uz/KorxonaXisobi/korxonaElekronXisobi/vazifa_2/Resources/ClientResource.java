package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_2.Resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_2.Entities.Client;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_2.Services.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientResource {

    private final ClientService clientService;

    private final PasswordEncoder passwordEncoder;

    public ClientResource(ClientService clientService, PasswordEncoder passwordEncoder) {
        this.clientService = clientService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/add/client")
    public ResponseEntity save(@RequestBody Client client) {

        List<String> jshshirs = clientService.findJshshir();
        List<String> passportNumbers = clientService.findPassportNumber();

        int countJshshir = 0, countPassportNumber = 0;
        for (String jshshir : jshshirs) {
            if (passwordEncoder.matches(client.getJshshir(), jshshir)) {
                countJshshir++;
                break;
            }
        }

        for (String passportNumber : passportNumbers) {
            if (passwordEncoder.matches(client.getPassportNumber(), passportNumber)) {
                countPassportNumber++;
                break;
            }
        }

        if (countJshshir == 0 && countPassportNumber == 0) {
            return ResponseEntity.ok(clientService.save(client));
        } else
            return new ResponseEntity("already exist", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/client/list")
    public ResponseEntity findClient(@RequestParam Long id) {
        if (!clientService.existClient(id)) {
            return new ResponseEntity("not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(clientService.findClient(id));
    }

    @DeleteMapping("/delete/client/{id}")
    public ResponseEntity deleteClientById(@PathVariable Long id) {
        if (clientService.existClient(id)) {
            clientService.deleteClientById(id);
            return ResponseEntity.ok("deleted");
        }
        else
            return new ResponseEntity("not found", HttpStatus.NOT_FOUND);
    }

}

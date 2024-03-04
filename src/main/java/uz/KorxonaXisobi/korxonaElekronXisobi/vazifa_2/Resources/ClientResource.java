package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_2.Resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_2.Entities.Client;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_2.Services.ClientService;

@RestController
@RequestMapping("/api")
public class ClientResource {

    private final ClientService clientService;

    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/client")
    public ResponseEntity save(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.save(client));
    }

    @GetMapping("/client/list")
    public ResponseEntity findClient(@RequestParam Long id){
        if (!clientService.existClient(id)){
            return new ResponseEntity("already exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(clientService.findClient(id));
    }

}

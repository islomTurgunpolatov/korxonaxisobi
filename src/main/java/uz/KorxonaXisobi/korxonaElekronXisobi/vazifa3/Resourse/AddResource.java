package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa3.Resourse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa3.Entities.Advertising;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa3.Service.AddService;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AddResource {

    private final AddService addService;

    public AddResource(AddService addService) {
        this.addService = addService;
    }

    @PostMapping("/adds")
    public ResponseEntity save(@RequestBody Advertising advertising) {
        return ResponseEntity.ok(addService.save(advertising));
    }

    @GetMapping("/adds/list")
    public ResponseEntity findAdd() {
        return ResponseEntity.ok(addService.findAll());
    }

    @GetMapping("/adds/param")
    public ResponseEntity findByEmployeeAccount(@RequestParam Long id) {
        if (!addService.exist(id)) {
            return new ResponseEntity("not found", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(addService.findByEmployeeAccountId(id));
    }

    @PatchMapping("/adds/{id}")
    public ResponseEntity update(@PathVariable Long id,
                                 @RequestBody Advertising advertising){

        Optional<Advertising> optionalAdd = addService.findById(id);

        if (optionalAdd.isPresent()) {
            Advertising existingAdd = getAdvertising(advertising, optionalAdd);
            if (advertising.getEmployeeAccount() != null){
                return new ResponseEntity("permission denied", HttpStatus.BAD_REQUEST);
            }

            addService.save(existingAdd);

            return ResponseEntity.ok(existingAdd);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private static Advertising getAdvertising(Advertising advertising, Optional<Advertising> optionalAdd) {
            Advertising existingAdd = optionalAdd.get();

            if (advertising.getAddType() != null) {
                existingAdd.setAddType(advertising.getAddType());
            }
            if (advertising.getAddExpense() != null) {
                existingAdd.setAddExpense(advertising.getAddExpense());
            }
            if (advertising.getAddPeriod() != null) {
                existingAdd.setAddPeriod(advertising.getAddPeriod());
            }
            if (advertising.getAddBeginTime() != null) {
                existingAdd.setAddBeginTime(advertising.getAddBeginTime());
            }
            return existingAdd;
    }
}

package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa7.Resources;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa7.Entities.UserEntity;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa7.Services.UserEntityService;

@RestController
@RequestMapping("/api")
public class UserEntityResource {

    private final UserEntityService userEntityService;

    public UserEntityResource(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @PostMapping("/save/user")
    public ResponseEntity save(@RequestBody UserEntity user) {
        if (!userEntityService.exist(user.getUsername())) {
            if (userEntityService.checkUserPositionRole(user.getPositionRole(), user.getEmployeeId())) {
                return ResponseEntity.ok(userEntityService.save(user));
            } else
                return new ResponseEntity("something went wrong!", HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity("already exist!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/user")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(userEntityService.findAll());
    }

    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        if (userEntityService.existById(id)){
            return ResponseEntity.ok("user with "+userEntityService.deleteUserById(id)+" id has been deleted");
        }
        else
            return new ResponseEntity("not found", HttpStatus.NOT_FOUND);
    }

    }

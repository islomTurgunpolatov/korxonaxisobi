package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa7.Services;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa7.Entities.UserEntity;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa7.Entities.UserPositionRole;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa7.Repositories.UserEntityRepository;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Employee;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    public UserEntityService(UserEntityRepository userEntityRepository, EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity save(UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userEntityRepository.save(user);
    }

    public boolean exist(String username) {
        return userEntityRepository.existsByUsername(username);
    }

    public Optional<Employee> findById(Long id){
        return employeeRepository.findById(id);
    }

    public List<UserEntity> findAll() {
        return userEntityRepository.findAll();
    }

    public boolean checkUserPositionRole(UserPositionRole positionRole, Employee employee) {
        Long id = employee.getId();
        Optional<Employee> result = findById(id);
        if (result.isPresent()){
            UserPositionRole userPositionRole = result.map(Employee::getPositionRole).orElse(null);
            if (userPositionRole != null && userPositionRole.getName().equals(positionRole.getName())){
                return true;
            }
        }
        return false;
    }

    public Long deleteUserById(Long id) {
        userEntityRepository.deleteById(id);
        return id;
    }

    public boolean existById(Long id) {
        return userEntityRepository.existsUserEntityById(id);
    }
}

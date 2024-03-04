package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa3.Service;

import org.springframework.stereotype.Service;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa3.Entities.Advertising;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa3.Repository.AddRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddService {

    private final AddRepository addRepository;

    public AddService(AddRepository addRepository) {
        this.addRepository = addRepository;
    }

    public Advertising save(Advertising advertising) {
        return addRepository.save(advertising);
    }

    public List<Advertising> findAll() {
        return addRepository.findAll();
    }

    public boolean exist(Long id) {
        return addRepository.existsByEmployeeAccountId(id);
    }

    public List<Advertising> findByEmployeeAccountId(Long id){
        return addRepository.findByEmployeeAccountId(id);
    }

    public Optional<Advertising> findById(Long id){
        return addRepository.findById(id);
    }
}

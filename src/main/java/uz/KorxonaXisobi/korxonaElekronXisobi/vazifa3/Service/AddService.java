package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa3.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Advertising> findAll(Pageable pageable) {
        return addRepository.findAll(pageable);
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

    public void deleteAddById(Long id) {
        addRepository.deleteById(id);
    }
}

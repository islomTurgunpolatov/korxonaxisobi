package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa7.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa7.Entities.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity,Long> {

    Boolean existsByUsername(String username);

    Boolean existsUserEntityById(Long id);

    UserEntity findUserEntitiesByUsername(String username);

}

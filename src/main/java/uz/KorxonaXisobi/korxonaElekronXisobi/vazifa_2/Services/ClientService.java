package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_2.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Account;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_2.Entities.Client;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_2.Repositories.ClientRepository;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Client save(Client client){
        client.setPassportNumber(passwordEncoder.encode(client.getPassportNumber()));
        client.setJshshir(passwordEncoder.encode(client.getJshshir()));
        return clientRepository.save(client);
    }

    public boolean existClient(Long id){
        return clientRepository.existsByEmployeeAccountId(id);
    }

    public List<Client> findClient(Long id){
        return clientRepository.findClientByEmployeeAccountId(id);
    }
}

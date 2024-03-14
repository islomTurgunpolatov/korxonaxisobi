package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa5.Services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa5.Models.ClientInfoModel;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa5.Models.TopThreeEmployeesModel;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Account;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Repositories.AccountRepository;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_2.Repositories.ClientRepository;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class ClientStatisticsService {

    private final ClientRepository clientRepository;

    private final AccountRepository accountRepository;

    public ClientStatisticsService(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public List<ClientInfoModel> getNumberOfClientsByDate(){
        Gson gson = new Gson();
        String str = clientRepository.getNumberOfClientsByDate();

        Type type = new TypeToken<List<ClientInfoModel>>(){}.getType();

        return gson.fromJson(str,type);
    }

    public Account getActiveEmployeeAccount(){
        return accountRepository.getActiveEmployeeAccount();
    }

    public List<TopThreeEmployeesModel> getTopThreeEmployees(){
        Gson gson = new Gson();
        String str = accountRepository.getTopThreeEmployees();

        Type type = new TypeToken<List<TopThreeEmployeesModel>>(){}.getType();
        return gson.fromJson(str,type);
    }

    public int countClientsByDate(){
        return clientRepository.countClientsByDate();
    }

    public String dateMostClientsRegistered(){
        return clientRepository.dateMostClientsRegistered();
    }
}

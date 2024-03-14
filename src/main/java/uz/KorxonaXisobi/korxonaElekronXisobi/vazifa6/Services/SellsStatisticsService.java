package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa6.Services;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa3.Repository.AddRepository;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa6.Models.AccountStatisticsModel;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class SellsStatisticsService {

    private final AddRepository addRepository;

    public SellsStatisticsService(AddRepository addRepository) {
        this.addRepository = addRepository;
    }

    public List<AccountStatisticsModel> getEmployeeAccount(){
        Gson gson = new Gson();
        String str = addRepository.getEmployeeAccount();

        Type type = new TypeToken<List<AccountStatisticsModel>>(){}.getType();

       return gson.fromJson(str,type);
    }

    public String addTypeMostMoneySpent(){
        return addRepository.addTypeMostMoneySpent();
    }

    public int countAdvertisingByMonth(){
        return addRepository.countAdvertisingWithinMonth();
    }

    public int countAdvertisingStoppedWithinLastMonth(){
        return addRepository.countAdvertisingStoppedWithinLastMonth();
    }
}

package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa6.Resources;

import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa6.Services.SellsStatisticsService;

@RestController
@RequestMapping("/api")
public class SellsStatisticsResource {

    private final SellsStatisticsService sellsStatisticsService;

    public SellsStatisticsResource(SellsStatisticsService sellsStatisticsService) {
        this.sellsStatisticsService = sellsStatisticsService;
    }

    @GetMapping("/statistics/most-edd-expense")
    public ResponseEntity getEmployeeAccount() {
        return ResponseEntity.ok(sellsStatisticsService.getEmployeeAccount());
    }
    @GetMapping("/statistics/most-expensive-add-type")
    public ResponseEntity addTypeMostMoneySpent() {
        Gson gson = new Gson();
        String str = sellsStatisticsService.addTypeMostMoneySpent();
        AddTypeMostMoneySpent addTypeMostMoneySpent = gson.fromJson(str, AddTypeMostMoneySpent.class);
        return ResponseEntity.ok(addTypeMostMoneySpent);
    }

    @GetMapping("/statistics/count-adds-within-month")
    public ResponseEntity countAdvertisingByMonth(){
        return ResponseEntity.ok(sellsStatisticsService.countAdvertisingByMonth());
    }

    @GetMapping("/statistics/count-stopped-adds-last-month")
    public ResponseEntity countAdvertisingStoppedWithinLastMonth(){
        return ResponseEntity.ok(sellsStatisticsService.countAdvertisingStoppedWithinLastMonth());
    }

    static class AddTypeMostMoneySpent {
        private String add_type;

        private double total_expense_$;

        public String getAdd_type() {
            return add_type;
        }

        public void setAdd_type(String add_type) {
            this.add_type = add_type;
        }

        public double getTotal_expense_$() {
            return total_expense_$;
        }

        public void setTotal_expense_$(double total_expense_$) {
            this.total_expense_$ = total_expense_$;
        }
    }
}

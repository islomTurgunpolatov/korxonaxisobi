package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa3.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Account;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Employee;

import java.io.Serializable;

@Entity
public class Advertising implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addType;

    private String addExpense;

    private String addPeriod;

    private String addBeginTime;

    @OneToOne
    @JoinColumn(name = "employee_account_id", unique = true, nullable = false)
    private Account employeeAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddType() {
        return addType;
    }

    public void setAddType(String addType) {
        this.addType = addType;
    }

    public String getAddExpense() {
        return addExpense;
    }

    public void setAddExpense(String addExpense) {
        this.addExpense = addExpense;
    }

    public String getAddPeriod() {
        return addPeriod;
    }

    public void setAddPeriod(String addPeriod) {
        this.addPeriod = addPeriod;
    }

    public String getAddBeginTime() {
        return addBeginTime;
    }

    public void setAddBeginTime(String addBeginTime) {
        this.addBeginTime = addBeginTime;
    }

    public Account getEmployeeAccount() {
        return employeeAccount;
    }

    public void setEmployeeAccount(Account employeeAccount) {
        this.employeeAccount = employeeAccount;
    }
}

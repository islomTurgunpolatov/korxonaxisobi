package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa3.Entities;

import jakarta.persistence.*;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Account;

import java.io.Serializable;

@Entity
public class Advertising implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addType;

    @Column(name = "add_expense_$")
    private double addExpense$;

    private String addPeriod;

    private String addBeginTime;

    @ManyToOne
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

    public double getAddExpense$() {
        return addExpense$;
    }

    public void setAddExpense$(double addExpense$) {
        this.addExpense$ = addExpense$;
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

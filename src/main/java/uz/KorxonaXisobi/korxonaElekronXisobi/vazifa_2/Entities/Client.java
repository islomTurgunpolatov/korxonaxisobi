package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_2.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Account;

import java.io.Serializable;

@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientFirstName;

    private String clientLastName;

    @NotNull
    private String passportSeries;

    @NotNull
    @Size(min = 5)
    @Column(unique = true, nullable = false)
    private String passportNumber;

    @NotNull
    @Size(min = 10)
    @Column(unique = true, nullable = false)
    private String jshshir;

    @NotNull
    private String address;

    private String date;

    @ManyToOne
    private Account employeeAccount;

    public Account getEmployeeAccount() {
        return employeeAccount;
    }

    public void setEmployeeAccount(Account employeeAccount) {
        this.employeeAccount = employeeAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getJshshir() {
        return jshshir;
    }

    public void setJshshir(String jshshir) {
        this.jshshir = jshshir;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

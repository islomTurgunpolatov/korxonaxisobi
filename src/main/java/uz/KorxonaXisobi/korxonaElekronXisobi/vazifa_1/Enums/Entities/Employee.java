package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa7.Entities.UserPositionRole;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private int age;

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

    private String nation;

    private double salary;

    @NotNull
    @Column(nullable = false)
    private String address;

    @ManyToOne
    private Department department;

    @ManyToOne
    private UserPositionRole positionRole;

    @OneToOne(optional = false)
    @JoinColumn(name = "account_id", unique = true, nullable = false)
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public UserPositionRole getPositionRole() {
        return positionRole;
    }

    public void setPositionRole(UserPositionRole positionRole) {
        this.positionRole = positionRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa7.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import uz.KorxonaXisobi.korxonaElekronXisobi.vazifa_1.Enums.Entities.Employee;

import java.io.Serializable;
import java.util.Set;

@Entity
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5)
    @Column(unique = true, nullable = false)
    private String username;

    @NotNull
    @Size(min = 5)
    @Column(unique = true, nullable = false)
    private String password;

    @OneToOne(optional = false)
    @JoinColumn(name = "employee_id", unique = true, nullable = false)
    private Employee employeeId;

    @ManyToOne
    private UserPositionRole positionRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public UserPositionRole getPositionRole() {
        return positionRole;
    }

    public void setPositionRole(UserPositionRole positionRole) {
        this.positionRole = positionRole;
    }
}

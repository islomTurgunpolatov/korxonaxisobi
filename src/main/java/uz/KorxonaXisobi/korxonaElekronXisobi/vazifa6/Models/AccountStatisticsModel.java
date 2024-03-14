package uz.KorxonaXisobi.korxonaElekronXisobi.vazifa6.Models;

public class AccountStatisticsModel {

    private Long id;

    private String account;

    private double max_amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getMax_amount() {
        return max_amount;
    }

    public void setMax_amount(double max_amount) {
        this.max_amount = max_amount;
    }
}

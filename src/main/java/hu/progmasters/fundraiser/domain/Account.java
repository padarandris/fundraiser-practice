package hu.progmasters.fundraiser.domain;

import hu.progmasters.fundraiser.dto.AccountFormCommand;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "goal")
    private String goal;

    @Column(name = "balance")
    private Integer balance;

    @Column(name = "funds")
    private Integer funds;

    @OneToMany(mappedBy = "sender")
    @Column(name = "outgoing_transfer")
    private List<Transfer> outgoingTransfer = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    @Column(name = "incoming_transfer")
    private List<Transfer> incomingTransfer = new ArrayList<>();

    public Account() {
    }

    public Account(AccountFormCommand accountFormCommand, String ipAddress) {
        this.username = accountFormCommand.getUsername();
        this.ipAddress = ipAddress;
        this.goal = accountFormCommand.getGoal();
        this.balance = 5000;
        this.funds = 0;
    }

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

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getFunds() {
        return funds;
    }

    public void setFunds(Integer funds) {
        this.funds = funds;
    }

    public List<Transfer> getOutgoingTransfer() {
        return outgoingTransfer;
    }

    public void setOutgoingTransfer(List<Transfer> outgoingTransfer) {
        this.outgoingTransfer = outgoingTransfer;
    }

    public List<Transfer> getIncomingTransfer() {
        return incomingTransfer;
    }

    public void setIncomingTransfer(List<Transfer> incomingTransfer) {
        this.incomingTransfer = incomingTransfer;
    }
}

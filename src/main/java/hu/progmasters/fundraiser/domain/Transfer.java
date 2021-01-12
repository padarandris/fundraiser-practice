package hu.progmasters.fundraiser.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver")
    private Account receiver;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "transfer_date")
    private LocalDateTime time;

    public Transfer() {

    }

    public Transfer(Account sender, Account receiver, Integer amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.time = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}

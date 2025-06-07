package com.kepes.model;

import jakarta.persistence.*;


// lombok getter and setters annotations are not working
@Entity
@Table(
        name="subscription",
        //prevent duplicate entries
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"fk_id_user", "fk_id_event"})
}
)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subscription")
    private long idSubscription;

    @ManyToOne
    @JoinColumn(name = "fk_id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_id_event")
    private Event event;

    public long getIdSubscription() {
        return idSubscription;
    }

    public void setIdSubscription(long idSubscription) {
        this.idSubscription = idSubscription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
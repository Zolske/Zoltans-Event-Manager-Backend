package com.kepes.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

// lombok getter and setters annotations are not working
@Entity
// can not use "user" as name in postgres database
@Table(name="events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private long idEvent;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description_short", nullable = false)
    private String descShort;

    @Column(name = "description", nullable = false)
    private String desc;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "is_over")
    private Boolean isOver;

    public Event(){}

    public Event(String title, String descShort, String desc, LocalDate date, LocalTime time, String address) {
        this.title = title;
        this.descShort = descShort;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.address = address;
    }

    public long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(long idEvent) {
        this.idEvent = idEvent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescShort() {
        return descShort;
    }

    public void setDescShort(String descShort) {
        this.descShort = descShort;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getOver() {
        return isOver;
    }

    public void setOver(Boolean isOver) {
        this.isOver = isOver;
    }
}

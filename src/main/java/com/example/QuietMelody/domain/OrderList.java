package com.example.QuietMelody.domain;

import javax.persistence.*;

@Entity
public class OrderList {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String idOfProducts;
    private String time;
    private String date;
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public OrderList() {
    }

    public OrderList(User user, String idOfProducts, String time, String date, Status status) {
        this.author = user;
        this.idOfProducts = idOfProducts;

        this.time = time;
        this.date = date;
        this.status = status;
    }

    public Long getUserId(){ return author.getId(); }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdOfProducts() {
        return idOfProducts;
    }

    public void setIdOfProducts(String idOfProducts) {
        this.idOfProducts = idOfProducts;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
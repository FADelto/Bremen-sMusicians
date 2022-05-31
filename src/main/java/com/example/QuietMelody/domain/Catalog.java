package com.example.QuietMelody.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
/*!
  \brief Класс отвечающий за сущность пользователя в БД.
    \param id Переменная отвечающая за id пользователя
    \param username Переменная отвечающая за ник пользователя
    \param firstName Переменная отвечающая за имя пользователя
    \param secondName Переменная отвечающая за фамилию пользователя
    \param email Переменная отвечающая за электронную почту пользователя
    \param phone Переменная отвечающая за телефон пользователя
    \param password Переменная отвечающая за пароль пользователя
    \param active Переменная отвечающая за активность пользователя
    \param isAdmin() Функция проверяющая права пользователя
    \param getId() Геттер id пользователя
    \param getUsername() Геттер ника пользователя
    \param getFirstName() Геттер имени пользователя
    \param getSecondName() Геттер фамилии пользователя
    \param getEmail() Геттер электронной почты пользователя
    \param getPhone() Геттер телефона пользователя
    \param isEnable() Геттер активности пользователя


 */
@Entity
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String imgUrl;
    private long price;
    private String description;

    public Catalog(){}
    public Catalog(Long id, String name, String imgUrl, long price, String description) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
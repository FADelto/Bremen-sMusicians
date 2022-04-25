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
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String firstName;
    private String secondName;
    private String email;
    private String phone;
    private String password;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    public boolean isAdmin(){
        return  roles.contains(Role.ADMIN);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getUsername() {
        return username;
    }
    public String getSecondName() {
        return secondName;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
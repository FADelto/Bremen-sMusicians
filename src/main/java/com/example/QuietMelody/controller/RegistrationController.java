package com.example.QuietMelody.controller;

import com.example.QuietMelody.domain.Role;
import com.example.QuietMelody.domain.User;
import com.example.QuietMelody.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Objects;

/*!
    \brief Контроллер, используемый для регистрации пользователей
    \param @GetMapping() Указывает путь для перехода на страницу
    \return Новый пользователь
    \return Страница регистрации (в случае, если имя пользователя уже используется)
 */

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/registration")
    public String registration(Model model) /// Метод для вывода страницы регистрации
    {
        model.addAttribute("user", new User());
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") User user, Model model) /// Метод для создания нового пользователя в базе данных
    {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null){
            model.addAttribute("message","Такой пользователь уже есть");
            return "registration";
        }
        user.setActive(true);
        if (Objects.equals(user.getUsername(), "admin"))
            user.setRoles(Collections.singleton(Role.ADMIN)); else
            user.setRoles(Collections.singleton(Role.USER));

        userRepo.save(user);
        return "redirect:/";
    }
}

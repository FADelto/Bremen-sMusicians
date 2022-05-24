package com.example.QuietMelody.controller;

import com.example.QuietMelody.domain.Role;
import com.example.QuietMelody.domain.User;
import com.example.QuietMelody.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*!
    \brief Контроллер, используемый для хранения и изменения данных пользователей
    \param @GetMapping() Указывает путь для перехода на страницу
    \param firstName Имя пользователя
    \param secondName Фамилия пользователя
    \param password Пароль пользователя
    \param email Email пользователя
    \param phone Телефон пользователя
    \param userId Id пользователя
    \return Данные пользователя
 */

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public  String userList(Model model)/// Поиск нужного пользователя
    {
        model.addAttribute("users",userRepo.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user,Model model)/// Изменение данных пользователя
    {
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam String firstName,
            @RequestParam String secondName,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam Map<String,String> form,
            @RequestParam("userId") User user
    ) /// Сохранение данных пользователя
    {
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()){
            if(roles.contains(key))
                user.getRoles().add(Role.valueOf(key));
        }
        userRepo.save(user);

        return "redirect:/user";
    }
}

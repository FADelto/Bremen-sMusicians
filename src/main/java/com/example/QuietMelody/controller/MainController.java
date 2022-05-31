package com.example.QuietMelody.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*!
    \brief Контроллер, используемый для перехода между страницами
    \param @GetMapping() Указывает путь для перехода на страницу
    \return Главная страница веб-приложения
    \return Страница с ошибкой
 */


@Controller
public class MainController {


    @GetMapping("/")  /// Метод для вывода главной страницы
    public String index()
    {
        return "index";
    }

    @GetMapping("/error") /// Метод на случай возникновения ошибки
    public String error(){
        return "error";
    }

    @GetMapping("/workshop")
    public String WorkshopsMain() {
        return "workshop";
    }

    @GetMapping("/aboutus")
    public String AboutUsMain() {
        return "aboutus";
    }
}
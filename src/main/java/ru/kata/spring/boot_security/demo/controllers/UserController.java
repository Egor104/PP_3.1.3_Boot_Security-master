package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "usersAll";
    }

    @GetMapping("/userAdd")
    public String showFormForAdd(Model model) {
        model.addAttribute("user", new User());
        return "userAdd";
    }

    @PostMapping("/new_user")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping( "/edit/{id}")
    public String showFormForUpdate(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "userUpdate";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

//    @GetMapping("/userInfo")
//    public String showInfo() {
//
//        return "userInfo";
//    }
}
package ru.shashkov.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.shashkov.springboot.model.User;
import ru.shashkov.springboot.service.UserServiceImp;


import java.util.List;

@Controller
public class UserController {
    private UserServiceImp userService;

    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String showAllUsers(ModelMap model) {
        List<User> users = userService.listUsers();
        model.addAttribute("users", users);
        return "users";
    }
    @GetMapping("/users/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/users/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
        userService.updateUser(id,user);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}


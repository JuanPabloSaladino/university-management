package com.juanpablosaladino.university_management.controller;

import com.juanpablosaladino.university_management.model.Admin;
import com.juanpablosaladino.university_management.model.Student;
import com.juanpablosaladino.university_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "login"})
    public String index(Model model) {
        model.addAttribute("studentForm", new Student());
        model.addAttribute("adminForm", new Admin());
        return "index";
    }

    @GetMapping("/user-form")
    public String userForm(Model model) {
        model.addAttribute("studentForm", new Student());
        return "student-form";
    }

    @GetMapping(value = "users-list")
    public String usersList(Model model) {
        model.addAttribute("userList", userService.getUsers());
        return "users-list";
    }

}

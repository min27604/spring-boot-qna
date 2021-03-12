package com.codessquad.qna.controller;

import com.codessquad.qna.domain.User;
import com.codessquad.qna.domain.UserRepository;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public String create(User user) {
        userRepository.save(user);
        System.out.println(user);
        return "redirect:/users";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    @GetMapping("/{id}")
    public String profile(@PathVariable long id, Model model) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return "redirect:/users";
        }
        model.addAttribute("user", user.get());
        return "user/profile";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable long id, Model model) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return "redirect:/users";
        }
        model.addAttribute("user", user.get());
        return "user/updateForm";
    }

    @PutMapping("/{id}")
    public String modifyProfile(@PathVariable long id, User updatedUser, String oldPassword) {
        System.out.println(oldPassword);

        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent() || !user.get().matchPassword(oldPassword)) {
            return "redirect:/users";
        }

        userRepository.save(user.get().updateInfo(updatedUser));
        return "redirect:/users";
    }
}

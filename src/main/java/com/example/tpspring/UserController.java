package com.example.tpspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="")
    public @ResponseBody String addNewUser(@RequestBody CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setMdp(request.getMdp());
        user.setRole(request.getRole());

        userRepository.save(user);
        return "User Saved";
    }

    @GetMapping(path="")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}

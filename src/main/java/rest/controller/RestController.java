package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.model.Role;
import rest.model.User;
import rest.service.UserServiceImpl;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final UserServiceImpl userService;
    @Autowired
    public RestController(UserServiceImpl userService) {
        this.userService = userService;
    }

//    OK
    @GetMapping("/users")
    public List<User> getAllUsers () {
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public ResponseEntity<User> saveUser (@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    OK
    @PostMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    OK
    @GetMapping("/getUser")
    public User getUser (Principal principal) {
        return userService.getUserByName(principal.getName());
    }


}

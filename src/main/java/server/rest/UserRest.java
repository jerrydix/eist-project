package server.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.model.User;
import server.service.UserService;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserRest {

    private UserService userService;

    public UserRest(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("api/register")
    public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password) {
        if (!userService.registerUser(username, password)) {
            return ResponseEntity.badRequest().body("Username taken or password too short");
        }
        return ResponseEntity.ok("Registered successfully");
    }

    @PostMapping("api/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        userService.printUsers();
        if (!userService.authenticateUser(username, password)) {
            return ResponseEntity.badRequest().body("Wrong username or password");
        }
        return ResponseEntity.ok(username);
    }

    @PostMapping("api/logout")
    public ResponseEntity<String> logout(@RequestParam String username) {
        if (!userService.logout(username)) {
            return ResponseEntity.badRequest().body("Error");
        }
        return ResponseEntity.ok("Logged out");
    }

    @GetMapping("api/loggedInUser")
    public ResponseEntity<String> getLoggedInUser() {
        User user = userService.getLoggedInUser();
        if (user == null) {
            return ResponseEntity.ok("null");
        }
        return ResponseEntity.ok(user.getUsername());
    }

    @GetMapping("api/users/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = userService.getUserData(username);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }
}

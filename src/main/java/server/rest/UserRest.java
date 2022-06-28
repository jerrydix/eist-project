package server.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.model.User;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserRest {

    @PostMapping("api/register")
    public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password) {
        User some = User.getUser(username);
        if (some != null) {
            return ResponseEntity.badRequest().body("Username taken");
        }

        User user = new User(username, password);

        return ResponseEntity.ok("Registered successfully");
    }

    @PostMapping("api/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if (User.isLoggedIn()) {
            return ResponseEntity.badRequest().body("Broke the system");
        }
        User current = User.getUser(username);
        if (current == null || !current.authenticateUser(password)) {
            return ResponseEntity.badRequest().body("Wrong username or password");
        }

        return ResponseEntity.ok(current.getUsername());
    }

    @PostMapping("api/logout")
    public ResponseEntity<String> logout(@RequestParam String username) {
        if (!User.isLoggedIn()) {
            return ResponseEntity.badRequest().body("Broke the system");
        }
        User current = User.getUser(username);
        if (current == null || !current.isAuthenticated()) {
            return ResponseEntity.badRequest().body("Error");
        }
        current.logout();
        return ResponseEntity.ok("Logged out");
    }

    @GetMapping("api/users/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        if (!User.isLoggedIn()) {
            return ResponseEntity.badRequest().build();
        }
        User current = User.getUser(username);
        if (current == null || !current.isAuthenticated()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(current);
    }
}

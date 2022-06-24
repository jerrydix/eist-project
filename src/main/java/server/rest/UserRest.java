package server.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.model.User;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserRest {

    @PostMapping("api/register")
    public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password) {
        User some = User.getUser(username);
        if (some != null) {
            return ResponseEntity.badRequest().body("Username " + username + " is already taken");
        }

        User user = new User(username, password);

        return ResponseEntity.ok("Registered Successfully, " + user.getUsername());
    }

    @PostMapping("api/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if (User.isLoggedIn()) {
            return ResponseEntity.badRequest().body("Can't login when already logged in");
        }
        User current = User.getUser(username);
        if (current == null || !current.authenticateUser(password)) {
            return ResponseEntity.badRequest().body("Incorrect username or password, please try again");
        }
        return ResponseEntity.ok("Welcome " + current.getUsername());
    }

    @PostMapping("api/logout")
    public ResponseEntity<String> logout(@RequestParam String username) {
        if (!User.isLoggedIn()) {
            return ResponseEntity.badRequest().body("Can't logout when not logged in");
        }
        User current = User.getUser(username);
        if (current == null || !current.isAuthenticated()) {
            return ResponseEntity.badRequest().body("User doesn't exist or hasn't logged in");
        }
        current.logout();
        return ResponseEntity.ok("Thanks for using Garching Airlines");
    }
}

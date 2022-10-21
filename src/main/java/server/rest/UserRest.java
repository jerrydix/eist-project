package server.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.model.User;
import server.model.flights.surveys.Reward;
import server.service.UserService;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserRest {

    private final UserService userService;

    public UserRest(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("api/register")
    public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String flightNumber) {
        if (!userService.registerUser(username, password, email, flightNumber)) {
            return ResponseEntity.badRequest().body("Username taken or password too short");
        }
        return ResponseEntity.ok("Registered successfully");
    }

    @PostMapping("api/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        String response = userService.authenticateUser(username, password);
        return ResponseEntity.ok(response);
    }

    @PostMapping("api/logout")
    public ResponseEntity<String> logout() {
        if (!userService.logout()) {
            return ResponseEntity.badRequest().body("Error");
        }
        return ResponseEntity.ok("Logged out");
    }

    @PostMapping("api/fund")
    public ResponseEntity<User> addMoney(@RequestParam double money) {
        User user = userService.addMoney(money);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("api/defund")
    public ResponseEntity<User> removeMoney(@RequestParam double money) {
        User user = userService.removeMoney(money);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("api/exchange")
    public ResponseEntity<User> exchangeReward(@RequestBody Reward reward) {
        User user = userService.exchangeReward(reward);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("api/loggedInUser")
    public ResponseEntity<String> getLoggedInUser() {
        User user = userService.getLoggedInUser();
        if (user == null) {
            return ResponseEntity.ok("null");
        }
        return ResponseEntity.ok(user.getUsername());
    }

    @GetMapping("api/users")
    public ResponseEntity<User> getUser() {
        User user = userService.getLoggedInUser();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("api/completedSurvey")
    public ResponseEntity<Boolean> completedSurvey() {
        boolean ans = userService.completedSurvey();
        return ResponseEntity.ok(ans);
    }
}

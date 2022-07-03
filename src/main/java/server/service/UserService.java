package server.service;

import org.springframework.stereotype.Service;
import server.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<User> systemUsers;

    private boolean loggedIn;

    private User loggedInUser;

    public UserService() {
        this.systemUsers = new ArrayList<>();
        loggedIn = false;
        loggedInUser = null;
    }

    public boolean registerUser(String username, String password) {
        User user = getUser(username);
        if (user != null || password == null || password.isBlank()) {
            return false;
        }
        user = new User(username, password);
        systemUsers.add(user);
        return true;
    }

    public boolean authenticateUser(String username, String password) {
        if (loggedIn) {
            return false;
        }
        User user = getUser(username);
        if (user == null || user.isAuthenticated()) {
            return false;
        }
        loggedIn = user.authenticateUser(password);
        if (loggedIn) {
            loggedInUser = user;
        }
        return loggedIn;
    }

    public boolean logout(String username) {
        if (!loggedIn) {
            return false;
        }
        User user = getUser(username);
        if (user == null || !user.isAuthenticated()) {
            return false;
        }
        user.logout();
        loggedIn = false;
        loggedInUser = null;
        return true;
    }

    public User getUserData(String username) {
        if (!loggedIn) {
            return null;
        }
        User user = getUser(username);
        if (user == null || !user.isAuthenticated()) {
            return null;
        }
        return user;
    }

    public User getUser(String username) {
        for (User user : systemUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getSystemUsers() {
        return systemUsers;
    }

    public void printUsers() {
        for (User user : systemUsers) {
            System.out.println(user.getUsername());
        }
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}

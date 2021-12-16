package mk.ukim.finki.servlet_thymeleaf.service;

import mk.ukim.finki.servlet_thymeleaf.model.User;

public interface AuthService {

    User login(String username, String password);

    User register(String username, String password, String repeatedPassword, String name, String surname);

}

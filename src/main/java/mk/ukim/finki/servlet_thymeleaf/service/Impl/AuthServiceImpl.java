package mk.ukim.finki.servlet_thymeleaf.service.Impl;

import mk.ukim.finki.servlet_thymeleaf.model.User;
import mk.ukim.finki.servlet_thymeleaf.model.exceptions.InvalidArguementsException;
import mk.ukim.finki.servlet_thymeleaf.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.servlet_thymeleaf.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.servlet_thymeleaf.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.servlet_thymeleaf.repository.impl.InMemoryUserRepository;
import mk.ukim.finki.servlet_thymeleaf.repository.jpa.UserRepository;
import mk.ukim.finki.servlet_thymeleaf.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User login(String username, String password) {

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {

            throw new InvalidArguementsException();

        }

        return userRepository.findByUsernameAndPassword(username,password)
                .orElseThrow(InvalidUserCredentialsException::new);

    }

    @Override
    public User register(String username, String password, String repeatedPassword, String name, String surname) {

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArguementsException();
        }

        if(!password.equals(repeatedPassword)){
            throw new PasswordsDoNotMatchException();
        }

        if(this.userRepository.findByUsername(username).isPresent()
                || !this.userRepository.findByUsername(username).isEmpty()){
            throw new UsernameAlreadyExistsException(username);
        }

        User user = new User(name,surname,username,password);
        return userRepository.save(user);
    }
}

package mk.ukim.finki.servlet_thymeleaf.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException {

    public PasswordsDoNotMatchException(){
        super("Passwords do not match exception");
    }

}

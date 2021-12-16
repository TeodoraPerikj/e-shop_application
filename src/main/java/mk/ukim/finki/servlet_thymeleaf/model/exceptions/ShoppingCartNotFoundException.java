package mk.ukim.finki.servlet_thymeleaf.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingCartNotFoundException extends RuntimeException{

    public ShoppingCartNotFoundException(Long cartId) {
        super(String.format("Shopping cart with id %d was not found",cartId));
    }
}

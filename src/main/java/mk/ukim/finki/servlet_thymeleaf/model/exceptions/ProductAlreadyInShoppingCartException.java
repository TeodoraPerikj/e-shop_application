package mk.ukim.finki.servlet_thymeleaf.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ProductAlreadyInShoppingCartException extends RuntimeException{

    public ProductAlreadyInShoppingCartException(Long productId, String username) {
        super(String.format("Product with id %d already exists in shopping cart for user with username"
                ,productId,username));
    }
}

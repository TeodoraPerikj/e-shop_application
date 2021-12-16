package mk.ukim.finki.servlet_thymeleaf.repository.impl;

import mk.ukim.finki.servlet_thymeleaf.bootstrap.DataHolder;
import mk.ukim.finki.servlet_thymeleaf.model.ShoppingCart;
import mk.ukim.finki.servlet_thymeleaf.model.enumerations.ShoppingCartStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryShoppingCartRepository {

    public Optional<ShoppingCart> findById(Long id){
        return DataHolder.shoppingCarts.stream()
                .filter(cart -> cart.getId().equals(id))
                .findFirst();
    }

    public Optional<ShoppingCart> findByUsernameAndStatus(String username, ShoppingCartStatus status){
        return DataHolder.shoppingCarts.stream()
                .filter(cart -> cart.getUser().getUsername().equals(username)
                        && cart.getStatus().equals(status))
                .findFirst();
    }

    public ShoppingCart save(ShoppingCart cart){
        DataHolder.shoppingCarts
                .removeIf(cart1 -> cart1.getUser().getUsername().equals(cart.getUser().getUsername()));
        DataHolder.shoppingCarts.add(cart);
        return cart;
    }

}

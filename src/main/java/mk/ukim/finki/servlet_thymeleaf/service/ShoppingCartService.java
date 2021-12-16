package mk.ukim.finki.servlet_thymeleaf.service;

import mk.ukim.finki.servlet_thymeleaf.model.Product;
import mk.ukim.finki.servlet_thymeleaf.model.ShoppingCart;
import mk.ukim.finki.servlet_thymeleaf.model.User;

import java.util.List;

public interface ShoppingCartService {

    List<Product> findAllProductsInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username, Long productId);

}

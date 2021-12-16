package mk.ukim.finki.servlet_thymeleaf.repository.jpa;

import mk.ukim.finki.servlet_thymeleaf.model.ShoppingCart;
import mk.ukim.finki.servlet_thymeleaf.model.User;
import mk.ukim.finki.servlet_thymeleaf.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {

    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);

}

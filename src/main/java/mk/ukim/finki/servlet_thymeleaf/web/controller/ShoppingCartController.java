package mk.ukim.finki.servlet_thymeleaf.web.controller;

import mk.ukim.finki.servlet_thymeleaf.model.ShoppingCart;
import mk.ukim.finki.servlet_thymeleaf.model.User;
import mk.ukim.finki.servlet_thymeleaf.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest request, Model model){

        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error",error);
        }

        User user = (User) request.getSession().getAttribute("user");
        ShoppingCart cart = this.shoppingCartService.getActiveShoppingCart(user.getUsername());

        model.addAttribute("products",this.shoppingCartService
                .findAllProductsInShoppingCart(cart.getId()));

        model.addAttribute("bodyContent","shopping-cart");
        return "master-template";
    }

    @PostMapping("/add-product/{id}")
    public String addProductToShoppingCart(@PathVariable Long id,
                                           HttpServletRequest request){
        try{
            User user = (User) request.getSession().getAttribute("user");
            ShoppingCart shoppingCart = this.shoppingCartService
                    .addProductToShoppingCart(user.getUsername(),id);

            return "redirect:/shopping-cart";
        }catch (RuntimeException exception){
            return "redirect:/shopping-cart?error="+exception.getMessage();
        }
    }
}

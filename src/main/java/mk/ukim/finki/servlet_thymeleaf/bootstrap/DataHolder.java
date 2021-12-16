package mk.ukim.finki.servlet_thymeleaf.bootstrap;

import mk.ukim.finki.servlet_thymeleaf.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Category> categories = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();

//    @PostConstruct
//    public void init(){
//
//        categories.add(new Category("Software","Software description"));
//        categories.add(new Category("Films","Films description"));
//
//        users.add(new User("Tea","Perikj","teodora.perikj","tp"));
//        users.add(new User("Mihi","Perikj","mihaela.perikj","mp"));
//
//        Manufacturer manufacturer = new Manufacturer("Nike","NY NY");
//        manufacturers.add(manufacturer);
//        Category category = new Category("Sport","Sport description");
//        categories.add(category);
//        products.add(new Product("Ball 1",235.8, 7, category, manufacturer));
//        products.add(new Product("Ball 2",235.8, 7, category, manufacturer));
//        products.add(new Product("Ball 3",235.8, 7, category, manufacturer));
//
//    }

}

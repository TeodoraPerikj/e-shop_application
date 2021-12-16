package mk.ukim.finki.servlet_thymeleaf.repository.impl;

import mk.ukim.finki.servlet_thymeleaf.bootstrap.DataHolder;
import mk.ukim.finki.servlet_thymeleaf.model.Category;
import mk.ukim.finki.servlet_thymeleaf.model.Manufacturer;
import mk.ukim.finki.servlet_thymeleaf.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository {

    public List<Product> findAll(){
        return DataHolder.products;
    }

    public Optional<Product> findById(Long id){
        return DataHolder.products
                .stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    public Optional<Product> findByName(String name){
        return DataHolder.products
                .stream()
                .filter(product -> product.getName().equals(name))
                .findFirst();
    }

    public Optional<Product> save(String name, Double price, Integer quantity,
                                  Category category, Manufacturer manufacturer){

        DataHolder.products.removeIf(product -> product.getName().equals(name));
        Product product = new Product(name, price, quantity, category, manufacturer);
        DataHolder.products.add(product);
        return Optional.of(product);

    }

    public void deleteById(Long id){
        DataHolder.products.removeIf(product -> product.getId().equals(id));
    }

}

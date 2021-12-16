package mk.ukim.finki.servlet_thymeleaf.service;

import mk.ukim.finki.servlet_thymeleaf.model.Category;

import java.util.List;

public interface CategoryService {

    Category create(String name, String description);

    Category update(String name, String description);

    List<Category> listCategories();

    List<Category> searchCategories(String searchText);

    void delete(String name);

}

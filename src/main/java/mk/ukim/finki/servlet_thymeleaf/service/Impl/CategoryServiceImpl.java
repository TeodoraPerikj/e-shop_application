package mk.ukim.finki.servlet_thymeleaf.service.Impl;

import mk.ukim.finki.servlet_thymeleaf.model.Category;
import mk.ukim.finki.servlet_thymeleaf.repository.impl.InMemoryCategoryRepository;
import mk.ukim.finki.servlet_thymeleaf.repository.jpa.CategoryRepository;
import mk.ukim.finki.servlet_thymeleaf.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;

    }

    @Override
    public Category create(String name, String description) {

        if (name == null || description == null || name.isEmpty() || description.isEmpty()) {
            return null;
        }

        Category category = new Category(name, description);
        categoryRepository.save(category);
        return category;

    }

    @Override
    public Category update(String name, String description) {

        if (name == null || description == null || name.isEmpty() || description.isEmpty()) {
            return null;
        }

        Category category = new Category(name, description);
        categoryRepository.save(category);
        return category;

    }

    @Override
    public List<Category> listCategories() {

        return categoryRepository.findAll();

    }

    @Override
    public List<Category> searchCategories(String searchText) {

        if (searchText == null || searchText.isEmpty()) {
            return null;
        }

        return categoryRepository.findAllByNameLike(searchText);

    }

    @Override
    public void delete(String name) {

        if (name == null || name.isEmpty()) {
            return;
        }

        categoryRepository.deleteByName(name);

    }
}

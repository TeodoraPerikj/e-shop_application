package mk.ukim.finki.servlet_thymeleaf.repository.impl;

import mk.ukim.finki.servlet_thymeleaf.bootstrap.DataHolder;
import mk.ukim.finki.servlet_thymeleaf.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryCategoryRepository {

    public List<Category> findAll() {

        return DataHolder.categories;

    }

    public Category save(Category category) {

        if (category == null || category.getName().isEmpty() || category.getDescription().isEmpty()) {
            return null;
        }

        DataHolder.categories.removeIf(category1 -> category1.getName().equals(category.getName())
                || category1.getDescription().equals(category.getDescription()));
        DataHolder.categories.add(category);

        return category;
    }

    public Optional<Category> findByName(String name) {

        return DataHolder.categories.stream().filter(category -> category.getName().equals(name)).findFirst();

    }

    public Optional<Category> findById(Long id) {

        return DataHolder.categories
                .stream()
                .filter(category -> category.getId().equals(id))
                .findFirst();

    }

    public List<Category> search(String searchText) {

        if (searchText == null || searchText.isEmpty()) {
            return null;
        }

        return DataHolder.categories.stream().filter(category -> category.getName().contains(searchText)
                || category.getDescription().contains(searchText)).collect(Collectors.toList());

    }

    public void delete(String name) {

        if (name == null || name.isEmpty()) {
            return;
        }

        DataHolder.categories.removeIf(category -> category.getName().equals(name));

    }

}

package mk.ukim.finki.servlet_thymeleaf.service.Impl;

import mk.ukim.finki.servlet_thymeleaf.model.Category;
import mk.ukim.finki.servlet_thymeleaf.model.Manufacturer;
import mk.ukim.finki.servlet_thymeleaf.model.Product;
import mk.ukim.finki.servlet_thymeleaf.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.servlet_thymeleaf.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.servlet_thymeleaf.repository.impl.InMemoryCategoryRepository;
import mk.ukim.finki.servlet_thymeleaf.repository.impl.InMemoryManufacturerRepository;
import mk.ukim.finki.servlet_thymeleaf.repository.impl.InMemoryProductRepository;
import mk.ukim.finki.servlet_thymeleaf.repository.jpa.CategoryRepository;
import mk.ukim.finki.servlet_thymeleaf.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.servlet_thymeleaf.repository.jpa.ProductRepository;
import mk.ukim.finki.servlet_thymeleaf.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Double price, Integer quantity,
                                  Long categoryId, Long manufacturerId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        this.productRepository.deleteByName(name);
        return Optional.of(new Product(name, price, quantity, category, manufacturer));

    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}

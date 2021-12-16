package mk.ukim.finki.servlet_thymeleaf.repository.jpa;

import mk.ukim.finki.servlet_thymeleaf.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findAllByNameLike(String name);

    void deleteByName(String name);

}

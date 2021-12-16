package mk.ukim.finki.servlet_thymeleaf.repository.jpa;

import mk.ukim.finki.servlet_thymeleaf.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {

    void deleteById(Long id);

    //Optional<Manufacturer> saveByNameAndAddress(String name, String address);
}

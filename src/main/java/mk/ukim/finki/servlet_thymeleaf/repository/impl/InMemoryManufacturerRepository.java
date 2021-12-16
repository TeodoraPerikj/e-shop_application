package mk.ukim.finki.servlet_thymeleaf.repository.impl;

import mk.ukim.finki.servlet_thymeleaf.bootstrap.DataHolder;
import mk.ukim.finki.servlet_thymeleaf.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {

    public List<Manufacturer> findAll(){
        return DataHolder.manufacturers;
    }

    public Optional<Manufacturer> findById(Long id){
        return DataHolder.manufacturers
                .stream()
                .filter(man->man.getId().equals(id))
                .findFirst();
    }

    public Optional<Manufacturer> save(String name, String address){
        DataHolder.manufacturers.removeIf(manufacturer -> manufacturer.getName().equals(name));
        Manufacturer manufacturer = new Manufacturer(name, address);
        DataHolder.manufacturers.add(manufacturer);

        return Optional.of(manufacturer);
    }

    public boolean deleteById(Long id){
        return DataHolder.manufacturers.removeIf(manufacturer -> manufacturer.getId().equals(id));
    }

}

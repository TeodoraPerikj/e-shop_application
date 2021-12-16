package mk.ukim.finki.servlet_thymeleaf.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "manufacturer_address")
    private String address;

    public Manufacturer(){

    }

    public Manufacturer(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
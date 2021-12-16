package mk.ukim.finki.servlet_thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ServletThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServletThymeleafApplication.class, args);
    }

}

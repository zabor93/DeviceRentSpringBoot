package pl.javastart.devicerentboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javastart.devicerentboot.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}

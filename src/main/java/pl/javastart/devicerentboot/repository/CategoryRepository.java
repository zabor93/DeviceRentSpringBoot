package pl.javastart.devicerentboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.devicerentboot.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}

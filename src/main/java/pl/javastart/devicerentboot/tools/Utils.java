package pl.javastart.devicerentboot.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.devicerentboot.category.Category;
import pl.javastart.devicerentboot.category.CategoryRepository;

import java.util.List;

@Service
public class Utils {

    CategoryRepository categoryRepository;

    @Autowired
    public Utils(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public boolean checkUnique(String s) {
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(s)) {
                return false;
            }
        }
        return true;
    }

}

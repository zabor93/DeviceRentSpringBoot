package pl.javastart.devicerentboot.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.devicerentboot.tools.Utils;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CategoryController  {

    private Scanner scanner;
    private CategoryRepository categoryRepository;
    private Utils utils;

    
    @Autowired
    public CategoryController(Scanner scanner, CategoryRepository categoryRepository, Utils utils) {
        this.scanner = scanner;
        this.categoryRepository = categoryRepository;
        this.utils = utils;
    }


    public void addCategory() {
        Category category = new Category();
        System.out.println("Podaj nazwe kategori:");
        String s = scanner.nextLine();
        boolean t = false;
        t = utils.checkUnique(s);
        if (t == true) {
            category.setName(scanner.nextLine());
            scanner.nextLine();
            System.out.println("Podaj opis kategori:");
            category.setDescription(scanner.nextLine());
            categoryRepository.save(category);

        } else {
            System.out.println("Podana kategoria juz istnieje");
        }
    }




    public void removeCategory() {
        System.out.println("Podaj id kategori ktore chcesz usunac:");
        Long id = scanner.nextLong();
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            category.ifPresent(categoryRepository::delete);
        } else {
            System.out.println("Nie ma takiej kategorii");
        }
    }

    public CategoryController() {
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}

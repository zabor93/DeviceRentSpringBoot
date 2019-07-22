package pl.javastart.devicerentboot.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CategoryController {

    private Scanner scanner;
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(Scanner scanner, CategoryRepository categoryRepository) {
        this.scanner = scanner;
        this.categoryRepository = categoryRepository;
    }


    public void addCategory() {
        Category category = new Category();
        System.out.println("Podaj nazwe kategori:");
        category.setName(scanner.nextLine());
        scanner.nextLine();
        System.out.println("Podaj opis kategori:");
        category.setDescription(scanner.nextLine());
        categoryRepository.save(category);
    }


    public void removeCategory() {
        System.out.println("Podaj id kategori ktore chcesz usunac:");
        Long id =scanner.nextLong();
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            category.ifPresent(categoryRepository::delete);
        }
        else {
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

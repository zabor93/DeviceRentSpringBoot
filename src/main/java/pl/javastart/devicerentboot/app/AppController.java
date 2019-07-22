package pl.javastart.devicerentboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.javastart.devicerentboot.category.Category;
import pl.javastart.devicerentboot.customer.Customer;
import pl.javastart.devicerentboot.device.Device;
import pl.javastart.devicerentboot.category.CategoryRepository;
import pl.javastart.devicerentboot.customer.CustomerRepository;
import pl.javastart.devicerentboot.device.DeviceRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Scanner;

@Controller
public class AppController {

    private static final int ADD_DEVICE = 1;

    private static final int ADD_CATEGORY = 2;

    private static final int ADD_CUSTOMER = 3;

    private static final int RENT_DEVICE = 4;

    private static final int REMOVE_DEVICE = 5;

    private static final int REMOVE_CATEGORY = 6;

    private static final int REMOVE_CUSTOMER = 7;

    private static final int CLOSE_APP = 8;

    private static final int UNDEFINIED = -1;

    Scanner scanner;
    CategoryRepository categoryRepository;
    CustomerRepository customerRepository;
    DeviceRepository deviceRepository;

    @Autowired
    public AppController(Scanner scanner, CategoryRepository categoryRepository, CustomerRepository customerRepository, DeviceRepository deviceRepository) {
        this.scanner = scanner;
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.deviceRepository = deviceRepository;
    }


    public void mainLoop() {
        System.out.println("Opcje:");
        int option = UNDEFINIED;
        while (option != CLOSE_APP) {
            printMenu();
            option = chooseOption();
            executeOption(option);
        }
    }

    private void executeOption(int option) {

        switch (option) {
            case ADD_DEVICE:
                addDevice();
                break;
            case ADD_CATEGORY:
                addCategory();
                break;
            case ADD_CUSTOMER:
                addCustomer();
                break;
            case RENT_DEVICE:
                rent();
                break;
            case REMOVE_DEVICE:
                removeDevice();
                break;
            case REMOVE_CATEGORY:
                removeCategory();
                break;
            case REMOVE_CUSTOMER:
                removeCustomer();
                break;
            case CLOSE_APP:
                closeApp();
                break;
            default:
                System.out.println("Opcja niepoprawna");
        }

    }

    private void closeApp() {
        System.out.println("Bye bye");
        System.exit(0);

    }

    private void removeCustomer() {

    }

    private void removeCategory() {
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

    private void removeDevice() {

    }


    private void rent() {
        System.out.println("Podaj id uzytkownika");
        Long id = scanner.nextLong();
        Optional<Customer> customer = customerRepository.findById(id);
        System.out.println("Podaj id urzadzenia");
        Long id2 = scanner.nextLong();
        Optional<Device> device = deviceRepository.findById(id2);
        if (device.isPresent() && customer.isPresent()) {
            device.ifPresent(device1 -> {
                if (device1.getQuantity() > device1.getCustomers().size()) {
                    device1.addCustomer(customer.get());
                } else {
                    System.out.println("BRAK");
                }
            });
        }
    }

    private void addCustomer() {
        Customer customer = new Customer();
        System.out.println("Podaj nazwe klienta");
        customer.setFirstName(scanner.nextLine());
        scanner.nextLine();
        System.out.println("Podaj nazwisko klienta");
        customer.setLastName(scanner.nextLine());
        System.out.println("Podaj iD klienta:");
        customer.setIdNumber(scanner.next());
        System.out.println("Podaj pesel");
        customer.setPesel(scanner.next());
        customerRepository.save(customer);
    }

    private void addCategory() {
        Category category = new Category();
        System.out.println("Podaj nazwe kategori:");
        category.setName(scanner.nextLine());
        scanner.nextLine();
        System.out.println("Podaj opis kategori:");
        category.setDescription(scanner.nextLine());
        categoryRepository.save(category);
    }

    @Transactional
    public void addDevice() {
        Device device = new Device();
        System.out.println("Podaj nazwe urządzenia");
        String str1 = scanner.nextLine();
        device.setName(str1);
        scanner.nextLine();
        System.out.println("Podaj opis urządzenia");
        String str2 = scanner.nextLine();
        device.setDescription(str2);
        System.out.println("Podaj cene urządzenia");
        device.setPrice(scanner.nextDouble());
        System.out.println("Podaj ilość(sztuki) urządzenia");
        device.setQuantity(scanner.nextInt());
        System.out.println("Podaj Id kategori");
        Long id = scanner.nextLong();
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            category.ifPresent(device::setCategory);
        } else {
            System.out.println("Klient o danym id nie istnieje.");
            System.out.println(id);
            category.ifPresent(device::setCategory);
        }
        System.out.println("Dodano urzadzenie");
        deviceRepository.save(device);
    }

    private int chooseOption() {
        int option = scanner.nextInt();
        if (option > 8 || option < 1) {
            return UNDEFINIED;
        } else {
            return option;
        }
    }

    private void printMenu() {
        System.out.println("1-Dodaj urządzenie");
        System.out.println("2-Dodaj kategorię");
        System.out.println("3-Dodaj klienta");
        System.out.println("4-Wypozycz urzadzenie");
        System.out.println("5-Usun urządzenie");
        System.out.println("6-Usun kategorie");
        System.out.println("7-Usun klienta");
        System.out.println("8-Koniec");
        System.out.println("Podaj ID opcji:");
    }


}

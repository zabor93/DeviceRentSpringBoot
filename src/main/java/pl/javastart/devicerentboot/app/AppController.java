package pl.javastart.devicerentboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.javastart.devicerentboot.category.Category;
import pl.javastart.devicerentboot.category.CategoryController;
import pl.javastart.devicerentboot.customer.Customer;
import pl.javastart.devicerentboot.customer.CustomerController;
import pl.javastart.devicerentboot.device.Device;
import pl.javastart.devicerentboot.category.CategoryRepository;
import pl.javastart.devicerentboot.customer.CustomerRepository;
import pl.javastart.devicerentboot.device.DeviceController;
import pl.javastart.devicerentboot.device.DeviceRepository;
import pl.javastart.devicerentboot.rent.RentController;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

@Controller
public class AppController {


    private Scanner scanner;
    private CategoryController categoryController;
    private CustomerController customerController;
    private DeviceController deviceController;
    private RentController rentController;

    @Autowired
    public AppController(Scanner scanner, CategoryController categoryController, CustomerController customerController, DeviceController deviceController, RentController rentController) {
        this.scanner = scanner;
        this.categoryController = categoryController;
        this.customerController = customerController;
        this.deviceController = deviceController;
        this.rentController = rentController;
    }


    public void mainLoop() {
      Options options;

        do {
            printOptions();
            options = readOption();
            executeOption(options);
        } while (options != Options.EXIT);


    }

    private Options readOption() {
        boolean correctOptionSelected = false;
        Options option = null;
        while(!correctOptionSelected) {
            System.out.println("Podaj ID opcji:");
            int optionId = scanner.nextInt();
            scanner.nextLine();
            try {
                option = Options.numberToCategory(optionId);
                correctOptionSelected = true;
            } catch(InvalidOptionException e) {
                System.err.println(e.getMessage());
            }
        }
        return option;
    }


    private void printOptions(){
        System.out.println("Opcje");
        Arrays.stream(Options.values()).forEach(System.out::println);
    }

    private void executeOption(Options option) {

        switch (option) {
            case ADD_DEVICE:
                deviceController.createDevice();
                break;
            case ADD_CATEGORY:
                categoryController.addCategory();
                break;
            case ADD_CUSTOMER:
                customerController.addCustomer();
                break;
            case RENT_DEVICE:
                rentController.rentDeviceToCustomer();
                break;
            case REMOVE_DEVICE:
                deviceController.removeDevice();
                break;
            case REMOVE_CATEGORY:
                categoryController.removeCategory();
                break;
            case REMOVE_CUSTOMER:
                customerController.removeCustomer();
                break;
            case EXIT:
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

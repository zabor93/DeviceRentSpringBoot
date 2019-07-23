package pl.javastart.devicerentboot.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.devicerentboot.category.CategoryRepository;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CustomerController {

    private CustomerRepository customerRepository;
    private Scanner scanner;

    @Autowired
    public CustomerController(Scanner scanner, CustomerRepository customerRepository) {
        this.scanner = scanner;
        this.customerRepository = customerRepository;
    }


    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }


    public void addCustomer() {
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


    public void removeCustomer() {
        System.out.println("Podaj ID klienta");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customer.ifPresent(customerRepository::delete);
        }
        else
        {
            System.out.println("Nie ma takiego klienta");
        }

    }


}

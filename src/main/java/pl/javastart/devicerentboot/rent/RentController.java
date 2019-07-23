package pl.javastart.devicerentboot.rent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.devicerentboot.customer.Customer;
import pl.javastart.devicerentboot.customer.CustomerRepository;
import pl.javastart.devicerentboot.device.Device;
import pl.javastart.devicerentboot.device.DeviceRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Scanner;

@Service
public class RentController {

    private Scanner scanner;
    private DeviceRepository deviceRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public RentController(Scanner scanner, DeviceRepository deviceRepository, CustomerRepository customerRepository) {
        this.scanner = scanner;
        this.deviceRepository = deviceRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void rentDeviceToCustomer() {
        try {
            rent();
        } catch (RentException e) {
            System.err.println(e.getMessage());
        }
    }

    private void rent() {
        System.out.println("Podaj ID klienta:");
        long customerId = scanner.nextLong();
        System.out.println("Podaj ID urządzenia:");
        long deviceId = scanner.nextLong();
        Optional<Customer> customer = customerRepository.findById(customerId);
        Optional<Device> device = deviceRepository.findById(deviceId);
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
}

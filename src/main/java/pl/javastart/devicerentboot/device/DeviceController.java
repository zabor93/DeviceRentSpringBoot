package pl.javastart.devicerentboot.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.devicerentboot.category.Category;
import pl.javastart.devicerentboot.category.CategoryNotFoundException;
import pl.javastart.devicerentboot.category.CategoryRepository;
import pl.javastart.devicerentboot.tools.Utils;

import javax.rmi.CORBA.Util;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class DeviceController {

    private Scanner scanner;
    private DeviceRepository deviceRepository;
    private CategoryRepository categoryRepository;
    private Utils utils;

    @Autowired
    public DeviceController(Scanner scanner, DeviceRepository deviceRepository, CategoryRepository categoryRepository, Utils utils) {
        this.scanner = scanner;
        this.deviceRepository = deviceRepository;
        this.categoryRepository = categoryRepository;
        this.utils = utils;
    }


    @Transactional
    public void createDevice() {
        try {
            Device device = readDevice();
            deviceRepository.save(device);
            System.out.println("Dodano urządzenie");
            System.out.println(device);
        } catch (CategoryNotFoundException e) {
            System.err.println("Urządzenia nie dodano. " + e.getMessage());
        }
    }

    private Device readDevice() {
        Device device = new Device();
        System.out.println("Nazwa urządzenia:");
        device.setName(scanner.nextLine());
        System.out.println("Opis urządzenia:");
        device.setDescription(scanner.nextLine());
        System.out.println("Cena urządzenia:");
        device.setPrice(scanner.nextDouble());
        System.out.println("Ilość(szt) urządzenia:");
        device.setQuantity(scanner.nextInt());
        System.out.println("Podaj nazwe kategori:");
        String categoryName = scanner.next();

        Optional<Category> category = categoryRepository.findByNameIgnoreCase(categoryName);
        if (category.isPresent()) {
            category.ifPresent(device::setCategory);
        } else {
            throw new CategoryNotFoundException("Kategoria o podanej nazwie nie istnieje");
        }
        return device;
    }


    public void removeDevice() {
        System.out.println("Podaj id urządzenia, które chcesz usunąć:");
        long deviceId = scanner.nextLong();
        Optional<Device> device = deviceRepository.findById(deviceId);

        if (device.isPresent()) {
            device.ifPresent(deviceRepository::delete);
        } else {
            throw new CategoryNotFoundException("Kategoria o podanym ID nie istnieje");

        }
    }

    public void searchDevice() {
        System.out.println("Podaj fragment nazwy");
        String word=scanner.next();
        List<Device> deviceList = deviceRepository.findAllByNameContainingIgnoreCase(word);
        if (deviceList.isEmpty()) {
            System.out.println("Nie znaleziono takiego urzadzenie");
        }
        else
        {
            System.out.println("Znaleziono");
            deviceList.forEach(System.out::println);
        }

    }
}

package pl.javastart.devicerentboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.javastart.devicerentboot.dao.DeviceDao;
import pl.javastart.devicerentboot.model.Category;
import pl.javastart.devicerentboot.model.Customer;
import pl.javastart.devicerentboot.model.Device;
import pl.javastart.devicerentboot.repository.DeviceRepository;

@SpringBootApplication
public class DevicerentbootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DevicerentbootApplication.class, args);

//        DeviceDao deviceDao = ctx.getBean(DeviceDao.class);

        DeviceRepository deviceRepository = ctx.getBean(DeviceRepository.class);
        Device device = new Device();
        device.setName("Wiertarka udarowa2");
        device.setDescription("Wiertarka udarowa o dużej mocy 3000W z zestawem wierteł w komplecie");
        device.setPrice(80);
        device.setQuantity(5);

        Category category = new Category();
        category.setName("Elektronarzędzia 2");
        category.setDescription("Wiertarki, szlifierki, młoty udarowe i inne elektronarzędzia");

        Customer customer = new Customer();
        customer.setFirstName("Janek");
        customer.setLastName("Nowak");
        customer.setPesel("90908765123");
        customer.setIdNumber("ABC678123");

        device.setCategory(category);
        device.addCustomer(customer);

//        deviceDao.save(device);

        deviceRepository.save(device);

    }

}

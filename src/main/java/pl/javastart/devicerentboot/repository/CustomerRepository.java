package pl.javastart.devicerentboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javastart.devicerentboot.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

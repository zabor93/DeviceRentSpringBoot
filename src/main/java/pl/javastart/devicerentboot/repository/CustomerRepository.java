package pl.javastart.devicerentboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.devicerentboot.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

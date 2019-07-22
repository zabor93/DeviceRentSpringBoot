package pl.javastart.devicerentboot.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.devicerentboot.customer.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

package pl.javastart.devicerentboot.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastart.devicerentboot.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Customer customer){
        entityManager.persist(customer);
    }

    public Customer get(Long id) {
        Customer customer=entityManager.find(Customer.class, id);
        return customer;
    }

    @Transactional
    public Customer update(Customer customer) {
        return entityManager.merge(customer);
    }

    @Transactional
    public void delete(Customer customer) {
        Customer attachedCustomer = get(customer.getId());
        entityManager.remove(attachedCustomer);
    }

}

package pl.javastart.devicerentboot.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastart.devicerentboot.model.Device;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class DeviceDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Device device){
        entityManager.persist(device);
    }

    public Device get(Long id) {
        Device device=entityManager.find(Device.class, id);
        return device;
    }


    @Transactional
    public Device update(Device device) {
        return entityManager.merge(device);
    }

    @Transactional
    public void delete(Device device) {
        Device attachedDevice = get(device.getId());
        entityManager.remove(attachedDevice);
    }

}

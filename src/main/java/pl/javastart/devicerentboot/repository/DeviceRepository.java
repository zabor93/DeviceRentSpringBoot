package pl.javastart.devicerentboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.devicerentboot.model.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

}

package pl.javastart.devicerentboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javastart.devicerentboot.model.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {

}

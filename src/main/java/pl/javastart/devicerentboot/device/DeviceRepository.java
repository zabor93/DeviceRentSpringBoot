package pl.javastart.devicerentboot.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.devicerentboot.device.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

}

package allMySons.allMySons.Repository;

import allMySons.allMySons.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface deviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findByDevice(String device);

    @Query(value = "SELECT * FROM amssandesh WHERE is_checked_out=1", nativeQuery = true)
    List<Device> getCheckedDevice();
}

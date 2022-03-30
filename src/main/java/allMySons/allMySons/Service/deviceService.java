package allMySons.allMySons.Service;

import allMySons.allMySons.Repository.deviceRepository;
import allMySons.allMySons.Repository.userRepository;
import allMySons.allMySons.model.Device;
import allMySons.allMySons.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class deviceService {

    @Autowired
    private deviceRepository deviceRepository;

    @Autowired
    private userRepository userRepository;

    public Device createDevice(Device device){
        Device newDevice = new Device();
        newDevice.setDevice(device.getDevice());
        newDevice.setOs(device.getOs());
        newDevice.setManufacturer(device.getManufacturer());
        newDevice.setLastCheckedOutDate(device.getLastCheckedOutDate());
        newDevice.setLastCheckedOutBy(device.getLastCheckedOutBy());
        newDevice.setCheckedOut(false);
        return deviceRepository.save(newDevice);
    }

    public Optional<Device> getDevice(String device) {
        return deviceRepository.findByDevice(device);
    }

    public Optional<Device> getbyId(Long id) {
        return deviceRepository.findById(id);
    }

    public List<Device> getAllDevice(){
        return deviceRepository.findAll();
    }

    public void deleteById(Long id){
        deviceRepository.deleteById(id);
    }
    public List<Device> getCheckedDevice(){
        return deviceRepository.getCheckedDevice();
    }

    public Device updateDevice(Optional<Device> existingDevice, Device device){
        existingDevice.get().setDevice(device.getDevice());
        existingDevice.get().setOs(device.getOs());
        existingDevice.get().setManufacturer(device.getManufacturer());
        existingDevice.get().setLastCheckedOutBy(device.getLastCheckedOutBy());
        existingDevice.get().setLastCheckedOutDate(device.getLastCheckedOutDate());
        existingDevice.get().setCheckedOut(true);
        return deviceRepository.save(existingDevice.get());
    }

    //Users
    public User createUser(User user){
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setCount(user.getCount());
        return userRepository.save(newUser);
    }
    public Optional<User> getName(String name) {
        return userRepository.findByName(name);
    }
    public void deleteUserbyID(Long id){
        userRepository.deleteById(id);
    }
    public Optional<User> getbyUserId(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Optional<User> existingUser, User user){
        existingUser.get().setName(user.getName());
        existingUser.get().setCount(user.getCount());
        return userRepository.save(existingUser.get());
    }
}

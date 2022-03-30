package allMySons.allMySons.Controller;

import allMySons.allMySons.Service.deviceService;
import allMySons.allMySons.model.Device;
import allMySons.allMySons.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/main")
public class deviceController {

    @Autowired
    private deviceService deviceService;

    @PostMapping("/device")
    public ResponseEntity<Object> createDevice(@RequestBody @Valid Device device){
        Optional<Device> existingDevice = deviceService.getDevice(device.getDevice());
        if (existingDevice.isPresent()){
            return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST, "REQUEST FAILED",null,device.getDevice() +" already exist. Please Try different device name");
        }
        Device savedDevice = deviceService.createDevice(device);
        return ApiResponse.generateResponse(HttpStatus.CREATED, "device creation successful",savedDevice,null);
    }

    @GetMapping("/display")
    public Object getAllUsers() {
        List<Device> deviceList = deviceService.getAllDevice();
        if (deviceList.size()>0){
            return ApiResponse.generateResponse(HttpStatus.OK, deviceList.size() + "devices available", deviceList, null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND, "no Device available", null, "Devices not available");
    }

    @GetMapping("/devices/{id}")
    public ResponseEntity<Object> getbUserId(@PathVariable Long id){
        Optional<Device> unew = deviceService.getbyId(id);
        if (!unew.isPresent()){
            return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST, "Request failed", null, "Does not exist");
        }else{
            return ApiResponse.generateResponse(HttpStatus.OK, "Getting user with id", unew, null);

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteByid(@PathVariable  Long id){
        Optional<Device> unew = deviceService.getbyId(id);
        if (!unew.isPresent()){
            return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST, "Request failed", null, "Device id with " + id + "doesnot exist");
        }
        deviceService.deleteById(id);
        return ApiResponse.generateResponse(HttpStatus.OK, "Device deleted",null,null);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateDevice(@RequestBody @Valid Device device, @PathVariable Long id) {
        Optional<Device> existingDevice = deviceService.getbyId(id);
        if (existingDevice.isPresent()) {
            deviceService.updateDevice(existingDevice, device);
            return ApiResponse.generateResponse(HttpStatus.CREATED, "Device with id "+id+" is updated", existingDevice, null);
        }
        return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST, "Device with id "+id+"Not found",null,"Could not find the device");
    }

}

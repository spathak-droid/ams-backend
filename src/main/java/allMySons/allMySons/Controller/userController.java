package allMySons.allMySons.Controller;

import allMySons.allMySons.Service.deviceService;
import allMySons.allMySons.model.Device;
import allMySons.allMySons.model.User;
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
@RequestMapping("/user")
public class userController {
    @Autowired
    private deviceService deviceService;

    @PostMapping("/add")
    public ResponseEntity<Object> createUser(@RequestBody @Valid User user){
        Optional<User> existingUser = deviceService.getName(user.getName());
        if (existingUser.isPresent()){
            return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST, "REQUEST FAILED",null,user.getName() +" already exist. Please Try different user name");
        }
        User savedUser = deviceService.createUser(user);
        return ApiResponse.generateResponse(HttpStatus.CREATED, "user creation successful",savedUser,null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteByid(@PathVariable  Long id){
        Optional<User> unew = deviceService.getbyUserId(id);
        if (!unew.isPresent()){
            return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST, "Request failed", null, "Device id with " + id + " doesnot exist");
        }
        deviceService.deleteUserbyID(id);
        return ApiResponse.generateResponse(HttpStatus.OK, "Device deleted",null,null);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateDevice(@RequestBody @Valid User user, @PathVariable Long id) {
        Optional<User> existingUser = deviceService.getbyUserId(id);
        if (existingUser.isPresent()) {
            deviceService.updateUser(existingUser, user);
            return ApiResponse.generateResponse(HttpStatus.CREATED, "Device with id "+id+" is updated", existingUser, null);
        }
        return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST, "Device with id "+id+"Not found",null,"Could not find the user");
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<Object> getbUserId(@PathVariable String name){
        Optional<User> unew = deviceService.getName(name);
        if (!unew.isPresent()){
            return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST, "Request failed", null, "Does not exist");
        }else{
            return ApiResponse.generateResponse(HttpStatus.OK, "Getting user with id", unew, null);
        }
    }

    @GetMapping("/checked")
    public ResponseEntity<Object> getbUserId(){
        List<Device> unew = deviceService.getCheckedDevice();
        if (unew.size() <= 0){
            return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST, "Request failed", null, "Does not exist");
        }else{
            return ApiResponse.generateResponse(HttpStatus.OK, "Getting user with id", unew, null);
        }
    }
}

package com.HMPackage.controller;

import java.util.Optional;
import com.HMPackage.DTO.UserRoleDTO;
import com.HMPackage.baseResponse.PageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.HMPackage.DTO.UserDTO;
import com.HMPackage.baseResponse.BaseResponse;
import com.HMPackage.entity.User;
import com.HMPackage.service.UserServiceInterface;
import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceInterface userServiceInterface;
    Logger logging = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/login")
    public BaseResponse login(@RequestBody UserRoleDTO userRoleDTO) {
        logging.trace("Traced Successfully");
        BaseResponse baseResponse = null;
        baseResponse = BaseResponse.builder().Data(userServiceInterface.login(userRoleDTO)).build();
        return baseResponse;
    }

    @PostMapping("/create")
    public BaseResponse<User> addUser(@RequestBody UserDTO userDTO) {
        logging.trace("Traced Successfully");
        BaseResponse<User> baseResponse = null;
        baseResponse = BaseResponse.<User>builder().Data(userServiceInterface.addUser(userDTO)).build();
        return baseResponse;
    }

    @RolesAllowed(value = "USER")
    @GetMapping("/get/{id}")
    public BaseResponse<Optional<User>> getUserById(@PathVariable Long id) {
        BaseResponse<Optional<User>> baseResponse = null;
        baseResponse = BaseResponse.<Optional<User>>builder().Data(userServiceInterface.getUserbyId(id)).build();
        return baseResponse;
    }

    @PutMapping("/update")
    @RolesAllowed(value = "USER")
    public BaseResponse<Optional<User>> update(@RequestBody UserDTO userDTO) {
        logging.trace("Traced Successfully");
        BaseResponse<Optional<User>> baseResponse = null;
        baseResponse = BaseResponse.<Optional<User>>builder().Data(userServiceInterface.updatebyId(userDTO)).build();
        return baseResponse;
    }

    @RolesAllowed(value = "ADMIN")
    @PutMapping("/softdelete")
    public BaseResponse deletesoft(@RequestBody UserDTO userDTO) {
        logging.trace("Traced Successfully");
        BaseResponse<Optional<User>> baseResponse = null;
        baseResponse = BaseResponse.<Optional<User>>builder().Data(userServiceInterface.deletesoft(userDTO)).build();
        return baseResponse;
    }

    @GetMapping("/pagination/{pageno}/{pageSize}/{name}")
    @RolesAllowed(value = "ADMIN")
    private PageResponse<User> pageByUserName(@PathVariable int pageno, @PathVariable int pageSize, @PathVariable String name) {
        return userServiceInterface.pageByUserName(pageno, pageSize, name);
    }

    @GetMapping("/hello")
    public String helloworld() {
        logging.trace("from service side info");
        return "string from service side";
    }
}

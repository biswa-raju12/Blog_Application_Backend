package com.Applicatoin_Blog_demo.Controller;


import com.Applicatoin_Blog_demo.Entity.User;
import com.Applicatoin_Blog_demo.Exceptions.GlobalExceptionHandler;
import com.Applicatoin_Blog_demo.Exceptions.ResourceNotFoundException;
import com.Applicatoin_Blog_demo.Payroads.ApiResponse;
import com.Applicatoin_Blog_demo.Payroads.UserDTO;
import com.Applicatoin_Blog_demo.Service.UserService;
import jakarta.validation.Valid;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/Api/Users")
public class UserController {

    @Autowired
    private UserService userService;

    //Post- Create User

    @PostMapping("/")
    public ResponseEntity<UserDTO> CreateUser(@Valid @RequestBody UserDTO userDTO)
    {

        UserDTO userDTO1= this.userService.CreateUser(userDTO);
        return new ResponseEntity<>(userDTO1, HttpStatus.CREATED);
    }

    //Put - Update User
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> UpdateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable("id") Long Id) throws ResourceNotFoundException {

        UserDTO UpdatesUser= this.userService.UpdateUser(userDTO,Id);
        return new ResponseEntity<>(UpdatesUser, HttpStatus.OK);
    }


    //Delete - Delete User

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> DeleteUser(@PathVariable("id") Long Id) throws ResourceNotFoundException {

        this.userService.DeleteUser(Id);

        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.ACCEPTED);

    }




    //Get - Get All user Details

    @GetMapping("/")
    public List<UserDTO> GetAllUsers()
    {
        List<UserDTO> userDTOS=this.userService.GetAllUsers();

        return userDTOS;
    }

    //Get - Get User by Id

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> GetAllUsers(@PathVariable("id") Long id) throws ResourceNotFoundException {
        UserDTO user=this.userService.GetUserById(id);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}

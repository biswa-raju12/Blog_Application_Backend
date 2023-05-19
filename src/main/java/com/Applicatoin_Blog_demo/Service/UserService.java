package com.Applicatoin_Blog_demo.Service;

import com.Applicatoin_Blog_demo.Entity.User;
import com.Applicatoin_Blog_demo.Exceptions.ResourceNotFoundException;
import com.Applicatoin_Blog_demo.Payroads.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

     UserDTO CreateUser(UserDTO userDTO);

    public UserDTO UpdateUser(UserDTO userDTO,Long userId) throws ResourceNotFoundException;
    public UserDTO GetUserById(Long userId) throws ResourceNotFoundException;
    public List<UserDTO> GetAllUsers();
//
    void DeleteUser(Long userId) throws ResourceNotFoundException;

}

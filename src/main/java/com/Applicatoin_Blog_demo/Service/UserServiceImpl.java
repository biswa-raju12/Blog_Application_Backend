package com.Applicatoin_Blog_demo.Service;

import com.Applicatoin_Blog_demo.Entity.User;
import com.Applicatoin_Blog_demo.Exceptions.ResourceNotFoundException;
import com.Applicatoin_Blog_demo.Payroads.UserDTO;
import com.Applicatoin_Blog_demo.Repositiory.UserRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDTO CreateUser( @RequestBody UserDTO userDTO) {
        User user= this.DTOtoUser(userDTO);

        User savedUser= this.userRepository.save(user);

        return this.UserToDTO(savedUser);

    }

    @Override
    public UserDTO UpdateUser(UserDTO userDTO, Long userId) throws ResourceNotFoundException {

        //get the user information  by id

        User user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserId",userId));


//        set the user information which is coming under request body

        user.setUserName(userDTO.getUserName());
        user.setUserEmail(userDTO.getUserEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());

        //Save the data

        User updatedUser = userRepository.save(user);

        //Convert user to userDTO and Return

        UserDTO userDTO1=this.UserToDTO(updatedUser);

        return userDTO1;
    }

    @Override
    public UserDTO GetUserById(Long userId) throws ResourceNotFoundException {

        User user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserId",userId));


        UserDTO userDTO=this.UserToDTO(user);


        return userDTO;
    }

    @Override
    public List<UserDTO> GetAllUsers() {

        List<User> users= userRepository.findAll();

        List<UserDTO> userDTOS= users.stream().map(user->this.UserToDTO(user)).collect(Collectors.toList());


        return userDTOS;
    }

    @Override
    public void DeleteUser(Long userId) throws ResourceNotFoundException {

        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

//        UserDTO userDTO=this.UserToDTO(user);

        userRepository.delete(user);
    }

    public User DTOtoUser(UserDTO userDTO)
    {
//        User user=new User();
//        user.setUserId(userDTO.getUserId());
//        user.setUserName(userDTO.getUserName());
//        user.setUserEmail(userDTO.getUserEmail());
//        user.setPassword(userDTO.getPassword());
//        user.setAbout(userDTO.getAbout());

        User user = this.modelMapper.map(userDTO,User.class);

        return user;

    }

    public UserDTO UserToDTO(User user)
    {
        UserDTO userDTO= this.modelMapper.map(user, UserDTO.class);

//        userDTO.setUserId(user.getUserId());
//        userDTO.setUserName(user.getUserName());
//        userDTO.setUserEmail(user.getUserEmail());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setAbout(user.getAbout());

        return userDTO;

    }

}

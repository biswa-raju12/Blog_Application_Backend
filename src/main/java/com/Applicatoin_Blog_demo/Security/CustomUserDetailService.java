//package com.Applicatoin_Blog_demo.Security;
//
//import com.Applicatoin_Blog_demo.Entity.User;
//import com.Applicatoin_Blog_demo.Exceptions.ResourceNotFoundException;
//import com.Applicatoin_Blog_demo.Repositiory.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class CustomUserDetailService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        //loading user from DataBase by UserName
//        User user = this.userRepository.findByUserName(username);
//
//
////                .orElseThrow(() -> new ResourceNotFoundException("User", "Email" + username, 0));
//
//
//        return user;
//    }
//}

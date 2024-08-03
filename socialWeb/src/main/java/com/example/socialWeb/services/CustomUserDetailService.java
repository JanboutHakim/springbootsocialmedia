package com.example.socialWeb.services;

import com.example.socialWeb.models.User;
import com.example.socialWeb.reoasitory.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByuser_name(username);
        if(user==null){
            throw new UsernameNotFoundException("Not found!");
        }
        UserDetails userDetails=new org.springframework.security.core.userdetails.User(
                user.getUser_name(),
                user.getPassword(),
                new ArrayList<>()
        );


        return userDetails;
    }
}

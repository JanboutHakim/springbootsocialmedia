package com.example.socialWeb.services;

import com.example.socialWeb.DTOS.UserDTO;
import com.example.socialWeb.models.User;
import com.example.socialWeb.reoasitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void add(UserDTO userDTO){
        MultipartFile image=userDTO.getProfilePicture();
        String imagePath="src/main/resources/static/images";
        try{
            Path uploadDir= Paths.get(imagePath);
            try(InputStream inputStream=image.getInputStream()) {
                Files.copy(inputStream,Paths.get(uploadDir+"/"+image.getOriginalFilename()),
                        StandardCopyOption.REPLACE_EXISTING);

            }
        }catch (Exception e){
            System.out.println(e);
        }
        User user=new User();
        user.setUser_name(userDTO.getUser_name());
        user.setPassword(userDTO.getPassword());
        user.setFullName(userDTO.getFullName());
        user.setProfilePicturePath("/images/"+image.getOriginalFilename());
        userRepository.save(user);

    }

    public User checkUsername(String userName){
        return userRepository.findByuser_name(userName);
    }

    public User findById(int id){
        return userRepository.findById(id).get();
    }
    public User findByUserName(String userName){
        return userRepository.findByuser_name(userName);

    }
}

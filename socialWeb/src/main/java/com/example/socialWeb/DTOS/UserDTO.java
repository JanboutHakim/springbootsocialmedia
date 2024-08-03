package com.example.socialWeb.DTOS;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String user_name;
    private String password;
    private String fullName;
    private MultipartFile profilePicture;

    @Override
    public String toString() {
        return "UserDTO{" +
                "user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", profilePicture=" + profilePicture +
                '}';
    }
}

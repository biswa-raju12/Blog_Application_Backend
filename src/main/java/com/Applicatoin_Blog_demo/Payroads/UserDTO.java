package com.Applicatoin_Blog_demo.Payroads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long UserId;
    @NotEmpty
    @Size(max = 100,message = "UserName must not be Blank!")
    private String UserName;

    @Email(message = "Correct Email is not found!")
    private String UserEmail;

    @NotEmpty
    @Size(min = 8, message = "min length Password Not Found")
    private String Password;
    @NotEmpty
    @Size(min = 10,message = "Enter min size value for this field")
    private String About;

}

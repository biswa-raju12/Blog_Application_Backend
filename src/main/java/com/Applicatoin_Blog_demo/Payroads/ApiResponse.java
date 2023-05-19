package com.Applicatoin_Blog_demo.Payroads;

import com.Applicatoin_Blog_demo.Repositiory.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ApiResponse {

    private String Message;
    private boolean Success;




}

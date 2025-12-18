package co.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncprder;
import org.springframework.web.bind.annotataion.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController{
    @Autowired
    private UserService userservice;

    @Autowired
    
}
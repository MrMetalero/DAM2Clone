package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.models.dto.UserDto;

@RestController
@RequestMapping("/api")
public class UserRestController {
    

    @GetMapping("/llamadaDTO")
    public UserDto mostrarDTO(){
        
        User user = new User("Daniel", "Mena");
        UserDto userDto = new UserDto();
        userDto.setTitle("Hola mundo Title");
        userDto.setName(user.getName());
        userDto.setLastname(user.getLastname());

        return userDto;

        
    }


    @PostMapping("/crearUsuario")
    public UserDto crearUsuario(@RequestBody UserDto userD){
       
        return userD;

    }


}

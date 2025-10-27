package com.microservicio.bff_gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservicio.bff_gateway.dto.NewUserDto;
import com.microservicio.bff_gateway.dto.ResponseLoginDto;
import com.microservicio.bff_gateway.dto.UserDto;
import com.microservicio.bff_gateway.service.LoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuariosBFFController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDto> login(@RequestBody UserDto userDto) throws Exception {
        ResponseLoginDto resp = loginService.login(userDto);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> crear(@RequestBody NewUserDto newUserDto) throws Exception {
        loginService.create(newUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
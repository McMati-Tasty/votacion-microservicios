package com.microservicio.bff_gateway.service;

import org.springframework.stereotype.Service;

import com.microservicio.bff_gateway.dto.NewUserDto;
import com.microservicio.bff_gateway.dto.ResponseLoginDto;
import com.microservicio.bff_gateway.dto.UserDto;
import com.microservicio.bff_gateway.requester.UsuariosRequester;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImp implements LoginService {

    private final UsuariosRequester usuariosRequester;

    @Override
    public ResponseLoginDto login(UserDto userDto) throws Exception {
        return usuariosRequester.login(userDto);
    }

    @Override
    public void create(NewUserDto newUserDto) throws Exception {
        usuariosRequester.create(newUserDto);
    }
}
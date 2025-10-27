package com.microservicio.bff_gateway.service;

import com.microservicio.bff_gateway.dto.NewUserDto;
import com.microservicio.bff_gateway.dto.ResponseLoginDto;
import com.microservicio.bff_gateway.dto.UserDto;

public interface LoginService {

    public ResponseLoginDto login(UserDto userDto) throws Exception;

    public void create(NewUserDto newUserDTO) throws Exception;

}

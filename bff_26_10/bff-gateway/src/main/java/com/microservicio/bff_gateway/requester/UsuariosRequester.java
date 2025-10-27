package com.microservicio.bff_gateway.requester;

import com.microservicio.bff_gateway.dto.NewUserDto;
import com.microservicio.bff_gateway.dto.ResponseLoginDto;
import com.microservicio.bff_gateway.dto.UserDto;

public interface UsuariosRequester {

    public ResponseLoginDto login(UserDto userDto) throws Exception;

    public void create(NewUserDto newUserDto) throws Exception;

}

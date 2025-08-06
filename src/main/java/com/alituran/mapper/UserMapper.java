package com.alituran.mapper;


import com.alituran.dto.AuthRequest;
import com.alituran.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User toUser(AuthRequest authRequest);

    AuthRequest toAuthRequest(User user);



}

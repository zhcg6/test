package com.person.stuservice.service;

import org.apache.ibatis.annotations.Mapper;


public interface ITokenService {
    void saveToken(String username, String uuid);
}

package com.person.stuservice.service.impl;

import com.person.stuservice.mapper.TokenMapper;
import com.person.stuservice.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class TokenService implements ITokenService {

    @Autowired
    TokenMapper tokenMapper;

    @Override
    public void saveToken(String username, String uuid) {
        tokenMapper.saveToken(username,uuid);
    }
}

package com.person.stuservice.service.impl;

import com.person.stuservice.entity.Zyh;
import com.person.stuservice.mapper.ZyhMapper;
import com.person.stuservice.service.IZyhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZyhService implements IZyhService {
    @Autowired
    ZyhMapper zyhMapper;

    @Override
    public Zyh listUser(String username) {
        return zyhMapper.listUser(username);
    }

    @Override
    public String selectPassword(String username) {
        return zyhMapper.select(username);
    }

    @Override
    public String selectId(String username) {
        return zyhMapper.selectId(username);
    }

    @Override
    public void updatePassword(String username, String password) {
        zyhMapper.update(username,password);
    }

    @Override
    public String selectPermission(String username) {
        return zyhMapper.selectPermission(username);

    }

    @Override
    public void updateUser(Zyh zyh) {
         zyhMapper.updateUser(zyh);
    }


    @Override
    public String getNumber(String username) {
        return zyhMapper.getNumber(username);
    }

    @Override
    public void save(Zyh zyh) {
        zyhMapper.save(zyh);
    }

    @Override
    public String selectPhoneNumber(String phonenumber) {
        return zyhMapper.getPhoneNumber(phonenumber);
    }

    @Override
    public String getUsername(String phonenumber) {
        return zyhMapper.getUsername(phonenumber);
    }



}

package com.person.stuservice.service;

import com.person.stuservice.entity.Zyh;

public interface IZyhService {
     Zyh listUser(String username);

    String selectPassword(String username);

    String selectId(String username);


    String getNumber(String username);

    void save(Zyh zyh);

    String selectPhoneNumber(String phonenumber);


    String getUsername(String phonenumber);

    void updatePassword(String username,String password);


    String selectPermission(String username);

    void updateUser(Zyh zyh);
}

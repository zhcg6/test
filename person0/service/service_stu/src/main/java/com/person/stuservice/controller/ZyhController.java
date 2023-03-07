package com.person.stuservice.controller;

import cn.hutool.jwt.JWTUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.person.stuservice.common.Result;
import com.person.stuservice.entity.Zyh;
import com.person.stuservice.service.ITokenService;
import com.person.stuservice.service.IZyhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/Zyh")
public class ZyhController {
    @Autowired
    private IZyhService iZyhService;
    @Autowired
    private ITokenService iTokenService;

    @PostMapping("/{username}/{password}")//登录
    public Result login(@PathVariable String username,@PathVariable String password){

        if(password.equals(iZyhService.selectPassword(username)))//登录成功
        {
            String permission = iZyhService.selectPermission(username);
            String secret = "cg";
            String token = JWT.create().withClaim("username",username).
                    withClaim("permission",permission).sign(Algorithm.HMAC256(secret));
//            String json = token;
//            DecodedJWT decode = JWT.decode(json);
//            String username1 = decode.getClaim("username").asString();
//            String permission1 = decode.getClaim("permission").asString();
//            System.out.println(username1);
//            System.out.println(permission1);
            return Result.success(token);
        }
        else {
            return Result.success("error");
        }

    }

    @PostMapping("/select/{username}")//验证账号以及得到电话号码
    public Result selectId(@PathVariable String username){
        String s = iZyhService.selectId(username);
        String number = iZyhService.getNumber(username);
        String[] strings = new String[2];
        strings[0] = s;
        strings[1] = number;
        return Result.success(strings);
    }

    @PutMapping("/update/{username}/{password}")//重置密码
    public Result updatePassword(@PathVariable String password,@PathVariable String username){
        iZyhService.updatePassword(username,password);
        return Result.success("success");
    }



    @RequestMapping("/save")//注册
    public Result saveUser(@RequestBody Zyh zyh){
        iZyhService.save(zyh);
        return Result.success();
    }

    @PostMapping("/select/phone/{phonenumber}")//验证电话号码是否存在
    public Result selectPhoneNumber(@PathVariable String phonenumber){
        String s = iZyhService.selectPhoneNumber(phonenumber);
        return Result.success(s);
    }

    @GetMapping("/findUser/{token}")//查看用户信息
    public Result findUser(@PathVariable String token){
            String json = token;
            DecodedJWT decode = JWT.decode(json);
            String username = decode.getClaim("username").asString();
            Zyh s = iZyhService.listUser(username);
            return Result.success(s);
    }

    @PostMapping("/getUsername/{phonenumber}")//通过电话查询账号
    public Result getUsername(@PathVariable String phonenumber){

        String username = iZyhService.getUsername(phonenumber);
        return Result.success(username);
    }

    @PostMapping("/getPermission/{token}")//通过token查询权限
    public Result getPermission(@PathVariable String token){
            String json = token;
            DecodedJWT decode = JWT.decode(json);
            String permission1 = decode.getClaim("permission").asString();
        return Result.success(permission1);
    }

    @RequestMapping("/updateUser/{token}")//编辑个人信息
    public Result updateUser(@RequestBody Zyh zyh,@PathVariable String token){
        String json = token;
        DecodedJWT decode = JWT.decode(json);
        String username = decode.getClaim("username").asString();
        if(username.equals(zyh.getUsername())) {
            iZyhService.updateUser(zyh);
            return Result.success("success");
        }else{
            return Result.success("error");
        }
    }

    @PostMapping("/getToken/{phonenumber}")//生成token
    public Result getToken(@PathVariable String phonenumber){
        String username = iZyhService.getUsername(phonenumber);
        String permission = iZyhService.selectPermission(username);
        String secret = "cg";
        String token = JWT.create().withClaim("username",username).
                withClaim("permission",permission).sign(Algorithm.HMAC256(secret));
        return Result.success(token);
    }


}

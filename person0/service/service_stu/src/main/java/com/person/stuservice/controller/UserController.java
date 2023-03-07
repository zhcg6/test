package com.person.stuservice.controller;


import com.person.commonutils.R;
import com.person.stuservice.entity.User;
import com.person.stuservice.service.UserService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lx
 * @since 2023-02-27
 */
@RestController
@RequestMapping("/stuservice/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @ApiModelProperty("添加用户信息")
    @PostMapping("addUser")
    public R addUser(@RequestBody User user){
        boolean save = userService.save(user);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiModelProperty("修改用户信息")
    @PostMapping("updateUser")
    public R updateUser(@RequestBody User user){
        boolean update = userService.updateById(user);
        if(update){
            return R.ok();
        }else {
            return R.error();
        }
    }

}


package com.imooc.controller;


import com.imooc.service.UserService;
import com.imooc.pojo.Users;
import com.imooc.pojo.vo.UserVo;
import com.imooc.utils.IMoocJSONResult;
import com.imooc.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RegistLoginController extends BaseController {
    @Autowired
    UserService userService;

    @RequestMapping("regist")
    public IMoocJSONResult regist(@RequestBody Users user) throws Exception {
        //1 val
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return IMoocJSONResult.errorMsg("用户名和密码不能为空");
        }
        //2
        boolean usernameIsExist = userService.queryUsernameExist(user.getUsername());
        if (usernameIsExist) {
            return IMoocJSONResult.errorMsg("用户名已经存在，请换一个再试");
        }
        //3
        user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
        user.setReceiveLikeCounts(0);
        user.setFansCounts(0);
        user.setFollowCounts(0);
        user.setNickname(user.getUsername());
        userService.saveUser(user);

        user.setPassword("");
        UserVo userVo = setRedisToken(user);
        return IMoocJSONResult.ok(userVo);
    }

    @RequestMapping("login")
    public IMoocJSONResult login(@RequestBody Users user) throws Exception {
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return IMoocJSONResult.errorMsg("用户名和密码不能为空");
        }
        //2
        String username = user.getUsername();
        String password = MD5Utils.getMD5Str(user.getPassword());
        Users userResult = userService.queryUserForLogin(username, password);
        //3
        if (userResult == null) {
            return IMoocJSONResult.errorMsg("用户名和密码错误");
        }
        userResult.setPassword("");
        UserVo userVo = setRedisToken(userResult);
        return IMoocJSONResult.ok(userVo);
    }

    @RequestMapping("logout")
    public IMoocJSONResult logout(String userId) {
        redis.del(USER_REDIS_SESSION + ":" + userId);
        return IMoocJSONResult.ok();
    }

    private UserVo setRedisToken(Users user) {
        String token = UUID.randomUUID().toString();
        redis.set(USER_REDIS_SESSION + ":" + user.getId(), token, 1000 * 60 * 30);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        userVo.setUserToken(token);
        return userVo;
    }
}

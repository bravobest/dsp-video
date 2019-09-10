package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.UsersReport;

public interface UserService {
    boolean queryUsernameExist(String username);
    void saveUser(Users users);

    public Users queryUserInfo(String userId);
    Users queryUserForLogin(String username, String password);
    Users queryUserById(String userId);

    boolean isUserLikeVideo(String userId, String videoId);

    void updateByUserId(String userId, String uploadPathDB);



    void saveUserFanRelation(String userId, String fanId);
    void deleteUserFanRelation(String userId, String fanId);



    boolean queryIfFollow(String userId, String fanId);


    void reportUser(UsersReport userReport);
}

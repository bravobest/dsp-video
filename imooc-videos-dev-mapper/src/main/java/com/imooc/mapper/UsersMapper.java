package com.imooc.mapper;

import com.imooc.pojo.Users;
import com.imooc.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface UsersMapper extends MyMapper<Users> {
    void updateByUserId(@Param("userId") String userId, @Param("uploadPathDB") String uploadPathDB);

    Users queryUserById(String userId);


    public void addReceiveLikeCount(String userId);
    public void reduceReceiveLikeCount(String userId);





    void addFansCount(String userId);

    void addFollersCount(String userId);

    void reduceFansCount(String userId);

    void reduceFollersCount(String userId);

}
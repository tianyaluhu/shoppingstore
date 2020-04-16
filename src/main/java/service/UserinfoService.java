package service;

import entity.Userinfo;

public interface UserinfoService {
    int deleteByPrimaryKey(Integer uId);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);


    Userinfo selectByname(String username);

    Userinfo selectBynameAndPwd(String username, String password);
}

package mapper;

import entity.Userinfo;
import org.apache.ibatis.annotations.Param;

public interface UserinfoMapper {
    int deleteByPrimaryKey(Integer uId);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);

    Userinfo selectByPrimaryKey(Integer uId);

    Userinfo selectByname(String username);

    Userinfo selectBynameAndPwd(@Param("username") String username,@Param("password") String password);
}